package com.keremcengiz0.CarSalesProject.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String telNo;
}
