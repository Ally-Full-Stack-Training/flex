NOTES:

Procfile is a Heroku file to specify how the application starts on Heroku service:

web: java -Dserver.port=$PORT $JAVA_OPTS -jar target/app.jar

see more details on procfiles here: https://devcenter.heroku.com/articles/procfile

