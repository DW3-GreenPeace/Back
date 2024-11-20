package api.greenpeace.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.greenpeace.model.entity.AboutUs;

@Repository
public interface AboutUsRepository extends MongoRepository<AboutUs, String> {
    
}