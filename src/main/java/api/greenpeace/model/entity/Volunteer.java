package api.greenpeace.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Document(collection = "volunteers")
public class Volunteer {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String rg;
    private String endereco;
    private int age;
    private String email;
    private List<String> skills;
    
	public Volunteer(String id, String name, String cpf, String rg, String endereco, int age, String email,
			List<String> skills) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.age = age;
		this.email = email;
		this.skills = skills;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

    
    
    
}