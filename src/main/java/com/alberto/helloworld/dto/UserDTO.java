package com.alberto.helloworld.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserDTO {
    private final String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate dateOfBirth;
}
