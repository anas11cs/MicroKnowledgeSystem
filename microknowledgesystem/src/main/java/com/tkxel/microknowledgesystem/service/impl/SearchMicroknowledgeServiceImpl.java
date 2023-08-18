package com.tkxel.microknowledgesystem.service.impl;

import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import com.tkxel.microknowledgesystem.repository.MicroKnowledgeRepository;
import com.tkxel.microknowledgesystem.service.SearchMicroknowledgeService;
import com.tkxel.microknowledgesystem.service.SearchMicroknowledgeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class SearchMicroknowledgeServiceImpl implements SearchMicroknowledgeService {
    @Autowired
    private MicroKnowledgeRepository microKnowledgeRepository;
    @Override
    public List<MicroKnowledge> searchMicroknowledge(List<String> keywords){
        return microKnowledgeRepository.findByKeywordIn(keywords);
    }
    @Override
    public Optional<MicroKnowledge> viewMicroknowledge(String microKnowledgeId)
    {
        return microKnowledgeRepository.findById(microKnowledgeId);
    }
}
