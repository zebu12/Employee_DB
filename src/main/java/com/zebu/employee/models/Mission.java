package com.zebu.employee.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jean-pierre salum
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mission {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalDate period;

    @ManyToMany(mappedBy = "missions")
    private List<Employee> employees;
}
