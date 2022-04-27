package com.baeldung.resource.web.mappers;

import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.dto.UserDTO.StatusDTO;
import java.util.UUID;

public class UserDTOMapper {

    public static User convertToEntity(UserDTO dto, UUID userId) {
        return User.builder()
                .id(userId)
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
                .membership(dto.getMembership())
                .build();
    }

    public static UserDTO convertToDto(User entity) {
        return UserDTO.builder()
                .id(entity.getId().toString())
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
                .status(StatusDTO.valueOf(entity.getStatus().name()))
                .membership(entity.getMembership())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
