import os
import oracledb as odb

usr = "IM"
pwd = os.getenv("IM_PASSWORD")
dsn = os.getenv("DSN")

def get_connection():
    return odb.connect(user=usr, password=pwd, dsn=dsn)
