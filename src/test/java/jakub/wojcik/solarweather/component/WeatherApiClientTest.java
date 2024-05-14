package jakub.wojcik.solarweather.component;
import jakub.wojcik.solarweather.weatherResponse.component.WeatherApiClient;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class WeatherApiClientTest {
    @Autowired
    private WeatherApiClient weatherApiClient;


    @Test
    public void testFetchWeatherCorrectData() {
        String responseString = weatherApiClient.fetchWeatherData(22.0, 22.0);

        assertNotNull(responseString, "The response string should not be null");
        assertTrue(responseString instanceof String, "The response should be a string");
    }

    @Test
    public void testFetchWeatherIncorrectData() {
        Exception exception = assertThrows(HttpClientErrorException.BadRequest.class, () -> {
            weatherApiClient.fetchWeatherData(11111.0, 22.0);
        });

        assertNotNull(exception.getMessage());
        assertTrue(exception.getMessage().contains("400 Bad Request"));
    }


}
