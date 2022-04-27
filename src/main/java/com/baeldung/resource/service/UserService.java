package com.baeldung.resource.service;

import static com.baeldung.resource.persistence.model.User.Status.ACTIVATED;
import static com.baeldung.resource.persistence.model.User.Status.REQUESTED;

import com.baeldung.resource.exceptions.ResourceNotFound;
import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.persistence.repository.IUserRepository;
import com.baeldung.resource.web.dto.KeycloakUserInfo;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private IUserRepository repository;

    private KeycloakService keycloakService;

    public User create(UserDTO dto, UUID userId) {
        User entity = UserDTOMapper.convertToEntity(dto, userId);
        entity.setStatus(REQUESTED);
        return repository.save(entity);
    }

    public User update(UserDTO dto, UUID userId) {
        User user = get(userId);
        User entity = UserDTOMapper.convertToEntity(dto, userId);
        entity.setStatus(user.getStatus());
        entity.setMembership(entity.getMembership());
        return repository.save(entity);
    }

    public User get(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFound("No Personal Info found"));
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }

    public void activateUser(UUID id) {
        User user = this.get(id);
        keycloakService.addSCCUserRole(user.getId());
        user.setStatus(ACTIVATED);
        user.setStartDate(LocalDateTime.now());
        user.setEndDate(LocalDateTime.now().plusMonths(3));
        this.repository.save(user);
    }

    public KeycloakUserInfo getKeycloakInfo(String keycloakUserId) {
        return keycloakService.getUserInfo(keycloakUserId);
    }
}
