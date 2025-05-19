import yfinance as yf
import oracledb
import os
import pandas as pd
from datetime import datetime, timedelta
import logging
import time
from scripts.common.db_connection import get_connection

logging.basicConfig(
    filename='\\'.join(os.path.join(os.path.dirname(__file__)).split('\\')[:-1]) + '\\logs\\update_etf_history.log',
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logging.info('Script started.')




with get_connection() as connection:
    with connection.cursor() as cursor:

        logging.info('Succesfully connected to the database.')

        select_tickers_query = """SELECT ticker FROM etf ORDER BY ticker"""
        cursor.execute(select_tickers_query)
        tickers = [row[0] for row in cursor.fetchall()]
        requests_counter = 0

        for ticker in tickers:
            logging.info(f'Starting update for ETF: {ticker}')
            print(f'Starting update for ETF: {ticker}')

            select_last_date_query = """
            SELECT MAX(data) FROM etf_history
            WHERE ticker = :ticker
            """
            cursor.execute(select_last_date_query, {"ticker": ticker})
            last_date = cursor.fetchone()[0]


            if last_date is None:
                next_date = datetime(2000, 1, 1)
            else:
                next_date = last_date + timedelta(days=1)


            if next_date.date() > datetime.now().date():
                logging.info(f'Data already up to date for ETF: {ticker}')
                continue


            logging.info(f'Downloading data for {ticker} from: {next_date} until today...')
            print(f'Downloading data for {ticker} from: {next_date} until today...')
            etf = yf.Ticker(ticker)
            hist = etf.history(start=next_date.date(), end=datetime.now().date(), interval='1d')

            if hist.empty:
                logging.warning(f'No data available for ETF: {ticker} for {next_date.date()}...')
                print(f'No data available for ETF: {ticker} for {next_date.date()}...')
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
            INSERT INTO etf_history (DATA, TICKER, OPEN_PRICE, HIGH_PRICE, LOW_PRICE, CLOSE_PRICE, VOLUME)
            VALUES (:1, :2, ROUND(:3, 2), ROUND(:4, 2), ROUND(:5, 2), ROUND(:6, 2), :7)
            """

            for idx, row in hist.iterrows():
                try:
                    cursor.execute(insert_query, (
                        idx.date(),
                        ticker,
                        row['OPEN_PRICE'],
                        row['HIGH_PRICE'],
                        row['LOW_PRICE'],
                        row['CLOSE_PRICE'],
                        row['VOLUME'],
                    ))
                except oracledb.DatabaseError as e:
                    logging.error(f"Error during the update for ETF {ticker} for date  {idx.date()}: {e}")
                    print(f"Error during the update for ETF {ticker} for date  {idx.date()}: {e}")
                    continue

            connection.commit()
            logging.info(f'Data updated for ETF: {ticker}')
            print(f'Data updated for ETF: {ticker}')
            requests_counter += 1

            if requests_counter % 300 == 0 and requests_counter > 0:
                logging.info("Sleeping 120 sec.")
                print("sleeping 120 sec.")
                time.sleep(120)


logging.info('Script done.\n------------------------------------------------------------------------------\n')
print('Script completed successfully.')
