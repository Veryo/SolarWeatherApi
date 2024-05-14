package jakub.wojcik.solarweather.weatherResponse.service;

import jakub.wojcik.solarweather.weatherResponse.component.WeatherApiClient;
import jakub.wojcik.solarweather.weatherResponse.component.WeatherDataParser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {

    private final WeatherApiClient weatherApiClient;
    private final WeatherDataParser weatherDataParser;

    @Autowired
    public WeatherService(WeatherApiClient weatherApiClient, WeatherDataParser weatherDataParser) {
        this.weatherApiClient = weatherApiClient;
        this.weatherDataParser = weatherDataParser;
    }

    public JSONArray getWeatherData(double latitude, double longitude){
        String response = weatherApiClient.fetchWeatherData(latitude, longitude);
        return weatherDataParser.parseWeatherData(response);
    }
}
