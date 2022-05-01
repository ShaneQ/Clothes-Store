package com.baeldung.resource.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeDTO {

    private Long id;
    private String name;
}
