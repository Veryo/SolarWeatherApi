package jakub.wojcik.solarweather.weatherResponse.component;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClient {

    private static final String BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private final RestTemplate restTemplate;

    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchWeatherData(double latitude, double longitude) {

            String url = BASE_URL + "?latitude=" + latitude + "&longitude=" + longitude +
                    "&daily=weathercode,temperature_2m_max,temperature_2m_min,sunshine_duration&timezone=auto";
            return restTemplate.getForObject(url, String.class);

    }
}
