package user.user_service.Service;

import user.user_service.DTO.UserRequestDTO;
import user.user_service.DTO.UserResponseDTO;

import java.util.List;

public interface userInterface {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO addNewUser(UserRequestDTO userRequest);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO deleteUserByEmail(String email);
}
