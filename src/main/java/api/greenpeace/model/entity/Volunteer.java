package api.greenpeace.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@Document(collection = "volunteers")
public class Volunteer {
    @Id
    private String id;
    private String name;
    private String cpf;
    private String rg;
    private String endereco;
    private Date birth;
    private String email;
    private String phone;
    private String password;
    private List<String> skills;
    
	public Volunteer(String id, String name, String cpf, String rg, String endereco, Date birth, String email,
			String phone, String password, List<String> skills) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.password = password;
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
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
    
    
    
	
    
    
}