package com.baeldung.resource.service;

import static com.baeldung.resource.persistence.model.User.Status.ACTIVATED;
import static com.baeldung.resource.persistence.model.User.Status.REQUESTED;

import com.baeldung.resource.exceptions.ResourceNotFound;
import com.baeldung.resource.persistence.model.User;
import com.baeldung.resource.persistence.repository.IUserRepository;
import com.baeldung.resource.web.dto.UserDTO;
import com.baeldung.resource.web.mappers.UserDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private IUserRepository repository;

    private KeycloakService keycloakService;

    public UserService(IUserRepository repository,
            KeycloakService keycloakService) {
        this.repository = repository;
        this.keycloakService = keycloakService;
    }

    public User create(UserDTO dto, String userId) {
        User entity = UserDTOMapper.convertToEntity(dto, userId);
        entity.setStatus(REQUESTED);
        return repository.save(entity);
    }
    public User update(UserDTO dto, String userId) {
        User user = get(dto.getId());
        User entity = UserDTOMapper.convertToEntity(dto, userId);
        entity.setStatus(user.getStatus());
        entity.setMembership(entity.getMembership());
        return repository.save(entity);
    }

    public User get(String keycloakUserId) {
        return repository.findByKeycloakUserId(keycloakUserId).orElseThrow(() -> new ResourceNotFound("No Personal Info found"));
    }

    public User get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFound("No Personal Info found"));
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }

    public void activateUser(Long id) {
        User user = this.get(id);
        keycloakService.addSCCUserRole(user.getKeycloakUserId());
        user.setStatus(ACTIVATED);
        this.repository.save(user);

    }
}
