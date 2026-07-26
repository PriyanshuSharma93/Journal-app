package net.engineeringdigest.JournalApp.repository;

import net.engineeringdigest.JournalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").exists(true).ne(null)
                        .ne(""));
        query.addCriteria(Criteria.where("sentimemtAnalysis").is(true));
        return mongoTemplate.find(query, User.class);
    }
}