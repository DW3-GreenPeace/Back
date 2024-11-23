package api.greenpeace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import api.greenpeace.dto.response.WrapperResponseDTO;
import api.greenpeace.model.entity.Topic;
import api.greenpeace.service.TopicService;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin("*")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<WrapperResponseDTO<Topic>> createTopic(
            @RequestParam("text") String text,
            @RequestParam("title") String title,
            @RequestParam("image") MultipartFile image) {

        Topic topic = topicService.createTopic(text, title, image);
        WrapperResponseDTO<Topic> response = new WrapperResponseDTO<>(
                true, "Topic created successfully.", topic);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<WrapperResponseDTO<Iterable<Topic>>> getAllTopics() {
        Iterable<Topic> topics = topicService.getAllTopics();
        WrapperResponseDTO<Iterable<Topic>> response = new WrapperResponseDTO<>(
                true, "Topics retrieved successfully.", topics);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<Topic>> updateTopic(
            @PathVariable String id,
            @RequestParam(value = "text", required = false) String newText,
            @RequestParam(value = "title", required = false) String newTitle,
            @RequestParam(value = "image", required = false) MultipartFile newImage) {

        Topic updatedTopic = topicService.updateTopic(id, newText, newTitle, newImage);
        
        WrapperResponseDTO<Topic> response = new WrapperResponseDTO<>(
                true, "Topic updated successfully.", updatedTopic);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponseDTO<String>> deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
        WrapperResponseDTO<String> response = new WrapperResponseDTO<>(
                true, "Topic deleted successfully.", null);
        return ResponseEntity.ok(response);
    }
}