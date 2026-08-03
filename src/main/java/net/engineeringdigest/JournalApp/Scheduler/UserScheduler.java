package net.engineeringdigest.JournalApp.Scheduler;

import net.engineeringdigest.JournalApp.cache.AppCache;
import net.engineeringdigest.JournalApp.entity.JournalEntry;
import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.enums.Sentiment;
//package net.engineeringdigest.JournalApp.repository;

import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import net.engineeringdigest.JournalApp.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    // Runs every Sunday at 9:00 AM
    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {

        List<User> users = userRepository.getUserForSA();

        for (User user : users) {

            List<JournalEntry> journalEntries = user.getJournalEntries();

            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate()
                            .isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getSentiment)
                    .toList();

            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();

            for (Sentiment sentiment : sentiments) {

                if (sentiment != null) {
                    sentimentCounts.put(
                            sentiment,
                            sentimentCounts.getOrDefault(sentiment, 0) + 1
                    );
                }
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry
                    : sentimentCounts.entrySet()) {

                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {

                emailService.sendEmail(
                        user.getEmail(),
                        "Sentiment for last 7 days",
                        mostFrequentSentiment.toString()
                );
            }
        }
    }

    // Runs every Sunday at 9:00 AM
    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearAppCache() {
        appCache.init();
    }
}