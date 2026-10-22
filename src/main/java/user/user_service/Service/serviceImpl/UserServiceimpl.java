package user.user_service.Service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import user.user_service.DTO.RolesResponseDTO;
import user.user_service.DTO.UserRequestDTO;
import user.user_service.DTO.UserResponseDTO;
import user.user_service.Entity.roles;
import user.user_service.Entity.users;
import user.user_service.Entity.users_credentials;
import user.user_service.Exception.EmailAlreadyExistsException;
import user.user_service.Repository.RolesRepository;
import user.user_service.Repository.UserRepository;
import user.user_service.Repository.UsersCredentialRepository;
import user.user_service.Service.userInterface;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements userInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersCredentialRepository usersCredentialRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> mapToUserResponseDTO(user,mapToRolesResponseDTO(user.getRoles()))
                ).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO addNewUser(UserRequestDTO userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        users user = new users();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setAge(userRequest.getAge());
        user.setGender(userRequest.getGender());
        user.setStatus('A');
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        roles roles = rolesRepository.findById(3L).orElseThrow(() -> new RuntimeException("Role with ID 2 not found"));// when a new user is created it will automatically assign it to user role.
        user.setRoles(roles);
        users savedUser = userRepository.save(user);

        users_credentials usersCredentials = new users_credentials();
        usersCredentials.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        usersCredentials.setUser(user);
        usersCredentials.setEnabled(true);
        usersCredentials.setAccountLocked(false);
        usersCredentials.setFailedLoginAttempts(0);
        usersCredentials.setCreatedAt(LocalDateTime.now());
        usersCredentials.setUpdatedAt(LocalDateTime.now());
        usersCredentialRepository.save(usersCredentials);
        return mapToUserResponseDTO(savedUser, mapToRolesResponseDTO(roles));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return mapToUserResponseDTO(user, mapToRolesResponseDTO(user.getRoles()));
    }

    @Override
    public UserResponseDTO deleteUserByEmail(String email) {
        users user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus('I');
        user.setUpdatedAt(LocalDateTime.now());
        users updatedUser = userRepository.save(user);
        return mapToUserResponseDTO(updatedUser, mapToRolesResponseDTO(updatedUser.getRoles()));
    }


    private UserResponseDTO mapToUserResponseDTO(users user, RolesResponseDTO rolesResponseDTO) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getGender(),
                rolesResponseDTO,
                user.getStatus(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString()
        );
    }

    private RolesResponseDTO mapToRolesResponseDTO(roles role) {
        return new RolesResponseDTO(
                role.getId(),
                role.getRole_name(),
                role.getRole_code(),
                role.getRole_status()
        );
    }
}
