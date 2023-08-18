package com.tkxel.microknowledgesystem.service.impl;

import com.tkxel.microknowledgesystem.dtos.UserDto;
import com.tkxel.microknowledgesystem.models.Comment;
import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import com.tkxel.microknowledgesystem.models.UserModel;
import com.tkxel.microknowledgesystem.repository.MicroKnowledgeRepository;
import com.tkxel.microknowledgesystem.repository.UserRepository;
import com.tkxel.microknowledgesystem.service.ListMicroKnowledgeOfUserService;
import com.tkxel.microknowledgesystem.service.ListMicroKnowledgeOfUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Log4j2
public class ListMicroKnowledgeOfUserServiceImpl implements ListMicroKnowledgeOfUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MicroKnowledgeRepository microKnowledgeRepository;

    @Override
    @Transactional
    public UserDto searchUserByName(String userName) {
        UserModel userModel = null;
        UserDto userDto = new UserDto();
        try{
            userModel = userRepository.findByUserName(userName);
            if(userModel!=null) {
                userDto.setId(userModel.getId());
                userDto.setUserName(userModel.getUserName());
                userDto.setUserPassword(userModel.getUserPassword());
                userDto.setContainedMicroknowledge(userModel.getContainedMicroknowledge());
                userDto.setReadertoComment(userModel.getReadertoComment() == null ? new HashSet<Comment>() : userModel.getReadertoComment());
                userDto.setStaredMicroknowledge(userModel.getStaredMicroknowledge() == null ? new HashSet<MicroKnowledge>() : userModel.getStaredMicroknowledge());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return userDto;
    }

    @Override
        public List<MicroKnowledge> listMicroknowledgeOfUser(String userId) {
        return microKnowledgeRepository.findByUserContainModelId(userId);
    }
}
