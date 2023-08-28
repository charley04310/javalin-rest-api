import requests
import pytest
import json
import tools

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByType"])
def test_search_grass(url):
    response = requests.get(url, {"type": "GRASS"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    assert body['result']
    assert body['result'].__len__() == 2
    with open("dataset/create_bulbizarre.json", "r") as input_file:
        pokemon_bulbizarre = json.load(input_file)
        assert body['result'][0] == pokemon_bulbizarre or body['result'][1] == pokemon_bulbizarre
    with open("dataset/create_herbizarre.json", "r") as input_file:
        pokemon_herbizarre = json.load(input_file)
        assert body['result'][0] == pokemon_herbizarre or body['result'][1] == pokemon_herbizarre

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByType"])
def test_search_fire(url):
    response = requests.get(url, {"type": "FIRE"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    assert body['result']
    assert body['result'].__len__() == 1
    with open("dataset/create_dracaufeu.json", "r") as input_file:
        pokemon_dracaufeu = json.load(input_file)
        assert body['result'][0] == pokemon_dracaufeu

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByType"])
def test_search_unexisting_type(url):
    response = requests.get(url, {"type": "WIND"})
    assert response.status_code == 400, f"Expected status code 400, but got {response.status_code}"