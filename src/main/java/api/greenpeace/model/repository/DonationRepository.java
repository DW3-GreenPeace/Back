package api.greenpeace.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.greenpeace.model.entity.Donation;

public interface DonationRepository extends MongoRepository<Donation, String> {
}