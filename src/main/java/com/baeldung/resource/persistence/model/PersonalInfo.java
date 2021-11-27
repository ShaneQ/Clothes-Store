package com.baeldung.resource.persistence.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PERSONAL_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private String userId;

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

}


