package jakub.wojcik.solarweather.controller;

import jakub.wojcik.solarweather.weatherResponse.controller.WeatherController;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;



@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private WeatherController weatherController ;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getWeatherDataIsOk() throws Exception {

        mockMvc.perform(get("/weather")
                        .param("latitude", "22")
                        .param("longitude", "22")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());
    }


    @Test
    public void getWeatherDataWrongLatitude() throws Exception {
        mockMvc.perform(get("/weather")
                        .param("latitude", "1111111")
                        .param("longitude", "22"))
                .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Latitude must be in range of -90 to 90°. Given: 1111111.0")));
    }

    @Test
    public void getWeatherDataLatitudeIsText() throws Exception {
        mockMvc.perform(get("/weather")
                        .param("latitude", "tttt")
                        .param("longitude", "22"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Latiude and Longitute should not be empty and they need to be numbers")));
    }
}
