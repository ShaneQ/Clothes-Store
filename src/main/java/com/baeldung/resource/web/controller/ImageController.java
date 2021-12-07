package com.baeldung.resource.web.controller;

import com.baeldung.resource.persistence.model.Image;
import com.baeldung.resource.service.imageService;
import com.baeldung.resource.web.dto.ImageDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/admin/image")
@AllArgsConstructor
public class ImageController {

    imageService service;

    @GetMapping
    public ResponseEntity<List<Image>> getTodos() {
        return new ResponseEntity<>(service.getAllTodos(), HttpStatus.OK);
    }

    @PostMapping(
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageDTO> saveTodo(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(service.saveImage(file), HttpStatus.OK);
    }

    @GetMapping(value = "{id}/image/download")
    public byte[] downloadTodoImage(@PathVariable("id") Long id) {
        return service.downloadTodoImage(id);
    }
}
