package com.tkxel.microknowledgesystem.service;

import com.tkxel.microknowledgesystem.dtos.UserDto;
import com.tkxel.microknowledgesystem.models.MicroKnowledge;

import java.util.List;

public interface ListMicroKnowledgeOfUserService {

    UserDto searchUserByName(String userName);
    List<MicroKnowledge> listMicroknowledgeOfUser(String userId);
}
