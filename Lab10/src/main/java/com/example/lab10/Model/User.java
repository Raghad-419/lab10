package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints ="role='JOB_SEEKER' or role= 'EMPLOYER' AND (age>=22) AND email LIKE '%_@__%.__%' And LENGTH(name) >= 5 AND LENGTH(password) >= 6")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(12) not null")
    @NotEmpty(message = "Empty name")
    @Size(min = 5 ,message = "Length must be more than 4 characters. ")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabetic characters.")
    private String name;
    @Column(columnDefinition = "varchar(25) not null unique")
    @Email(message = "enter valid email")
    @NotEmpty(message = "Empty email")
    private String email;
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "Empty password")
    @Size(min = 6, message = "Password must be at least 6 characters long.")
    private String password;
    @Column(columnDefinition = "int not null")
    @Min(value = 22 ,message = "Age must be more than 21")
    private Integer age ;
    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = "Empty role")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER",message = "Role Must be either 'JOB_SEEKER' or 'EMPLOYER' only.")
    private String role;
}
