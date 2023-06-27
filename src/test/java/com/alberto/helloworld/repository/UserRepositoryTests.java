package com.alberto.helloworld.repository;

import com.alberto.helloworld.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserRepositoryTests {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findUserByUsername_UserExists_ReturnsUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("john");
        when(userRepository.findUserByUsername("john")).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findUserByUsername("john");

        assertEquals(Optional.of(user), result);
    }

    @Test
    void findUserByUsername_UserDoesNotExist_ReturnsEmptyOptional() {
        when(userRepository.findUserByUsername("john")).thenReturn(Optional.empty());

        Optional<User> result = userRepository.findUserByUsername("john");

        assertEquals(Optional.empty(), result);
    }

}