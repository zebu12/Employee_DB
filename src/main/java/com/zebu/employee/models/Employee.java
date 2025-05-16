package com.zebu.employee.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

/**
 * @author Jean-pierre Salum
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {


    @Id
    @GeneratedValue
    private Integer Id;

    @Column(unique=true, nullable=false)
    private String identifier;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private EmployeeRole role;
}
