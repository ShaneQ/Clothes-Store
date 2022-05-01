package com.baeldung.resource.web.dto;

import com.baeldung.resource.web.dto.UserDTO.StatusDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementDTO {

    private StatusDTO status;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    private Integer membership;
}
