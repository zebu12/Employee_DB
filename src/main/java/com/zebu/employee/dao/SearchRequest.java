package com.zebu.employee.dao;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jean-pierre Salum
 */

@Getter
@Setter
public class SearchRequest {

    private String firstName;
    private String lastName;
    private String email;
}
