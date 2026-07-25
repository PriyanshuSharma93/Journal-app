package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.api.response.WeatherResponse;
import net.engineeringdigest.JournalApp.cache.AppCache;
import net.engineeringdigest.JournalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apikey;


    @Autowired
    private RestTemplate resttemplate;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        String finalAPI = appCache.appcache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY, apikey);
        ResponseEntity<WeatherResponse> response = resttemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}



















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


