package api.greenpeace.service;


import org.springframework.stereotype.Service;

import api.greenpeace.model.entity.Donation;
import api.greenpeace.model.repository.DonationRepository;

import java.util.List;

@Service
public class DonationService {
    private final DonationRepository repository;

    public DonationService(DonationRepository repository) {
        this.repository = repository;
    }

    public List<Donation> findAll() {
        return repository.findAll();
    }

    public Donation save(Donation donation) {
        return repository.save(donation);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Donation findById(String id) {
        return repository.findById(id).orElse(null);
    }
}