package api.greenpeace.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.Volunteer;
import api.greenpeace.service.VolunteerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    private final api.greenpeace.service.VolunteerService service;

    public VolunteerController(VolunteerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<WrapperResponseDTO<List<Volunteer>>> getAllVolunteers() {
        List<Volunteer> volunteers = service.findAll();
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Volunteers retrieved successfully", volunteers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Volunteer>> getVolunteerById(@PathVariable String id) {
        Volunteer volunteer = service.findById(id);
        if (volunteer == null) {
            return ResponseEntity.status(404)
                    .body(new WrapperResponseDTO<>(false, "Volunteer not found", null));
        }
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Volunteer retrieved successfully", volunteer));
    }

    @PostMapping
    public ResponseEntity<WrapperResponseDTO<Volunteer>> createVolunteer(@RequestBody Volunteer volunteer) {
    	
    	Volunteer volunteerExist = service.findByName(volunteer.getName());
    	
    	if(volunteerExist != null) return ResponseEntity.status(403).body(new WrapperResponseDTO<>(false, "Volunteer already exists", null));
    	
        Volunteer savedVolunteer = service.save(volunteer);
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Volunteer created successfully", savedVolunteer));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Volunteer>> update(
        @PathVariable String id,
        @RequestBody Volunteer updatedVolunteer
    ) {
        Volunteer existingVolunteer = service.findById(id);

        if (existingVolunteer == null) {
            return ResponseEntity.status(404)
                .body(new WrapperResponseDTO<>(false, "Volunteer not found", null));
        }

        // Atualizando os campos restantes
        existingVolunteer.setName(updatedVolunteer.getName());
        existingVolunteer.setCpf(updatedVolunteer.getCpf());
        existingVolunteer.setRg(updatedVolunteer.getRg());
        existingVolunteer.setEndereco(updatedVolunteer.getEndereco());
        existingVolunteer.setAge(updatedVolunteer.getAge());
        existingVolunteer.setEmail(updatedVolunteer.getEmail());
        existingVolunteer.setSkills(updatedVolunteer.getSkills());

        Volunteer savedVolunteer = service.save(existingVolunteer);

        return ResponseEntity.ok().body(
            new WrapperResponseDTO<>(true, "Volunteer was edited.", savedVolunteer)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Void>> deleteVolunteer(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Volunteer deleted successfully", null));
    }
}