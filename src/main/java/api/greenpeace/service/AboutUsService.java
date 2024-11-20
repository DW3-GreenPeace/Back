package api.greenpeace.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.greenpeace.dto.service.response.UploadDTO;
import api.greenpeace.model.entity.AboutUs;
import api.greenpeace.model.repository.AboutUsRepository;


@Service
public class AboutUsService {

    @Autowired
    private AboutUsRepository aboutUsRepository;

    @Autowired
    private FileImageService fileImageService;

    public AboutUsService(AboutUsRepository aboutUsRepository, FileImageService fileImageService) {
		
		this.aboutUsRepository = aboutUsRepository;
		this.fileImageService = fileImageService;
	}

	public AboutUs createAboutUs(String text, MultipartFile image) {
        UploadDTO uploadResponse = fileImageService.upload(image);

        if (!uploadResponse.isBool()) {
            throw new RuntimeException(uploadResponse.getMessage());
        }

        AboutUs aboutUs = new AboutUs(text, uploadResponse.getMessage());
        return aboutUsRepository.save(aboutUs);
    }

    public Iterable<AboutUs> getAll() {
        return aboutUsRepository.findAll();
    }

    public AboutUs updateAboutUs(String id, String newText, MultipartFile newImage) {
        Optional<AboutUs> optionalAboutUs = aboutUsRepository.findById(id);
        if (optionalAboutUs.isEmpty()) {
            throw new RuntimeException("AboutUs entry not found with id: " + id);
        }

        AboutUs aboutUs = optionalAboutUs.get();

        if (newText != null && !newText.isEmpty()) {
            aboutUs.setText(newText);
        }

        if (newImage != null) {
            UploadDTO uploadResponse = fileImageService.upload(newImage);

            if (!uploadResponse.isBool()) {
                throw new RuntimeException(uploadResponse.getMessage());
            }

            aboutUs.setImage(uploadResponse.getMessage());
        }

        return aboutUsRepository.save(aboutUs);
    }
    
    public void deleteAboutUs(String id) {
        if (!aboutUsRepository.existsById(id)) {
            throw new RuntimeException("AboutUs entry not found with id: " + id);
        }

        aboutUsRepository.deleteById(id);
    }
    
}
