package api.greenpeace.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import api.greenpeace.dto.service.response.UploadDTO;

@Service
public class FileImageService {

	private Path filePath;
	
	public FileImageService() {
		
		this.filePath = Paths.get("uploads/").toAbsolutePath().normalize();
		
	}	
	
	public UploadDTO upload(MultipartFile image) {
	    try {
	        String fileType = image.getContentType();
	        if (fileType == null) return new UploadDTO("The image was not sended", false);
	        
	        if (!fileType.equals("image/jpeg") && !fileType.equals("image/png")) {
	            return new UploadDTO("The image format is invalid send .jpg, .png, .jpeg instead", false);
	        }
	   
	        String uniqueId = UUID.randomUUID().toString();
	        String imageFileName = uniqueId + ".png";  
	        

	        Path pathFinalTarget = filePath.resolve(imageFileName);

	        image.transferTo(pathFinalTarget);
	
	        return new UploadDTO(ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(imageFileName).toUriString(), true);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getStackTrace().toString());
	        return new UploadDTO("Error on saving image: " + e.getMessage(), false);
	    }
	}
	
}
