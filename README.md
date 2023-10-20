# Holdem

# run local app
java -jar dist/Holdem.jar

# build spring server
./gradlew build

# run spring server
java -jar build/libs/holdem-0.0.1-SNAPSHOT.jar 

# update spring server on docker hub
./gradlew build
// docker build -t holdem-app .
// docker tag holdem-app fanzengau/holdem-app
docker build -t fanzengau/holdem-app .
docker login
docker push fanzengau/holdem-app:latest
