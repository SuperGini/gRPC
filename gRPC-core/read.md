# Generate certificate and private key:

````
openssl req -x509 -nodes -newkey rsa:4096 -sha256 -keyout server.key -out server.crt -days 3650
````
