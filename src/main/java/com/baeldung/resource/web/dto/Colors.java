package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.Color;
import java.util.Arrays;

public class Colors {

    public static boolean equals(Color color, String[] filters) {
        return Arrays.stream(filters).anyMatch(color.getId().toString()::equals);
    }
}
