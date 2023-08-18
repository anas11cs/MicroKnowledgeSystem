package com.tkxel.microknowledgesystem.service;

import com.tkxel.microknowledgesystem.models.Comment;
import com.tkxel.microknowledgesystem.models.MicroKnowledge;

import java.util.List;

public interface MicroKnowledgeSystem {
    boolean logIn(String userId, String userPassword);
    Comment writeComment(Comment commentObject, String userId, String microId);
    MicroKnowledge starMicroknowledge(String microId, String userId);
    List<MicroKnowledge> listStaredMicroknowledgeOfUser(String userId);
}
