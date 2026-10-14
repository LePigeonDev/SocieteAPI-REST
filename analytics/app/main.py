from flask import Flask
from app.routes.stats import stats_bp


def create_app():
    app = Flask(__name__)
    app.register_blueprint(stats_bp, url_prefix="/api/v1")
    return app


app = create_app()