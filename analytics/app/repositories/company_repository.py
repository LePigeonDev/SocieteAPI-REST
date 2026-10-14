import pandas as pd
from sqlalchemy import text

from app.database.connection import get_engine


def get_company_data():
    query = text("""
        SELECT
            activite_principale_unite_legale,
            date_creation_unite_legale
        FROM mytable
    """)

    with get_engine().connect() as connection:
        for chunk in pd.read_sql_query(
            query,
            connection,
            chunksize=50000
        ):
            yield chunk
