web: java $JAVA_OPTS -jar target/BetterEbay-1.0-SNAPSHOT.jar db migrate heroku.yml && java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar target/BetterEbay-1.0-SNAPSHOT.jar server heroku.yml
