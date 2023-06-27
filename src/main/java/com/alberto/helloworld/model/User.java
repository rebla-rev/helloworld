package com.alberto.helloworld.model;

import com.alberto.helloworld.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(name = "users")
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private UUID id;

    @NotNull
    @EqualsAndHashCode.Include
    private String username;

    @NotNull
    @EqualsAndHashCode.Include
    private LocalDate dateOfBirth;
}
