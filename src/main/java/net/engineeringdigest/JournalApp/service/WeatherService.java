package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apikey="9dfd60f9a354d7e796947c3d611f881b";
    private static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate resttemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY",city).replace ("API_KEY",apikey);
        ResponseEntity<WeatherResponse> response = resttemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;


//    public WeatherResponse getWeather(String city){
//        String finalAPI = API.replace("CITY",city).replace ("API_KEY",apikey);
//        HttpHeaders httpHeaders =new HttpHeaders();
//        httpHeaders.set("key","value");
//        User user = User.builder().username("priyanshu").password("priyanshu").build();
//        HttpEntity<User> httpEntity=new HttpEntity<>(user);
//        ResponseEntity<WeatherResponse> response = resttemplate.
//        exchange(finalAPI, HttpMethod.POST,
//        new httpEntity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//

    }
}
