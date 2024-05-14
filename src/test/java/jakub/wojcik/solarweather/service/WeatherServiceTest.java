package jakub.wojcik.solarweather.service;

import jakub.wojcik.solarweather.weatherResponse.service.WeatherService;

import org.json.JSONArray;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;


    @Test
    public void testFetchWeatherServiceCorrectData() throws IOException {
        double latitude = 40.7128;
        double longitude = -74.0060;
        JSONArray response = weatherService.getWeatherData(latitude, longitude);

        assertNotNull(response, "The response should not be null");
        assertTrue(response instanceof JSONArray, "The response should be a JSONArray");

    }

    @Test
    public void testFetchWeatherServiceIncorrectData() {
        double latitude =1111111;
        double longitude = -74.0060;

        Exception exception = assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            weatherService.getWeatherData(latitude, longitude);
        });

        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("400 Bad Request"));

    }


}
