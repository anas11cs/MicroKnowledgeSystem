package com.tkxel.microknowledgesystem.service;

import com.tkxel.microknowledgesystem.models.MicroKnowledge;

public interface ManageMicroKnowledgeService {

    MicroKnowledge createMicroknowledge(MicroKnowledge microKnowledge, String userId);
    MicroKnowledge modifyMicroknowledge(MicroKnowledge microKnowledge, String userId);
    boolean authenticateUserAndMicroKnowledge(String userId, String microKnowledgeId);
    boolean authenticateMicroKnowledgeId(String microKnowledgeId);
}

