# rcr-service


## Local run

Code is written using Java 17. Setup Java > 17 and build. You need maven to build the app.
Will generate file `target/rcr-service-1.0-SNAPSHOT-jar-with-dependencies.jar` with all dependencies.

```
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
cd rcr-service
mvn clean install
java -jar target/rcr-service-1.0-SNAPSHOT-jar-with-dependencies.jar

# Test the app
curl --request POST 'localhost:8080/commands' \
--header 'Content-Type: application/json' \
--data-raw '{
    "commands": ["LEFT", "GRAB", "LEFT", "BACK", "LEFT", "BACK", "LEFT"]
}'

curl --request GET 'localhost:8080/rcrs/LEFT'
# should return {"rcr":"1"}

curl --request GET 'localhost:8080/rcrs/GRAB'
# should return {"rcr":"00"}

curl --request GET 'localhost:8080/rcrs/BACK'
# should return {"rcr":"01"}
```


## Dockerfile

Dockerfile maps local port `8080` to `80`.
You can build using the Dockerfile like below.

```
cd rcr-service
docker build -t rcr-app .
docker images # check image rcr-app:latest is created
docker run -d -p 80:8080 rcr-app

# Test the app
curl --request POST 'localhost:80/commands' \
--header 'Content-Type: application/json' \
--data-raw '{
    "commands": ["LEFT", "GRAB", "LEFT", "BACK", "LEFT", "BACK", "LEFT"]
}'

curl --request GET 'localhost:80/rcrs/LEFT'
# should return {"rcr":"1"}

curl --request GET 'localhost:80/rcrs/GRAB'
# should return {"rcr":"00"}

curl --request GET 'localhost:80/rcrs/BACK'
# should return {"rcr":"01"}
```


## Docker-compose

Included also docker-compose.yaml.
You can also run the app using below commands.

```
cd rcr-service
docker-compose up -d

# Test the app
curl --request POST 'localhost:80/commands' \
--header 'Content-Type: application/json' \
--data-raw '{
    "commands": ["LEFT", "GRAB", "LEFT", "BACK", "LEFT", "BACK", "LEFT"]
}'

curl --request GET 'localhost:80/rcrs/LEFT'
# should return {"rcr":"1"}

curl --request GET 'localhost:80/rcrs/GRAB'
# should return {"rcr":"00"}

curl --request GET 'localhost:80/rcrs/BACK'
# should return {"rcr":"01"}
```




