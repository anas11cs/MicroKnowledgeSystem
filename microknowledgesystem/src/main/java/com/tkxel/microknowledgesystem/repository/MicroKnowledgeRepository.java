package com.tkxel.microknowledgesystem.repository;

import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroKnowledgeRepository extends JpaRepository<MicroKnowledge, String> {
    List<MicroKnowledge> findByKeywordIn(List<String> keywords);
    List<MicroKnowledge> findByUserContainModelId(String userId);
}
