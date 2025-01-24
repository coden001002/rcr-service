# rcr-service


## Local run

Code is written using Java 17. Setup Java > 17 and build. You need maven to build the app.
Will generate file `target/rcr-service-1.0-SNAPSHOT-jar-with-dependencies.jar` with all dependencies.

```
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
cd rcr-service
mvn clean install
java -jar target/rcr-service-1.0-SNAPSHOT-jar-with-dependencies.jar
```


## Dockerfile

Dockerfile maps local port `8080` to `80`.
You can build using the Dockerfile like below.

```
cd rcr-service
docker build -t rcr-app .
docker run -p 80:80 rcr-app
```


## Testing the app

You can test the app as below.

```
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

