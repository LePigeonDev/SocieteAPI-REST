
from flask import Blueprint, jsonify
from sqlalchemy.exc import SQLAlchemyError

from app.services.analytics import (
    get_activity_statistics,
    get_activity_statistics_by_year
)


stats_bp = Blueprint("statistics", __name__)


@stats_bp.get("/activity")
def activity_statistics():
    try:
        results = get_activity_statistics()

        return jsonify({
            "count": len(results),
            "data": results
        }), 200

    except SQLAlchemyError:
        return jsonify({"error": "Database unavailable"}), 503


@stats_bp.get("/activity/by-year")
def activity_statistics_by_year():
    try:
        results = get_activity_statistics_by_year()

        return jsonify({
            "count": len(results),
            "data": results
        }), 200

    except SQLAlchemyError:
        return jsonify({"error": "Database unavailable"}), 503
