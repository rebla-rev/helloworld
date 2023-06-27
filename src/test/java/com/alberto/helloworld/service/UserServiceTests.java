package com.alberto.helloworld.service;

import com.alberto.helloworld.dto.UserDTO;
import com.alberto.helloworld.model.User;
import com.alberto.helloworld.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_NewUser_CallsSave() {
        UserDTO userDTO = UserDTO.builder()
                .username("john")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        userService.createUser(userDTO);

        verify(userRepository, times(1)).findUserByUsername("john");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_ExistingUser_UpdatesDateOfBirth() {
        User existingUser = User.builder()
                .username("john")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
        when(userRepository.findUserByUsername("john")).thenReturn(Optional.of(existingUser));

        UserDTO userDTO = UserDTO.builder()
                .username("john")
                .dateOfBirth(LocalDate.of(1995, 1, 1))
                .build();

        userService.createUser(userDTO);

        verify(userRepository, times(1)).findUserByUsername("john");
        verify(userRepository, times(1)).save(existingUser);
        assertEquals(LocalDate.of(1995, 1, 1), existingUser.getDateOfBirth());
    }

    @Test
    void isUsersBirthday_UserExistsAndBirthdayToday_ReturnsHappyBirthdayMessage() {
        User user = User.builder()
                .username("john")
                .dateOfBirth(LocalDate.now())
                .build();
        when(userRepository.findUserByUsername("john")).thenReturn(Optional.of(user));

        String message = userService.isUsersBirthday("john");

        assertEquals("Hello, john! Happy birthday!", message);
    }

    @Test
    void isUsersBirthday_UserExistsAndBirthdayInFuture_ReturnsDaysUntilBirthdayMessage() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        User user = User.builder()
                .username("john")
                .dateOfBirth(tomorrow)
                .build();
        when(userRepository.findUserByUsername("john")).thenReturn(Optional.of(user));

        String message = userService.isUsersBirthday("john");

        int days = tomorrow.getDayOfYear() - LocalDate.now().getDayOfYear();
        assertEquals("Hello, john! Your birthday is in " + days + " day(s)", message);
    }

    @Test
    void isUsersBirthday_UserDoesNotExist_ReturnsUserNotRegisteredMessage() {
        when(userRepository.findUserByUsername("john")).thenReturn(Optional.empty());

        String message = userService.isUsersBirthday("john");

        assertEquals("Hello, john! You are not registered in the system", message);
    }

}