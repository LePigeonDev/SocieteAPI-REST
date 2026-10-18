from flask import Flask
from flask_smorest import Api

from app.routes.stats import stats_bp

app = Flask(__name__)

app.config.update(
    API_TITLE="Societe Analytics",
    API_VERSION="v1",
    OPENAPI_VERSION="3.0.3",
    OPENAPI_URL_PREFIX="/",
    OPENAPI_SWAGGER_UI_PATH="/swagger-ui",
    OPENAPI_SWAGGER_UI_URL="https://cdn.jsdelivr.net/npm/swagger-ui-dist/"
)

api = Api(app)
api.register_blueprint(stats_bp, url_prefix="/api/v1")