import tools
import pytest
import requests

@pytest.mark.parametrize("url", ["http://localhost:8080/api/modify"])
def test_modify_lifePoints(url):
    # Create a pokemon
    tools.post_with_payload("http://localhost:8080/api/create", "dataset/create_evoli.json")
    # Modify pokemon
    response = tools.post_with_payload(url, "dataset/modify_evoli_lifepoints.json")
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    # Check if modification has been applied
    response = requests.get("http://localhost:8080/api/searchByName", {"name": "Evoli"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    assert body['result']
    assert body['result'].__len__() == 1
    assert body['result'][0]["lifePoints"] == 80

@pytest.mark.parametrize("url", ["http://localhost:8080/api/modify"])
def test_modify_add_power(url):
    # Modify pokemon
    response = tools.post_with_payload(url, "dataset/add_pikachu_power.json")
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    # Check if modification has been applied
    response = requests.get("http://localhost:8080/api/searchByName", {"name": "Pikachu"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    assert body['result']
    assert body['result'].__len__() == 1
    assert body['result'][0]["powers"].__len__() == 2
    power0 = {
            "powerName": "Boule Elec",
            "damageType": "ELECTRIC",
            "damage": 30
        }
    power1 = {
            "powerName": "Eclair Fou",
            "damageType": "ELECTRIC",
            "damage": 90
        }
    assert body['result'][0]["powers"][0] == power0 or body['result'][0]["powers"][0] == power1
    assert body['result'][0]["powers"][1] == power0 or body['result'][0]["powers"][1] == power1

@pytest.mark.parametrize("url", ["http://localhost:8080/api/modify"])
def test_modify_unexisting_pokemon(url):
    response = tools.post_with_payload(url, "dataset/modify_unexisting_pokemon.json")
    assert response.status_code == 404, f"Expected status code 404, but got {response.status_code}"

@pytest.mark.parametrize("url", ["http://localhost:8080/api/modify"])
def test_modify_invalid_request(url):
    response = tools.post_with_payload(url, "dataset/modify_invalid_json.json")
    assert response.status_code == 400, f"Expected status code 400, but got {response.status_code}"