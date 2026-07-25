package net.engineeringdigest.JournalApp.repository;
import net.engineeringdigest.JournalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId>{

}
