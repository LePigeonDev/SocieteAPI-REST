
import pandas as pd

from app.repositories.company_repository import get_company_data


def get_activity_statistics():
    totals = None

    for df in get_company_data():
        stats = (
            df.groupby("activite_principale_unite_legale")
            .size()
        )

        if totals is None:
            totals = stats
        else:
            totals = totals.add(stats, fill_value=0)

    if totals is None:
        return []

    return [
        {
            "activity_code": str(code),
            "company_count": int(count)
        }
        for code, count in totals.sort_values(
            ascending=False
        ).items()
    ]


def get_activity_statistics_by_year():
    totals = None

    for df in get_company_data():
        df["year"] = pd.to_datetime(
            df["date_creation_unite_legale"],
            errors="coerce"
        ).dt.year

        stats = (
            df.dropna(subset=["year"])
            .groupby([
                "activite_principale_unite_legale",
                "year"
            ])
            .size()
        )

        if totals is None:
            totals = stats
        else:
            totals = totals.add(stats, fill_value=0)

    if totals is None:
        return []

    return [
        {
            "activity_code": str(code),
            "year": int(year),
            "company_count": int(count)
        }
        for (code, year), count in totals.sort_values(
            ascending=False
        ).items()
    ]
