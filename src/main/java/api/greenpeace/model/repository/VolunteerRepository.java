package api.greenpeace.model.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import api.greenpeace.model.entity.Volunteer;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
	
	public Volunteer findByName(String name);
	public Volunteer findByEmail(String name);
	
}
