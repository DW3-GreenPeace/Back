package api.greenpeace.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("topics")
public class Topic {
	
	@Id
	public String id;
	public String text;
	public String title;
	public String image;
	
	public Topic(String text, String title, String image) {
		super();
		this.text = text;	
		this.title = title;
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
