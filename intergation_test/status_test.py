import requests
import pytest

@pytest.mark.parametrize("url", ["http://localhost:8080/api/status"])
def test_api_status(url):
    response = requests.get(url)
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
