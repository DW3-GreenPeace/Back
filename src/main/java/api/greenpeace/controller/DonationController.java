package api.greenpeace.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.Donation;
import api.greenpeace.service.DonationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin("*")
public class DonationController {
    private final DonationService service;

    public DonationController(DonationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<WrapperResponseDTO<List<Donation>>> getAllDonations() {
        List<Donation> donations = service.findAll();
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Donations retrieved successfully", donations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Donation>> getDonationById(@PathVariable String id) {
        Donation donation = service.findById(id);
        if (donation == null) {
            return ResponseEntity.status(404)
                    .body(new WrapperResponseDTO<>(false, "Donation not found", null));
        }
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Donation retrieved successfully", donation));
    }

    @PostMapping
    public ResponseEntity<WrapperResponseDTO<Donation>> createDonation(@RequestBody Donation donation) {
        Donation savedDonation = service.save(donation);
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Donation created successfully", savedDonation));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Donation>> update(
        @PathVariable String id,
        @RequestBody Donation updatedDonation
    ) {
        Donation existingDonation = service.findById(id);

        if (existingDonation == null) {
            return ResponseEntity.status(404)
                .body(new WrapperResponseDTO<>(false, "Donation not found", null));
        }

        // Atualizando os campos do objeto existente
        existingDonation.setNameDonor(updatedDonation.getNameDonor());
        existingDonation.setDate(updatedDonation.getDate());
        existingDonation.setDonation(updatedDonation.getDonation());

        Donation savedDonation = service.save(existingDonation);

        return ResponseEntity.ok().body(
            new WrapperResponseDTO<>(true, "Donation was edited.", savedDonation)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Void>> deleteDonation(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Donation deleted successfully", null));
    }
}