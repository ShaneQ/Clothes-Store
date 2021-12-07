package com.baeldung.resource.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    TODO_IMAGE("second-closet-club-images-local");
    private final String bucketName;
}
