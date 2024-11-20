package api.greenpeace.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.greenpeace.dto.service.response.UploadDTO;
import api.greenpeace.model.entity.Topic;
import api.greenpeace.model.repository.TopicRepository;


@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private FileImageService fileImageService;

    public Topic createTopic(String text, String title, MultipartFile image) {
        UploadDTO uploadResponse = fileImageService.upload(image);

        if (!uploadResponse.isBool()) {
            throw new RuntimeException(uploadResponse.getMessage());
        }

        Topic topic = new Topic(text, title, uploadResponse.getMessage());
        return topicRepository.save(topic);
    }

    public Iterable<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic updateTopic(String id, String newText, String newTitle, MultipartFile newImage) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException("Topic not found with id: " + id);
        }

        Topic topic = optionalTopic.get();

        if (newText != null && !newText.isEmpty()) {
            topic.setText(newText);
        }

        if (newTitle != null && !newTitle.isEmpty()) {
            topic.setTitle(newTitle);
        }

        if (newImage != null) {
            UploadDTO uploadResponse = fileImageService.upload(newImage);

            if (!uploadResponse.isBool()) {
                throw new RuntimeException(uploadResponse.getMessage());
            }

            topic.setImage(uploadResponse.getMessage());
        }

        return topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        if (!topicRepository.existsById(id)) {
            throw new RuntimeException("Topic not found with id: " + id);
        }

        topicRepository.deleteById(id);
    }
}
