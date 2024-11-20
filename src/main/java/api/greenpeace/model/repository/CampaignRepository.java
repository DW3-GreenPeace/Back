package api.greenpeace.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.greenpeace.model.entity.Campaign;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
}