package com.alberto.helloworld.controller;

import com.alberto.helloworld.dto.MessageDTO;
import com.alberto.helloworld.dto.UserDTO;
import com.alberto.helloworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PutMapping("/hello/{username}")
    public ResponseEntity<UserDTO> createUser(@PathVariable("username") String username,
                                        @RequestBody UserDTO user) {
        LocalDate dateOfBirth = user.getDateOfBirth();
        if (dateOfBirth.isAfter(LocalDate.now())|| !username.matches("[a-zA-Z]+")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = UserDTO.builder()
                .username(username)
                .dateOfBirth(dateOfBirth)
                .build();
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hello/{username}")
    public ResponseEntity<MessageDTO> isUsersBirthday(@PathVariable("username") String username) {
        MessageDTO message = MessageDTO.builder().message(
                userService.isUsersBirthday(username)).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
