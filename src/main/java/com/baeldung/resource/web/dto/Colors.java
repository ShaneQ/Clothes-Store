package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.Color;
import com.baeldung.resource.persistence.model.ProductSize;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Colors {

    public static boolean equals(Color color, String[] filters) {
        return Arrays.stream(filters).anyMatch(color.getId().toString()::equals);
    }
}
