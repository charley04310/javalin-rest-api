import requests
import pytest
import json
import tools

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByName"])
def test_search_pikachu(url):
    response = requests.get(url, {"name": "Pikachu"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    with open("dataset/create_pikachu.json", "r") as input_file:
        pokemon_pikachu = json.load(input_file)
        assert body['result'][0] == pokemon_pikachu

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByName"])
def test_search_dracaufeu(url):
    response = requests.get(url, {"name": "Dracau"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    assert body['result']
    assert body['result'].__len__() == 1
    with open("dataset/create_dracaufeu.json", "r") as input_file:
        pokemon_dracaufeu = json.load(input_file)
        assert body['result'][0] == pokemon_dracaufeu

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByName"])
def test_search_bizarre(url):
    tools.post_with_payload("http://localhost:8080/api/create", "dataset/create_herbizarre.json")

    response = requests.get(url, {"name": "bizarre"})
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

@pytest.mark.parametrize("url", ["http://localhost:8080/api/searchByName"])
def test_search_unexisting(url):
    response = requests.get(url, {"name": "Pancham"})
    assert response.status_code == 200, f"Expected status code 200, but got {response.status_code}"
    body = response.json()
    assert body['result'] == []