package api.greenpeace.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "donations")
public class Donation {
    @Id
    private String id;
    private String nameDonor;
    private String date;
    private Double donation;
    
	public Donation(String id, String nameDonor, String date, Double donation) {
		super();
		this.id = id;
		this.nameDonor = nameDonor;
		this.date = date;
		this.donation = donation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameDonor() {
		return nameDonor;
	}

	public void setNameDonor(String nameDonor) {
		this.nameDonor = nameDonor;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getDonation() {
		return donation;
	}

	public void setDonation(Double donation) {
		this.donation = donation;
	}

	
	
    
}