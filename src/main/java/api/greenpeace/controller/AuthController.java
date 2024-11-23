package api.greenpeace.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.greenpeace.dto.request.AuthRequestDTO;
import api.greenpeace.dto.response.AuthResponseDTO;
import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.Volunteer;
import api.greenpeace.model.repository.VolunteerRepository;

@RestController
@RequestMapping(path="/api/auth")
@CrossOrigin("*")
public class AuthController {
	
	private VolunteerRepository repo;//var para manipular a dependencia MongoRepositoryORM
	private PasswordEncoder passwordEncoder;
	
    //dando a injeção de dependencia nas depencias necessarias..
	@Autowired
	public AuthController(VolunteerRepository repositoryParam, PasswordEncoder passwordEncoder) {
		 this.repo = repositoryParam;
		 this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping(value = { "", "/" })
	public WrapperResponseDTO<AuthResponseDTO> login(@RequestBody AuthRequestDTO login) {
	    Volunteer volunteerFound = this.repo.findByEmail(login.getEmail());

	    if (volunteerFound != null) {
	    	
	        if (passwordEncoder.matches(login.getSenha(), volunteerFound.getPassword())) {
	        	
	            AuthResponseDTO loginData = new AuthResponseDTO(
	                volunteerFound.getId(),
	                volunteerFound.getName(),
	                volunteerFound.getCpf(),
	                volunteerFound.getRg(),
	                volunteerFound.getEndereco(),
	                volunteerFound.getBirth(),
	                volunteerFound.getEmail(),
	                volunteerFound.getPhone(),
	                volunteerFound.getSkills()
	            );

	            return new WrapperResponseDTO<>(
	                true,
	                "Autenticado em " + (new Date()).getTime(),
	                loginData
	            );
	        } else {
	            return new WrapperResponseDTO<>(
	                false,
	                "Senha inválida.",
	                null
	            );
	        }
	    } else {
	        return new WrapperResponseDTO<>(
	            false,
	            "Esse email não é utilizado.",
	            null
	        );
	    }
	}


	
}
