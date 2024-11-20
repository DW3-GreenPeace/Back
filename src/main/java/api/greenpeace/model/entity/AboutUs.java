package api.greenpeace.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("about")
public class AboutUs {
	
	@Id
	public String id;
	public String text;
	public String image;
	
	public AboutUs(String text, String image) {
		super();
		this.text = text;
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
