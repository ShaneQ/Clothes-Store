package com.baeldung.resource.service;

import com.baeldung.resource.persistence.model.Image;
import com.baeldung.resource.web.dto.ImageDTO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface imageService {
    ImageDTO saveImage(MultipartFile file);

    byte[] downloadTodoImage(Long id);

    List<Image> getAllTodos();
}