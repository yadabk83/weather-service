# weather-service

## Building the Project Yourself 

This project depends on maven for building. To build the jar locally, check out the project and build from source by doing the following:

    git clone https://github.com/yadabk83/weather-service.git
    cd weather-service
    mvn package

This will build the auto-configuration jar and associated sample. 

Note: As currently setup, the build requires Java 1.8. If you want to use another older version of Java adjust the build accordingly.

## Running the Sample 

The simplest way to run the sample is from the project root folder using maven. For example:

	mvn spring-boot:run
  
##
By default, application runs at port 8080. If you want it to run in different port, just update the application.properties file as
server.port=<your-port>
use postman to check the result as http://localhost:8080/weatherservice/find-weather
#request body, try with your zip code
{
	"zipCode":"22102"	
}
