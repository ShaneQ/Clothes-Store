package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.ProductCategory;
import com.baeldung.resource.persistence.model.Season;
import java.util.Arrays;

public class Categories {

    public static boolean equals(ProductCategory category, String[] filters) {
        return Arrays.stream(filters).anyMatch(category.getId().toString()::equals);
    }
}
