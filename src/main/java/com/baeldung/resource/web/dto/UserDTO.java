package com.baeldung.resource.web.dto;

import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
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

    private long id;
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
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDateTime endDate;

    public enum StatusDTO {
        REQUESTED,
        ACTIVATED,
        DEACTIVATED,
        BLOCKED;
    }

}
