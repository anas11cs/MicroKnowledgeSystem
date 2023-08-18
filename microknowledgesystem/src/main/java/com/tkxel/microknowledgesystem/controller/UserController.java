package com.tkxel.microknowledgesystem.controller;

import com.tkxel.microknowledgesystem.dtos.UserDto;
import com.tkxel.microknowledgesystem.models.UserModel;
import com.tkxel.microknowledgesystem.service.ListMicroKnowledgeOfUserService;
import com.tkxel.microknowledgesystem.service.ManageUserService;
import com.tkxel.microknowledgesystem.service.MicroKnowledgeSystem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class UserController {
    @Autowired
    private ManageUserService manageUserService;
    @Autowired
    private ListMicroKnowledgeOfUserService listMicroKnowledgeOfUserService;
    @Autowired
    private MicroKnowledgeSystem microKnowledgeSystem;
    @PostMapping("/createuser")
    public ResponseEntity<UserModel> addNewUser(@RequestBody UserModel userObject){
       log.info("User Id Recieved:"+userObject.getId());
       UserModel userModel = manageUserService.createUser(userObject);
       return (userModel != null) ?  new ResponseEntity<>(userModel,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/searchuser")
    public ResponseEntity<UserDto> getuserbyname(@RequestParam("userName") String userName) {
        log.info("User Name received for Search is:"+userName);
        UserDto userModel = listMicroKnowledgeOfUserService.searchUserByName(userName);
        return (userModel != null && userModel.getId()!=null) ?  new ResponseEntity<>(userModel,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel userObject){
       return (userObject.getId() != null && userObject.getUserPassword() !=null && microKnowledgeSystem.logIn(userObject.getId(), userObject.getUserPassword())) ?  new ResponseEntity<>("Login Successful!", HttpStatus.OK) : new ResponseEntity<>("Login Failed!", HttpStatus.FORBIDDEN);
    }
}
