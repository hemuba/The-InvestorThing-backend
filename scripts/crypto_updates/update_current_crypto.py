
import oracledb
import os
import logging
from datetime import datetime
from scripts.common.db_connection import get_connection

logging.basicConfig(
    filename='\\'.join(os.path.join(os.path.dirname(__file__)).split('\\')[:-1]) + '\\logs\\update_current_crypto.log',

    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s'
)
logging.info('Script started %s', datetime.now())


try:
    with get_connection() as connection:
        logging.info("Connected to the database.")
        print("Connected to the database.")
        with connection.cursor() as cursor:

            select_symbols_query = "SELECT symbol, purchase_price, no_of_coins FROM current_crypto"
            cursor.execute(select_symbols_query)
            current_crypto = cursor.fetchall()

            if not current_crypto:
                logging.warning("No Symbols found in CURRENT_CRYPTO.")


            for row in current_crypto:
                symbol = row[0]
                purchase_price = row[1]
                no_of_coins = row[2]

                select_last_close_query = """
                SELECT close_price FROM crypto_history
                WHERE symbol = :symbol AND data = (SELECT MAX(data) FROM crypto_history WHERE symbol = :symbol)
                """
                cursor.execute(select_last_close_query, {"symbol": symbol})
                last_close_price = cursor.fetchone()

                if last_close_price is None:
                    logging.warning(f"No available data for: {symbol}")
                    continue

                close_price = round(last_close_price[0], 2)

                try:


                    current_price = close_price
                    current_total = round(current_price * no_of_coins, 2)
                    current_return = round((current_price - purchase_price) * no_of_coins, 2)

                    percentage_return = round(((current_price - purchase_price) / purchase_price) * 100, 2)

                    logging.info(f"Symbol: {symbol}, Current Price: {current_price}, Purchase Price: {purchase_price}, No of Shares: {no_of_coins}")
                    logging.info(f"Current Total: {current_total}")

                    update_query = """
                    UPDATE current_crypto
                    SET current_price = :current_price,
                        current_total = :current_total,
                        current_return = :current_return
                    WHERE symbol = :symbol
                    """

                    cursor.execute(update_query, {
                        "current_price": current_price,
                        "current_total": current_total,
                        "current_return": current_return,
                        "symbol": symbol
                    })

                    logging.info(f"Updated Symbol: {symbol} new price closure: {current_price}")
                except Exception as e:
                    logging.error(f"Error during symbol update {symbol}: {str(e)}")
                    continue


            connection.commit()
            logging.info('Update completed.')
            print('Update completed.')

except oracledb.Error as e:
    logging.error(f"Connection to database refused {e}")
    raise

logging.info('Script done.\n------------------------------------------------------------------------------\n')