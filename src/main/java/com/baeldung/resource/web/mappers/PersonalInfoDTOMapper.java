package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.PersonalInfo;
import com.baeldung.resource.web.dto.PersonalInfoDTO;

public class PersonalInfoDTOMapper {

    public static PersonalInfo convertToEntity(PersonalInfoDTO dto, String userId) {
        return PersonalInfo.builder()
                .id(dto.getId())
                .userId(userId)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dob(dto.getDateOfBirth())
                .city(dto.getCity())
                .country(dto.getCountry())
                .eirCode(dto.getEirCode())
                .addressLineOne(dto.getAddressLineOne())
                .addressLineTwo(dto.getAddressLineTwo())
                .build();
    }

    public static PersonalInfoDTO convertToDto(PersonalInfo entity) {
        return PersonalInfoDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDob())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .country(entity.getCountry())
                .city(entity.getCity())
                .eirCode(entity.getEirCode())
                .addressLineOne(entity.getAddressLineOne())
                .addressLineTwo(entity.getAddressLineTwo())
                .build();
    }
}
