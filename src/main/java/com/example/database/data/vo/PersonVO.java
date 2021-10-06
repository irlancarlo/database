package com.example.database.data.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonVO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;


}