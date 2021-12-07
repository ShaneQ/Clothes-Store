package com.baeldung.resource.service;

import com.baeldung.resource.persistence.model.Image;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface imageService {
    Image saveTodo(String title, String description, MultipartFile file);

    byte[] downloadTodoImage(Long id);

    List<Image> getAllTodos();
}