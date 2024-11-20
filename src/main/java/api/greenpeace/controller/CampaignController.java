package api.greenpeace.controller;

import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.Campaign;
import api.greenpeace.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin("*")
public class CampaignController {
    private final CampaignService service;

    public CampaignController(CampaignService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<WrapperResponseDTO<List<Campaign>>> getAll() {
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Campaigns retrieved successfully", service.findAll()));
    }

    @PostMapping
    public ResponseEntity<WrapperResponseDTO<Campaign>> create(@RequestBody Campaign campaign) {
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Campaign created successfully", service.save(campaign)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Campaign>> getById(@PathVariable String id) {
        Campaign campaign = service.findById(id);
        return campaign != null
                ? ResponseEntity.ok(new WrapperResponseDTO<>(true, "Campaign retrieved", campaign))
                : ResponseEntity.status(404).body(new WrapperResponseDTO<>(false, "Campaign not found", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Campaign>> update(
        @PathVariable String id,
        @RequestBody Campaign updatedCampaign
    ) {
        Campaign existingCampaign = service.findById(id);

        if (existingCampaign == null) {
            return ResponseEntity.status(404)
                .body(new WrapperResponseDTO<>(false, "Campaign not found", null));
        }

        // Atualizando os campos do objeto existente
        existingCampaign.setTitle(updatedCampaign.getTitle());
        existingCampaign.setDescription(updatedCampaign.getDescription());
        existingCampaign.setAddress(updatedCampaign.getAddress());
        existingCampaign.setStartDate(updatedCampaign.getStartDate());
        existingCampaign.setEndDate(updatedCampaign.getEndDate());
        existingCampaign.setVolunteers(updatedCampaign.getVolunteers());

        Campaign savedCampaign = service.save(existingCampaign);

        return ResponseEntity.ok().body(
            new WrapperResponseDTO<>(true, "Campaign was edited.", savedCampaign)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Void>> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Campaign deleted successfully", null));
    }
}
