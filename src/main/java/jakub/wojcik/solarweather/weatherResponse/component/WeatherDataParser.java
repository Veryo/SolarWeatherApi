package jakub.wojcik.solarweather.weatherResponse.component;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;



@Component
public class WeatherDataParser {

    private static final double PANEL_POWER_KW = 2.5;
    private static final double PANEL_EFFICIENCY = 0.2;

    public JSONArray parseWeatherData(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONObject dailyData = json.getJSONObject("daily");
        JSONArray dates = dailyData.getJSONArray("time");
        JSONArray weatherCodes = dailyData.getJSONArray("weathercode");
        JSONArray tempMin = dailyData.getJSONArray("temperature_2m_min");
        JSONArray tempMax = dailyData.getJSONArray("temperature_2m_max");
        JSONArray sunshineDuration = dailyData.getJSONArray("sunshine_duration");

        JSONArray outputArray = new JSONArray();

        for (int i = 0; i < dates.length(); i++) {
            double dailySunshineHours = sunshineDuration.getDouble(i) / 3600;  // Convert seconds to hours
            double generatedEnergy = PANEL_POWER_KW * PANEL_EFFICIENCY * dailySunshineHours;  // Calculate energy in kWh
            generatedEnergy = Math.round(generatedEnergy * 100.0) / 100.0;

            JSONObject dailyDataObject = new JSONObject();
            dailyDataObject.put("date", dates.getString(i));
            dailyDataObject.put("weather_code", weatherCodes.getInt(i));
            dailyDataObject.put("min_temperature", tempMin.getDouble(i));
            dailyDataObject.put("max_temperature", tempMax.getDouble(i));
            dailyDataObject.put("estimated_generated_energy_kWh", generatedEnergy);

            outputArray.put(dailyDataObject);
        }

        return outputArray;
    }
}
