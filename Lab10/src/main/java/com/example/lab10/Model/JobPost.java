package com.example.lab10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "LENGTH(title) >= 5 AND salary>0")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(12) not null")
    @NotEmpty(message = "Empty title")
    @Size(min = 5 ,message = "Length must be more than 4 characters.")
    private String title;
    @Column(columnDefinition = "varchar(225) not null")
    @NotEmpty(message = "Empty description")
    private String description;
    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Empty location")
    private String location;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "Empty salary")
    @Positive(message = "Salary must be Positive")
    private Integer salary ;
    @Column(columnDefinition = "datetime default CURRENT_DATE")
    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate postingDate;

    // to Set default value for postingDate before persisting
    @PrePersist
    private void onCreate() {
        if (this.postingDate == null) {
            this.postingDate = LocalDate.now();
        }
    }
}




