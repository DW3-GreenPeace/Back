package api.greenpeace.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.greenpeace.model.entity.Topic;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    
}