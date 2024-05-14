# Weather Data API

## Description

Weather Data API is a simple API created using Spring Boot (Java 17) that allows for fetching current weather data.

## Requirements

- Java 17
- Maven (for dependency management and project building)

## Project Structure

All backend source files are located in the directory :
`C:\Users\wcuba\OneDrive\Pulpit\solarweather\src\main\java\jakub\wojcik\solarweather\weatherResponse`

**Components:**
- `WeatherApiClient` - responsible for fetching weather data from the Open Meteo service.
- `WeatherDataParser` - used for parsing the received data and extracting necessary information for further processing.

**Configuration:**
- `WeatherConfig` - Spring configuration defining a `RestTemplate` bean for making HTTP requests to external services.

**Controller:**
- `WeatherController` - handles the `/weather` endpoint and returns weather data in JSON format.
- `WeatherControllerExceptionHandler` - manages exceptions reported by `WeatherController`, ensuring proper error handling and feedback to the client.

**Service:**
- `WeatherService` - contains the main business logic, integrating `WeatherApiClient` and `WeatherDataParser` to fetch and process weather data. The method `getWeatherData(double latitude, double longitude)` is used to obtain and return processed weather data.

## Tests
Tests have been prepared for key components of the application to ensure their correct functioning and reliability.
