package jakub.wojcik.solarweather.component;

import jakub.wojcik.solarweather.weatherResponse.component.WeatherDataParser;

import org.json.JSONArray;
import org.json.JSONException;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WeatherDataParserTest {
    @Test
    public void testParseWeatherData() throws JSONException {

        String expectedData = "[{\"date\":\"2024-05-13\",\"max_temperature\":33.2,\"estimated_generated_energy_kWh\":5.94,\"weather_code\":2,\"min_temperature\":22.9},"
                + "{\"date\":\"2024-05-14\",\"max_temperature\":34.9,\"estimated_generated_energy_kWh\":5.98,\"weather_code\":2,\"min_temperature\":22.8},"
                + "{\"date\":\"2024-05-15\",\"max_temperature\":36.3,\"estimated_generated_energy_kWh\":5.97,\"weather_code\":2,\"min_temperature\":23.5},"
                + "{\"date\":\"2024-05-16\",\"max_temperature\":35.9,\"estimated_generated_energy_kWh\":6.11,\"weather_code\":0,\"min_temperature\":24.5},"
                + "{\"date\":\"2024-05-17\",\"max_temperature\":36,\"estimated_generated_energy_kWh\":6.19,\"weather_code\":0,\"min_temperature\":25.3},"
                + "{\"date\":\"2024-05-18\",\"max_temperature\":37,\"estimated_generated_energy_kWh\":6.24,\"weather_code\":1,\"min_temperature\":25.4},"
                + "{\"date\":\"2024-05-19\",\"max_temperature\":38.8,\"estimated_generated_energy_kWh\":6.24,\"weather_code\":0,\"min_temperature\":26.1}]";
        JSONArray expected = new JSONArray(expectedData);

        var dataParser = new WeatherDataParser();
        String dataToParse = "{\"latitude\":22.0,\"longitude\":22.0,\"generationtime_ms\":0.14901161193847656,\"utc_offset_seconds\":7200,\"timezone\":\"Africa/Tripoli\",\"timezone_abbreviation\":\"EET\",\"elevation\":510.0,\"daily_units\":{\"time\":\"iso8601\",\"weathercode\":\"wmo code\",\"temperature_2m_max\":\"°C\",\"temperature_2m_min\":\"°C\",\"sunshine_duration\":\"s\"},\"daily\":{\"time\":[\"2024-05-13\",\"2024-05-14\",\"2024-05-15\",\"2024-05-16\",\"2024-05-17\",\"2024-05-18\",\"2024-05-19\"],\"weathercode\":[2,2,2,0,0,1,0],\"temperature_2m_max\":[33.2,34.9,36.3,35.9,36.0,37.0,38.8],\"temperature_2m_min\":[22.9,22.8,23.5,24.5,25.3,25.4,26.1],\"sunshine_duration\":[42770.53,43087.99,43006.70,43959.87,44580.03,44892.17,44936.30]}}";
        JSONArray response = dataParser.parseWeatherData(dataToParse);

        assertEquals(expected.toString(), response.toString());
    }
}
