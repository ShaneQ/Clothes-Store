package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.Color;
import com.baeldung.resource.persistence.model.Season;
import java.util.Arrays;

public class Seasons {

    public static boolean equals(Season season, String[] filters) {
        return Arrays.stream(filters).anyMatch(season.getId().toString()::equals);
    }
}
