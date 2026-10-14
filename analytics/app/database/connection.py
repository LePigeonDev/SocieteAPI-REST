
from functools import lru_cache

from sqlalchemy import create_engine
from sqlalchemy.engine import URL

from app.config import Config


@lru_cache(maxsize=1)
def get_engine():
    url = URL.create(
        drivername="mysql+pymysql",
        username=Config.MYSQL_USER,
        password=Config.MYSQL_PASSWORD,
        host=Config.MYSQL_HOST,
        port=Config.MYSQL_PORT,
        database=Config.MYSQL_DATABASE,
    )

    return create_engine(
        url,
        pool_pre_ping=True,
        pool_recycle=1800,
    )
