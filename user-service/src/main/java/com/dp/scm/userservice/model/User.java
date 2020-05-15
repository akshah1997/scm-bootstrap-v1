package com.dp.scm.userservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
public class User {

    private long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;
    private String middleName;
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 200)
    private String email;
    private String phoneNo;
    private Date dob;
}
