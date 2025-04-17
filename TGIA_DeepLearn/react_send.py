import requests

data = {'data': 'data'}


def send_data_to_spring(data):
    url = 'http://223.194.129.108:8080/send-data'
    response = requests.post(url, data=data)
    print(response)
    if response.status_code == 200:
        print('data send successfully')
    else:
        print('error')


response = requests.get('http://223.194.129.108:8080')
print(response.status_code)
print(response.text)
