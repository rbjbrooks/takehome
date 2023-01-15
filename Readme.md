##Take Home Application

###Assumptions Made
- country codes that are input do not need to be in the same continent, thus the returned continents will be the list of continents that contain any of the input country codes
- input country codes that are not found or don't exist are discarded by the application with no error emitted, thus country codes that are found can still be processed appropriately
- output should be a flux/list of continents, not a single continent (this seems more appropriate and works better with rest api recommended practices in my opinion)

###Build and Run
- ensure you Java 17, Docker, and Gradle installed
- ensure you have internet access
- cd to project directory
- at command prompt: (cleans and builds project)
```
gradle clean & gradle build
``` 
- at command prompt:  (runs the microservice)
```
java -jar build/libs/takehome-0.0.1-SNAPSHOT.jar
```
- use any rest client to access the microservice at http://localhost:8080/takehomeapp/continent?countryCodes=CA,GB
- to build docker image: 
```
docker build --build-arg JAR_FILE=build/libs/takehome-0.0.1-SNAPSHOT.jar -t takehome/takehome-docker .
```
- to run docker image: 
```
docker run -p 8080:8080 takehome/takehome-docker
```

###Bonus Task
- I did not implement the bonus task due to time constraints
- This page gives a good overview of how to rate limit: https://www.baeldung.com/spring-bucket4j#:~:text=Rate%20limiting%20is%20a%20strategy,overuse%2C%20both%20unintentional%20and%20malicious.






