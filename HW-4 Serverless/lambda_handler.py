import http.client
import json

def lambda_handler(event, context):
    api_key = "xK/BUXJr+psSxfuvp/RlxQ==F1DqzLWXBpisvZKI"
    api_endpoint = "/v1/jokes?limit=1"
    
    conn = http.client.HTTPSConnection("api.api-ninjas.com")

    headers = {
        "X-Api-Key": api_key
    }
    responseheaders={
                    "Access-Control-Allow-Headers": "Content-Type",
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "OPTIONS, POST, GET"
                }
    try:
        conn.request("GET", api_endpoint, headers=headers)
        response = conn.getresponse()
        data = response.read()

        if response.status == 200:
            joke_data = json.loads(data.decode())
            joke = joke_data[0]["joke"] if joke_data else "No jokes found."
            return {
                "statusCode": 200,
                "body": joke
            }
        else:
            return {
                "statusCode": response.status,
                "body": "Failed to retrieve a joke."
            }
    except Exception as e:
        return {
            "statusCode": 500,
            "body": "Error: " + str(e)
        }
    finally:
        conn.close()