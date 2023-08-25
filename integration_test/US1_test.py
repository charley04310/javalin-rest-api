

import pytest
import tools

@pytest.mark.parametrize("url", ["http://localhost:8080/api/create"])
def test_create_pikachu(url):
    response = tools.post_with_payload(url, "dataset/create_pikachu.json")
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"

@pytest.mark.parametrize("url", ["http://localhost:8080/api/create"])
def test_create_dracaufeu(url):
    response = tools.post_with_payload(url, "dataset/create_dracaufeu.json")
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"

@pytest.mark.parametrize("url", ["http://localhost:8080/api/create"])
def test_create_bulbizarre(url):
    response = tools.post_with_payload(url, "dataset/create_bulbizarre.json")
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"

@pytest.mark.parametrize("url", ["http://localhost:8080/api/create"])
def test_post_with_payload_already_in_set(url):
    response = tools.post_with_payload(url, "dataset/create_bulbizarre.json")
    assert response.status_code == 400, f"Expected status code 200, but got {response.status_code}"

@pytest.mark.parametrize("url", ["http://localhost:8080/api/create"])
def test_post_with_payload_invalid_json(url):
    response = tools.post_with_payload(url, "dataset/create_invalid_wrong_lifepoints.json")
    assert response.status_code == 400, f"Expected status code 400, but got {response.status_code}"
    response = tools.post_with_payload(url, "dataset/create_invalid_wrong_type.json")
    assert response.status_code == 400, f"Expected status code 400, but got {response.status_code}"