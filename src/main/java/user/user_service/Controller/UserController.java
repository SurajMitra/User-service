package user.user_service.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.user_service.DTO.UserRequestDTO;
import user.user_service.DTO.UserResponseDTO;
import user.user_service.Service.userInterface;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    public userInterface userInterface;


    public UserController(userInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Operation(summary = "Get all users", description = "Retrieves a list of all users in the system.")
    @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    @GetMapping(value = "/all-users", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> users = userInterface.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users).getBody();
    }

    @Operation(summary = "Add a new user", description = "Adds a new user to the system with the provided details.")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @PostMapping(value = "/add-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserRequestDTO userRequest) {
        UserResponseDTO user = userInterface.addNewUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Operation(summary = "Get a user by ID", description = "Retrieves a user from the system based on their unique ID.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping(value = "/get-user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userInterface.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Delete a user by email", description = "Deletes a user from the system based on their email address.")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PatchMapping(value = "/delete-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDTO> deleteUserById(@RequestParam String email) {
        UserResponseDTO user =userInterface.deleteUserByEmail(email);
        return ResponseEntity.ok(user);
    }

}
