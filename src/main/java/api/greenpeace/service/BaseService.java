package api.greenpeace.service;

import api.greenpeace.dto.response.WrapperResponseDTO;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public class BaseService {

    protected <T> ResponseEntity<WrapperResponseDTO<T>> wrapResponse(Optional<T> data, String successMessage, String errorMessage) {
        if (data.isPresent()) {
            return ResponseEntity.ok(new WrapperResponseDTO<>(true, successMessage, data.get()));
        } else {
            return ResponseEntity.status(404).body(new WrapperResponseDTO<>(false, errorMessage, null));
        }
    }
}
