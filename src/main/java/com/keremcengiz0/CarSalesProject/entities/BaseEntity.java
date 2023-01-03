package com.keremcengiz0.CarSalesProject.entities;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Data
public class BaseEntity {

    private User user;

    public Optional<String> createdBy(User user) throws Exception {
        if(user.getAdverts().isEmpty()) {
            throw new Exception("User not found!");
        }
        return Optional.of(" Created by " + user.getUserName());
    }

    public Optional<String> createdDate() throws Exception {
        return Optional.of(" Created date " + LocalDate.now().toString());
    }

    public Optional<String> updatedBy(User user) throws Exception {
        if(user.getAdverts().isEmpty()) {
            throw new Exception("User not found!");
        }
        return Optional.of(" Updated by " + user.getUserName());
    }

    public Optional<String> updatedDate() throws Exception {
        return Optional.of(" Updated date " + LocalDate.now().toString());
    }
}
