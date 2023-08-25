import requests
import json

def post_with_payload(url, request_file):
    with open(request_file, "r") as input_file:
        create_request = json.load(input_file)
        return requests.post(url, json.dumps(create_request))

