package api.greenpeace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.AboutUs;
import api.greenpeace.service.AboutUsService;

@RestController
@RequestMapping("/api/about")
@CrossOrigin("*")
public class AboutUsController {

    @Autowired
    private AboutUsService aboutUsService;

    @PostMapping
    public ResponseEntity<WrapperResponseDTO<AboutUs>> createAboutUs(
            @RequestParam("text") String text,
            @RequestParam("image") MultipartFile image) {

        AboutUs aboutUs = aboutUsService.createAboutUs(text, image);
        WrapperResponseDTO<AboutUs> response = new WrapperResponseDTO<>(
                true, "AboutUs entry created successfully.", aboutUs);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<WrapperResponseDTO<Iterable<AboutUs>>> getAllAboutUs() {
        Iterable<AboutUs> aboutUsList = aboutUsService.getAll();
        WrapperResponseDTO<Iterable<AboutUs>> response = new WrapperResponseDTO<>(
                true, "AboutUs entries retrieved successfully.", aboutUsList);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<AboutUs>> updateAboutUs(
            @PathVariable String id,
            @RequestParam(value = "text", required = false) String newText,
            @RequestParam(value = "image", required = false) MultipartFile newImage) {

        AboutUs updatedAboutUs = aboutUsService.updateAboutUs(id, newText, newImage);
        WrapperResponseDTO<AboutUs> response = new WrapperResponseDTO<>(
                true, "AboutUs entry updated successfully.", updatedAboutUs);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<String>> deleteAboutUs(@PathVariable String id) {
        aboutUsService.deleteAboutUs(id);
        WrapperResponseDTO<String> response = new WrapperResponseDTO<>(
                true, "AboutUs entry deleted successfully.", null);
        return ResponseEntity.ok(response);
    }
}