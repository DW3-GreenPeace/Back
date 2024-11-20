package api.greenpeace.dto.service.response;

public class UploadDTO {

	private String message;
	private boolean bool;
	
	public UploadDTO(String message, boolean bool) {
		super();
		this.message = message;
		this.bool = bool;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	
	
}
