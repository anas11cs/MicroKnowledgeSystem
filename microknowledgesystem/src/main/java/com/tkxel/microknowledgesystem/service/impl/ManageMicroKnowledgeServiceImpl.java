package com.tkxel.microknowledgesystem.service.impl;

import com.tkxel.microknowledgesystem.dtos.MicroKnowledgeDto;
import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import com.tkxel.microknowledgesystem.models.UserModel;
import com.tkxel.microknowledgesystem.repository.CommentRepository;
import com.tkxel.microknowledgesystem.repository.MicroKnowledgeRepository;
import com.tkxel.microknowledgesystem.service.ManageMicroKnowledgeService;
import com.tkxel.microknowledgesystem.service.ManageUserService;
import com.tkxel.microknowledgesystem.service.ManageMicroKnowledgeService;
import com.tkxel.microknowledgesystem.service.ManageUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;

@Service
@Log4j2
public class ManageMicroKnowledgeServiceImpl implements ManageMicroKnowledgeService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MicroKnowledgeRepository microKnowledgeRepository;
    @Autowired
    private ManageUserService manageUserService;

    @Override
    public MicroKnowledge createMicroknowledge(MicroKnowledge microKnowledge, String userId){
        if(manageUserService.authenticateUserId(userId) && !microKnowledgeRepository.existsById(microKnowledge.getId())){

            UserModel currentUser = manageUserService.getUserById(userId).get();
            log.info("User Found with Id:" + currentUser.getId());
            microKnowledge.setUserContainModel(currentUser);
            microKnowledge.setLastEditTime(LocalDate.now());

            if(currentUser.getContainedMicroknowledge() == null) {
                currentUser.setContainedMicroknowledge(new HashSet<MicroKnowledge>());
            }
            currentUser.getContainedMicroknowledge().add(microKnowledge);
            manageUserService.updateUserIfExists(currentUser);
            log.info("User Saved with Updated MicroKnowledge user Id:" + currentUser.getId() + "micro Id:"+microKnowledge.getId());
            microKnowledgeRepository.save(microKnowledge);

           return microKnowledge;
        }
        log.warn("User Not Found with Id:"+userId + " Or Microknowledge Id:" +microKnowledge.getId() + " already exists");
        return null;
    }

    @Override
    @Transactional
    @Modifying
    public MicroKnowledge modifyMicroknowledge(MicroKnowledge microKnowledge, String userId){
        if(authenticateUserAndMicroKnowledge(userId , microKnowledge.getId())){

            MicroKnowledge oldMicro = microKnowledgeRepository.findById(microKnowledge.getId()).get();
            MicroKnowledgeDto updateMicro = new MicroKnowledgeDto();
            updateMicro.setId(oldMicro.getId());
            updateMicro.setContent(microKnowledge.getContent());
            updateMicro.setKeyword(microKnowledge.getKeyword());
            updateMicro.setLastEditTime(LocalDate.now());
            updateMicro.setUserContainModel(oldMicro.getUserContainModel());
            updateMicro.setUserStarModel(oldMicro.getUserStarModel());
            updateMicro.setContainedComment(oldMicro.getContainedComment());
            updateMicro.setNumberOfStars(oldMicro.getNumberOfStars());

            microKnowledge.setId(updateMicro.getId());
            microKnowledge.setKeyword(updateMicro.getKeyword());
            microKnowledge.setNumberOfStars(updateMicro.getNumberOfStars());
            microKnowledge.setContent(updateMicro.getContent());
            microKnowledge.setUserStarModel(updateMicro.getUserStarModel());
            microKnowledge.setContainedComment(updateMicro.getContainedComment());
            microKnowledge.setUserContainModel(updateMicro.getUserContainModel());
            microKnowledge.setLastEditTime(LocalDate.now());

            return microKnowledgeRepository.save(microKnowledge);
        }
        return null;
    }

    @Override
    public boolean authenticateUserAndMicroKnowledge(String userId , String microKnowledgeId){
        return (manageUserService.authenticateUserId(userId) && this.authenticateMicroKnowledgeId(microKnowledgeId));
    }

    @Override
    public boolean authenticateMicroKnowledgeId(String microKnowledgeId){
        return microKnowledgeRepository.existsById(microKnowledgeId);
    }
}
