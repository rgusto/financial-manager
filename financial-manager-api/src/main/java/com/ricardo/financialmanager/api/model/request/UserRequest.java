package com.ricardo.financialmanager.api.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;

}
