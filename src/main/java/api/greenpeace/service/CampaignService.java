package api.greenpeace.service;

import api.greenpeace.model.entity.Campaign;
import api.greenpeace.model.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {
    private final CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        this.repository = repository;
    }

    public List<Campaign> findAll() {
        return repository.findAll();
    }

    public Campaign save(Campaign campaign) {
        return repository.save(campaign);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Campaign findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
