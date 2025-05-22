import yfinance as yf
import oracledb
import os
import pandas as pd
from datetime import datetime, timedelta
import logging
import time
from scripts.common.db_connection import get_connection

logging.basicConfig(
    filename='\\'.join(os.path.join(os.path.dirname(__file__)).split('\\')[:-1]) + '\\logs\\update_crypto_history.log',


    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logging.info('Script started.')

with get_connection() as connection:
    with connection.cursor() as cursor:


        logging.info('Successfully connected to the database.')


        select_symbols_query = """SELECT symbol FROM crypto ORDER BY symbol"""
        cursor.execute(select_symbols_query)
        symbols = [row[0] for row in cursor.fetchall()]
        requests_counter = 0

        for symbol in symbols:
            try:
                logging.info(f'Starting update for Crypto: {symbol}')
                print(f'Starting update for Crypto: {symbol}')

                select_last_date_query = """
                SELECT MAX(data) FROM crypto_history
                WHERE symbol = :symbol
                """
                cursor.execute(select_last_date_query, {"symbol": symbol})
                last_date = cursor.fetchone()[0]

                if last_date is None:
                    next_date = datetime(2000, 1, 1)
                else:
                    next_date = last_date + timedelta(days=1)

                if next_date.date() > datetime.now().date():
                    logging.info(f'Data already up to date for crypto: {symbol}')
                    continue

                logging.info(f'Downloading data for {symbol} from: {next_date} until today...')
                print(f'Downloading data for {symbol} from: {next_date} until today...')
                crypto = yf.Ticker(symbol)
                end_date = (datetime.now() + timedelta(days=1)).date()
                hist = crypto.history(start=next_date.date(), end=end_date, interval="1d")
            except Exception as e:
                logging.error(f"Error during processing of {symbol}: {e}", exc_info=True)
                print(f"Error during processing of {symbol}: {e}")
                continue

            if hist.empty:
                logging.warning(f'No data available for Crypto: {symbol} for {next_date.date()}...')
                print(f'No data available for Crypto: {symbol} for {next_date.date()}...')
                continue

            hist = hist.rename(columns={
                'Date': 'DATA',
                'Open': 'OPEN_PRICE',
                'High': 'HIGH_PRICE',
                'Low': 'LOW_PRICE',
                'Close': 'CLOSE_PRICE',
                'Volume': 'VOLUME',
            })

            hist = hist.fillna(0)

            for col in ['OPEN_PRICE', 'HIGH_PRICE', 'LOW_PRICE', 'CLOSE_PRICE', 'VOLUME']:
                hist[col] = pd.to_numeric(hist[col], errors='coerce').fillna(0)

            insert_query = """
            INSERT INTO crypto_history (DATA, SYMBOL, OPEN_PRICE, HIGH_PRICE, LOW_PRICE, CLOSE_PRICE, VOLUME)
            VALUES (:1, :2, ROUND(:3, 2), ROUND(:4, 2), ROUND(:5, 2), ROUND(:6, 2), :7)
            """

            for idx, row in hist.iterrows():
                try:
                    cursor.execute(insert_query, (
                        idx.date(),
                        symbol,
                        row['OPEN_PRICE'],
                        row['HIGH_PRICE'],
                        row['LOW_PRICE'],
                        row['CLOSE_PRICE'],
                        row['VOLUME'],
                    ))
                except oracledb.DatabaseError as e:
                    logging.error(f"Error during the update for Crypto {symbol} for date  {idx.date()}: {e}")
                    print(f"Error during the update for Crypto {symbol} for date  {idx.date()}: {e}")
                    continue

            connection.commit()
            logging.info(f'Data updated for Crypto: {symbol}')
            print(f'Data updated for Crypto: {symbol}')
            requests_counter += 1

            if requests_counter % 300 == 0 and requests_counter > 0:
                logging.info("Sleeping 120 sec.")
                print("sleeping 120 sec.")
                time.sleep(120)


logging.info('Script done.\n------------------------------------------------------------------------------\n')
print('Script completed successfully.')
