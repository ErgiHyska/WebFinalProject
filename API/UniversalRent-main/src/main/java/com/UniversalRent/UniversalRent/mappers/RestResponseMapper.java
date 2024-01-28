package com.UniversalRent.UniversalRent.mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class RestResponseMapper {
    private RestResponseMapper() {}
    public static ResponseEntity<Object> map(String statusText, HttpStatus status, Object responseObj, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", statusText);
        map.put("data", responseObj);
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}

