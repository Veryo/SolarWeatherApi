package jakub.wojcik.solarweather.weatherResponse.controller;

import jakub.wojcik.solarweather.weatherResponse.service.WeatherService;
import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
    private WeatherService weatherService;


    @GetMapping("/weather")
    public ResponseEntity<String> getWeatherData(@RequestParam double latitude, @RequestParam double longitude) {

        logger.info("Received weather data request for Latitude: {}, Longitude: {}", latitude, longitude);
        JSONArray weatherData = weatherService.getWeatherData(latitude, longitude);
        logger.info("Weather data retrieved successfully.");
        return new ResponseEntity<>(weatherData.toString(2), HttpStatus.OK);

    }

}






