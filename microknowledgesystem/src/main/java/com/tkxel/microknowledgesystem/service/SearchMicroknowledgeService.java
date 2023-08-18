package com.tkxel.microknowledgesystem.service;

import com.tkxel.microknowledgesystem.models.MicroKnowledge;

import java.util.List;
import java.util.Optional;

public interface SearchMicroknowledgeService {
    List<MicroKnowledge> searchMicroknowledge(List<String> keywords);
    Optional<MicroKnowledge> viewMicroknowledge(String microKnowledgeId);
}
