package com.alberto.helloworld.controller;

import com.alberto.helloworld.dto.MessageDTO;
import com.alberto.helloworld.dto.UserDTO;
import com.alberto.helloworld.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_ValidData_ReturnsNoContent() {
        String username = "john";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .dateOfBirth(dateOfBirth)
                .build();

        ResponseEntity<UserDTO> expectedResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        ResponseEntity<UserDTO> actualResponse = userController.createUser(username, userDTO);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void createUser_InvalidData_ReturnsBadRequest() {
        String username = "john123";
        LocalDate dateOfBirth = LocalDate.of(2025, 1, 1);
        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .dateOfBirth(dateOfBirth)
                .build();

        ResponseEntity<UserDTO> expectedResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<UserDTO> actualResponse = userController.createUser(username, userDTO);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    void isUsersBirthday_ValidUsername_ReturnsOk() {
        String username = "john";
        when(userService.isUsersBirthday(username)).thenReturn("Hello, john! Happy birthday!");

        ResponseEntity<MessageDTO> expectedResponse = new ResponseEntity<>(
                MessageDTO.builder().message("Hello, john! Happy birthday!").build(), HttpStatus.OK);

        ResponseEntity<MessageDTO> actualResponse = userController.isUsersBirthday(username);

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody().getMessage(), actualResponse.getBody().getMessage());
    }
}