package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.Image;
import com.baeldung.resource.web.dto.ImageDTO;

public class ImageDTOMapper {

    public static ImageDTO convertToDto(Image entity) {
        return ImageDTO.builder()
                .id(entity.getId())
                .url(entity.getPath())
                .build();
    }
}
