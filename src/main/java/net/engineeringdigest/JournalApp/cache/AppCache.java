package net.engineeringdigest.JournalApp.cache;


import net.engineeringdigest.JournalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.JournalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;



    public  Map<String ,String > appcache;

    @PostConstruct
    public void init(){
        appcache=new HashMap<>();
        List<ConfigJournalAppEntity>all=configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            appcache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

    }
}
