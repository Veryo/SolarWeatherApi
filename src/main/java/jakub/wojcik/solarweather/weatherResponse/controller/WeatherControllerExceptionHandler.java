package jakub.wojcik.solarweather.weatherResponse.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WeatherControllerExceptionHandler {


    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message;
        if(ex.getValue().equals("null")){
            message = ("Enable Location ");
        } else {
            message = ("Latiude and Longitute should not be empty and they need to be numbers ");
        }


        logger.error(message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) throws IOException {
        String errorMessage = ex.getMessage();
        HttpStatus status = extractHttpStatus(errorMessage);
        String jsonContent = extractJsonContent(errorMessage);
        Map<String, String> errorDetails = parseJsonContent(jsonContent);
        String responseMessage = constructResponseMessage(errorDetails);
        logErrorDetails(responseMessage, status);
        return new ResponseEntity<>(responseMessage, status);
    }

    private HttpStatus extractHttpStatus(String errorMessage) {
        String httpStatusString = errorMessage.substring(0, errorMessage.indexOf(" "));
        return HttpStatus.valueOf(Integer.parseInt(httpStatusString));
    }

    private String extractJsonContent(String errorMessage) {
        return errorMessage.substring(errorMessage.indexOf("{"), errorMessage.lastIndexOf("}") + 1);
    }

    private Map<String, String> parseJsonContent(String jsonPart) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonPart);
        String reason = rootNode.path("reason").asText();
        String given = rootNode.path("Given").asText();
        Map<String, String> details = new HashMap<>();
        details.put("reason", reason);
        details.put("given", given);
        return details;
    }

    private String constructResponseMessage(Map<String, String> errorDetails) {
        return String.format("%s %s", errorDetails.get("reason"), errorDetails.get("given"));
    }

    private void logErrorDetails(String responseMessage, HttpStatus status) {
        logger.info(responseMessage);
        logger.info(String.valueOf(status));
    }
}
