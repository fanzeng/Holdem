/usr/bin/redis-server &
java -jar -noverify -XX:TieredStopAtLevel=1 /app.jar
