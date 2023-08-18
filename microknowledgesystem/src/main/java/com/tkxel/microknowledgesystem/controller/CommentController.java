package com.tkxel.microknowledgesystem.controller;

import com.tkxel.microknowledgesystem.models.Comment;
import com.tkxel.microknowledgesystem.service.MicroKnowledgeSystem;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class CommentController {
    @Autowired
    private MicroKnowledgeSystem microKnowledgeSystem;

    @PostMapping("/writecomment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment content, @RequestParam("userId") String userId, @RequestParam("microId") String microId){
        // We will recieve -> content + user Id + MicroKnowledge Id
        Comment comment = microKnowledgeSystem.writeComment(content,userId, microId);
        return (comment != null) ?  new ResponseEntity<>(comment, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
