package com.tkxel.microknowledgesystem.service.impl;

import com.tkxel.microknowledgesystem.models.Comment;
import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import com.tkxel.microknowledgesystem.models.UserModel;
import com.tkxel.microknowledgesystem.repository.CommentRepository;
import com.tkxel.microknowledgesystem.repository.MicroKnowledgeRepository;
import com.tkxel.microknowledgesystem.repository.UserRepository;
import com.tkxel.microknowledgesystem.service.ManageMicroKnowledgeService;
import com.tkxel.microknowledgesystem.service.MicroKnowledgeSystem;
import com.tkxel.microknowledgesystem.service.ManageMicroKnowledgeService;
import com.tkxel.microknowledgesystem.service.MicroKnowledgeSystem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MicroKnowledgeSystemImpl implements MicroKnowledgeSystem {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MicroKnowledgeRepository microKnowledgeRepository;
    @Autowired
    private ManageMicroKnowledgeService manageMicroKnowledgeService;
    @Override
    public boolean logIn(String userId,String userPassword){
        return userRepository.existsByIdAndUserPassword(userId, userPassword);
    }

    @Override
    public Comment writeComment(Comment commentObject, String userId, String microId){
        if(manageMicroKnowledgeService.authenticateUserAndMicroKnowledge(userId , microId)) {
            commentObject.setWritingTime(LocalDate.now());
            commentObject.setCommenttoMicroknowledge(microKnowledgeRepository.getById(microId));
            commentObject.setCommenttoReader(userRepository.getById(userId));
            commentRepository.save(commentObject);
            return commentObject;
        }
        return null;
    }

    @Override
    @Transactional
    public MicroKnowledge starMicroknowledge(String microId, String userId){
        if(manageMicroKnowledgeService.authenticateUserAndMicroKnowledge(userId , microId)){
            log.info("Going to fetch Micro and User Objects");
            MicroKnowledge micro = microKnowledgeRepository.findById(microId).get();
            UserModel user = userRepository.findById(userId).get();
            log.info("Fetched Micro and User Objects");
            if(user.getStaredMicroknowledge()==null){
                log.info("Starred Micro is null");
                user.setStaredMicroknowledge(new HashSet<>());
            }
            log.info("Check Starred Micro Object");
            user.getStaredMicroknowledge().add(micro);
            micro.setNumberOfStars(micro.getNumberOfStars()+1);
            micro.setUserStarModel(user);
            log.info("Going to Save Starred Micro Objects");
            userRepository.save(user);
            return microKnowledgeRepository.save(micro);
        }
        return null;
    }

    @Override
    @Transactional
    public List<MicroKnowledge> listStaredMicroknowledgeOfUser(String userId) {
        UserModel user = userRepository.findById(userId).get();
        List <MicroKnowledge> starredMicroKnowledge = user.getStaredMicroknowledge().stream().collect(Collectors.toList());
        return starredMicroKnowledge;
    }
}
