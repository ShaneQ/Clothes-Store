package com.baeldung.resource.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date dateOfBirth;
    private String country;
    private String city;
    private String eirCode;
    private String addressLineOne;
    private String addressLineTwo;
    private StatusDTO status;
    private int membership;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;
    private Integer bookingAllowanceMonthly;
    private Integer bookingAllowanceRemainingMonthly;

    public enum StatusDTO {
        REQUESTED,
        ACTIVATED,
        DEACTIVATED,
        BLOCKED;
    }
}
