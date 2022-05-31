package com.baeldung.resource.persistence.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Date dob;
    private String country;
    private String city;
    @Column(name = "eircode")
    private String eirCode;
    private String addressLineOne;
    private String addressLineTwo;
    private LocalDate startDate;
    private LocalDate endDate;
    private int membership;

    @Enumerated(EnumType.STRING)
    private User.Status status;

    public enum Status {
        REQUESTED,
        ACTIVATED,
        DEACTIVATED,
        BLOCKED;
    }

    public String getFullName(){
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}


