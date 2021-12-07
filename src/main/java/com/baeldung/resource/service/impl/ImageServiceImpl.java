package com.baeldung.resource.service.impl;

import static org.apache.http.entity.ContentType.IMAGE_BMP;
import static org.apache.http.entity.ContentType.IMAGE_GIF;
import static org.apache.http.entity.ContentType.IMAGE_JPEG;
import static org.apache.http.entity.ContentType.IMAGE_PNG;

import com.baeldung.resource.persistence.model.Image;
import com.baeldung.resource.persistence.repository.IImageRepository;
import com.baeldung.resource.service.FileStore;
import com.baeldung.resource.service.imageService;
import com.baeldung.resource.spring.properties.SCCBucketProperties;
import com.baeldung.resource.web.dto.ImageDTO;
import com.baeldung.resource.web.mappers.ImageDTOMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements imageService {
    private final FileStore fileStore;
    private final IImageRepository repository;
    private SCCBucketProperties properties;
    public ImageDTO saveImage(MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
        UUID s3BucketFolder = UUID.randomUUID();
        String path = String.format("%s/%s", properties.getName(), s3BucketFolder);
        String fileName = String.format("%s", file.getOriginalFilename()).replace(" ","");
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        Image todo = Image.builder()
                .path(properties.getUrl()+"/"+s3BucketFolder+"/"+fileName )
                .fileName(fileName)
                .build();
        Image save = repository.save(todo);
        Image image = repository.findById(save.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
        return ImageDTOMapper.convertToDto(image);
    }

    @Override
    public byte[] downloadTodoImage(Long id) {
        Image todo = repository.findById(id).get();
        return fileStore.download(todo.getPath(), todo.getFileName());
    }

    @Override
    public List<Image> getAllTodos() {
        List<Image> todos = new ArrayList<>();
        repository.findAll().forEach(todos::add);
        return todos;
    }
}
