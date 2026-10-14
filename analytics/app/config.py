
import os


class Config:
    MYSQL_HOST = os.environ.get("MYSQL_HOST", "mysql")
    MYSQL_PORT = int(os.environ.get("MYSQL_PORT", "3306"))
    MYSQL_DATABASE = os.environ["MYSQL_DATABASE"]
    MYSQL_USER = os.environ["MYSQL_USER"]
    MYSQL_PASSWORD = os.environ["MYSQL_PASSWORD"]
