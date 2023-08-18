package com.tkxel.microknowledgesystem.service.impl;

import com.tkxel.microknowledgesystem.models.UserModel;
import com.tkxel.microknowledgesystem.repository.UserRepository;
import com.tkxel.microknowledgesystem.service.ManageUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ManageUserServiceImpl implements ManageUserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserModel createUser(UserModel userObject){
        return (!authenticateUserId(userObject.getId()) ? userRepository.save(userObject) : null);
    }
    @Override
    public boolean authenticateUserId(String userId){
        return userRepository.existsById(userId);
    }
    @Override
    public Optional<UserModel> getUserById(String userId){
        return userRepository.findById(userId);
    }
    @Override
    public UserModel updateUserIfExists(UserModel userObject){
        if(authenticateUserId(userObject.getId())){
            userRepository.save(userObject);
        }
        return null;
    }
}
