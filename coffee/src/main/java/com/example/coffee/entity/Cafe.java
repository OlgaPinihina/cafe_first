package com.example.coffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@NoArgsConstructor
public class Cafe {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    @NotNull(message = "The name of the cafe must not be empty")
    @NotBlank(message = "The name of the cafe should not consist only of spaces")
    @Size(min = 3, max = 25, message = "The size should be between 2 and 25")
    private String name;


}
