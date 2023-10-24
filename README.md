# Holdem

# run local app
java -jar dist/Holdem.jar

# build spring server
./gradlew build

# run spring server
java -jar build/libs/holdem-0.0.1-SNAPSHOT.jar 

# update spring server on docker hub
./gradlew build
docker build -t fanzengau/holdem-app .
docker login
docker push fanzengau/holdem-app:latest

# run docker container locally
docker run -d --name docker-holdem-app -p 8080:8080 -p 6379:6379 fanzengau/holdem-app
docker exec -it docker-holdem-app bash