package api.greenpeace.service;

import org.springframework.stereotype.Service;

import api.greenpeace.model.entity.Volunteer;
import api.greenpeace.model.repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerService {
    private final VolunteerRepository repository;

    public VolunteerService(VolunteerRepository repository) {
        this.repository = repository;
    }

    public List<Volunteer> findAll() {
        return repository.findAll();
    }

    public Volunteer save(Volunteer volunteer) {
    	
        return repository.save(volunteer);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Volunteer findById(String id) {
        return repository.findById(id).orElse(null);
    }
    
    public Volunteer findByName(String name) {
        return repository.findByName(name);
    }
    
}