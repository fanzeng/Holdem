FROM azul/zulu-openjdk:19-latest
VOLUME /tmp
RUN apt-get update && apt-get install -y redis-server
COPY build/libs/holdem-0.0.1-SNAPSHOT.jar app.jar
COPY script/start.sh start.sh
EXPOSE 8080
ENV PORT=8080
ENV HOST=0.0.0.0
ENTRYPOINT ["/bin/bash", "/start.sh"]