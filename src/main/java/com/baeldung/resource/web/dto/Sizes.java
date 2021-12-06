package com.baeldung.resource.web.dto;

import com.baeldung.resource.persistence.model.ProductSize;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sizes {

    public static boolean contains(List<ProductSize> sizes, String[] filters){
        return sizes.stream().filter(productSize -> Arrays.stream(filters).anyMatch(productSize.getSize().getId().toString()::equals)).collect(
                Collectors.toList()).size() > 0;
    }

}
