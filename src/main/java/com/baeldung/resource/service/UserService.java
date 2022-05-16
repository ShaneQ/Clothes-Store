package com.baeldung.resource.service;

import static com.baeldung.resource.persistence.model.User.Status.REQUESTED;

import com.baeldung.resource.exceptions.ResourceNotFound;
import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.persistence.repository.IUserRepository;
import com.baeldung.resource.web.dto.KeycloakUserInfo;
import com.baeldung.resource.web.dto.Role;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.dto.UserManagementDTO;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private IUserRepository repository;

    private KeycloakService keycloakService;

    public User create(UserDTO dto, UUID userId) {
        User entity = UserDTOMapper.convertToEntity(dto, userId);
        entity.setStatus(REQUESTED);

        User user = repository.save(entity);
        keycloakService.addRole(userId, Role.SCC_USER_ROLE);
        return user;
    }

    public User update(UserDTO dto, UUID userId) {
        User user = get(userId);
        User entity = UserDTOMapper.convertToEntity(dto, userId);
        entity.setStatus(user.getStatus());
        entity.setMembership(entity.getMembership());
        entity.setStartDate(entity.getStartDate());
        entity.setEndDate(entity.getEndDate());
        return repository.save(entity);
    }

    public User get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("No Personal Info found"));
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }

    public KeycloakUserInfo getKeycloakInfo(UUID keycloakUserId) {
        return keycloakService.getUserInfo(keycloakUserId);
    }

    public User updateUserSettings(UUID userId, UserManagementDTO dto) {
        User user = get(userId);

        keycloakService.addRole(userId, Role.SCC_USER_ROLE);
        switch (dto.getStatus()) {
            case ACTIVATED:
                if (dto.getMembership() == 1 || dto.getMembership() == 2) {
                    try {
                        keycloakService.addRole(userId, Role.SCC_ACTIVE_MEMBERSHIP);
                    } catch (Exception ex) {
                        log.error("Exception occurred when communicating with keycloak via the client", ex);
                        throw ex;
                    }
                }
                break;
            case REQUESTED:
            case BLOCKED:
            case DEACTIVATED:
                keycloakService.removeRole(userId, Role.SCC_ACTIVE_MEMBERSHIP);
                break;
        }
        user.setMembership(dto.getMembership());
        user.setEndDate(dto.getEndDate());
        user.setStartDate(dto.getStartDate());
        user.setStatus(User.Status.valueOf(dto.getStatus().name()));
        return this.repository.save(user);
    }
}
