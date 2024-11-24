package api.greenpeace.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import api.greenpeace.dto.response.AuthResponseDTO;
import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.Volunteer;
import api.greenpeace.service.VolunteerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin("*")
public class VolunteerController {
	
    private final api.greenpeace.service.VolunteerService service;
	private PasswordEncoder passwordEncoder;

    public VolunteerController(VolunteerService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<WrapperResponseDTO<List<AuthResponseDTO>>> getAllVolunteers() {
        // Recupera todos os voluntários do serviço
        List<Volunteer> volunteers = service.findAll();

        // Mapeia os voluntários para AuthResponseDTO
        List<AuthResponseDTO> volunteerDTOs = volunteers.stream().map(volunteer -> 
            new AuthResponseDTO(
                volunteer.getId(),
                volunteer.getName(),
                volunteer.getCpf(),
                volunteer.getRg(),
                volunteer.getEndereco(),
                volunteer.getBirth(),
                volunteer.getEmail(),
                volunteer.getPhone(),
                volunteer.getSkills()
            )
        ).toList();

        // Retorna a lista de AuthResponseDTOs encapsulada em WrapperResponseDTO
        return ResponseEntity.ok(
            new WrapperResponseDTO<>(
                true, 
                "Volunteers retrieved successfully", 
                volunteerDTOs
            )
        );
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
    public ResponseEntity<WrapperResponseDTO<AuthResponseDTO>> createVolunteer(@RequestBody Volunteer volunteer) {
    	
    	Volunteer volunteerExist = service.findByName(volunteer.getName());
    	
    	if(volunteerExist != null) return ResponseEntity.status(403).body(new WrapperResponseDTO<>(false, "Volunteer already exists", null));
    	
    	volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
    	
        Volunteer savedVolunteer = service.save(volunteer);
        
        AuthResponseDTO registeredData = new AuthResponseDTO(
            savedVolunteer.getId(),
            savedVolunteer.getName(),
            savedVolunteer.getCpf(),
            savedVolunteer.getRg(),
            savedVolunteer.getEndereco(),
            savedVolunteer.getBirth(),
            savedVolunteer.getEmail(),
            savedVolunteer.getPhone(),
            savedVolunteer.getSkills()
        );
        
        return ResponseEntity.ok(new WrapperResponseDTO<>(true, "Volunteer created successfully", registeredData));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Volunteer>> update(
        @PathVariable String id,
        @RequestBody Volunteer updatedVolunteer
    ) {
        // Busca o voluntário existente pelo ID
        Volunteer existingVolunteer = service.findById(id);

        if (existingVolunteer == null) {
            return ResponseEntity.status(404)
                .body(new WrapperResponseDTO<>(false, "Volunteer not found", null));
        }

        // Busca um voluntário pelo nome
        Volunteer existingVolunteerByName = service.findByName(updatedVolunteer.getName());

        // Verifica se já existe um voluntário com o mesmo nome e ID diferente
        if (existingVolunteerByName != null && !existingVolunteerByName.getId().equals(existingVolunteer.getId())) {
            return ResponseEntity.status(403)
                .body(new WrapperResponseDTO<>(false, "Volunteer with this name already exists", null));
        }

        // Atualiza os campos do voluntário
        existingVolunteer.setName(updatedVolunteer.getName());
        existingVolunteer.setCpf(updatedVolunteer.getCpf());
        existingVolunteer.setRg(updatedVolunteer.getRg());
        existingVolunteer.setEndereco(updatedVolunteer.getEndereco());
        existingVolunteer.setBirth(updatedVolunteer.getBirth());
        existingVolunteer.setPhone(updatedVolunteer.getPhone());
        existingVolunteer.setEmail(updatedVolunteer.getEmail());
        existingVolunteer.setSkills(updatedVolunteer.getSkills());

        // Salva as mudanças
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