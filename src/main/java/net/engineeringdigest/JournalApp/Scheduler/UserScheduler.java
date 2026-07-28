package net.engineeringdigest.JournalApp.Scheduler;

import net.engineeringdigest.JournalApp.cache.AppCache;
import net.engineeringdigest.JournalApp.entity.JournalEntry;
import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.JournalApp.service.EmailService;
import net.engineeringdigest.JournalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentsAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron="0 0 9 ** SUN")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user:users){
           List<JournalEntry> journalEntries= user.getJournalEntries();
         List<String> filteredEntries=  journalEntries.stream().filter(x -> x.getDate()
                 .isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                 .map(x->x.getContent()).collect(Collectors.toList());
         String entry =String.join(" ",filteredEntries);
         String sentiments= SentimentAnalysisService.getSentiment(entry);
         emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days", sentiments);
        }
    }

   @Scheduled(cron="0 0 9 ** SUN")
    public void clearAppCache(){
        appCache.init();
    }


}
