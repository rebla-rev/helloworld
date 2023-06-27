package com.alberto.helloworld.service;

import com.alberto.helloworld.dto.UserDTO;
import com.alberto.helloworld.model.User;
import com.alberto.helloworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserDTO userDTO) {
        Optional<User> user = userRepository.findUserByUsername(userDTO.getUsername());
        if (user.isPresent()){
            user.get().setDateOfBirth(userDTO.getDateOfBirth());
            userRepository.save(user.get());
        }
        else{
            User newUser = User.builder()
                    .username(userDTO.getUsername())
                    .dateOfBirth(userDTO.getDateOfBirth())
                    .build();
            userRepository.save(newUser);
        }
    }
    public String isUsersBirthday(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()){
            if (user.get().getDateOfBirth().getDayOfMonth() == java.time.LocalDate.now().getDayOfMonth() &&
                    user.get().getDateOfBirth().getMonth() == java.time.LocalDate.now().getMonth()){
                return "Hello, " + username + "! Happy birthday!";
            }
            else{
                int days = user.get().getDateOfBirth().getDayOfYear() - java.time.LocalDate.now().getDayOfYear();
                if (days < 0){
                    days = 365 + days;
                }
                return "Hello, " + username + "! Your birthday is in " + days + " day(s)";
            }
        }
        else{
            return "Hello, " + username + "! You are not registered in the system";
        }
    }

}
