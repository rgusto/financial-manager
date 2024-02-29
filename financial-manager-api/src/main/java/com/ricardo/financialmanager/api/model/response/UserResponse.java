package com.ricardo.financialmanager.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;

}
