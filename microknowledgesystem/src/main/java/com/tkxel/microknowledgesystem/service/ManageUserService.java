package com.tkxel.microknowledgesystem.service;

import com.tkxel.microknowledgesystem.models.UserModel;

import java.util.Optional;

public interface ManageUserService {

    UserModel createUser(UserModel userObject);
    boolean authenticateUserId(String userId);
    Optional<UserModel> getUserById(String userId);
    UserModel updateUserIfExists(UserModel userObject);
}
