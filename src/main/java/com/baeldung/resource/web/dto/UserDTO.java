package com.baeldung.resource.web.dto;

import com.baeldung.resource.web.dto.BookingRequestDTO.StatusDTO;
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

    public enum StatusDTO {
        REQUESTED,
        ACTIVATED,
        DEACTIVATED,
        BLOCKED;
    }

}
