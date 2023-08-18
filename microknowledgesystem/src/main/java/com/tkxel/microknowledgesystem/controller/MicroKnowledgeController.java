package com.tkxel.microknowledgesystem.controller;

import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import com.tkxel.microknowledgesystem.service.ListMicroKnowledgeOfUserService;
import com.tkxel.microknowledgesystem.service.ManageMicroKnowledgeService;
import com.tkxel.microknowledgesystem.service.MicroKnowledgeSystem;
import com.tkxel.microknowledgesystem.service.SearchMicroknowledgeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
public class MicroKnowledgeController {
    @Autowired
    private MicroKnowledgeSystem microKnowledgeSystem;
    @Autowired
    private ManageMicroKnowledgeService manageMicroKnowledgeService;
    @Autowired
    private SearchMicroknowledgeService searchMicroknowledgeService;
    @Autowired
    private ListMicroKnowledgeOfUserService listMicroKnowledgeOfUserService;

    @PostMapping("/createmicroknowledge")
    public ResponseEntity<MicroKnowledge> addNewMicroKnowledge(@RequestBody MicroKnowledge microKnowledge, @RequestParam String userId){
        log.info("Microknowledge Id Recieved:"+microKnowledge.getId() + " User Id Recieved:"+userId);
        MicroKnowledge microKnowledgeSaved = manageMicroKnowledgeService.createMicroknowledge(microKnowledge, userId);
        return (microKnowledgeSaved != null) ?  new ResponseEntity<>(microKnowledgeSaved, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/updatemicroknowledge")
    public ResponseEntity<MicroKnowledge> updateMicroKnowledge(@RequestBody MicroKnowledge microKnowledge, @RequestParam String userId){
        log.info("Microknowledge Id Recieved:"+microKnowledge.getId());
        MicroKnowledge microKnowledgeSaved = manageMicroKnowledgeService.modifyMicroknowledge(microKnowledge, userId);
        return (microKnowledgeSaved != null) ?  new ResponseEntity<>(microKnowledgeSaved, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/searchmicroknowledge")
    public ResponseEntity<List<MicroKnowledge>> getAllMicroKnowledgesWithKeywords(@RequestParam("keyword") List<String> keywords){
     // List of All Microknowledges Returned
        try{
            List<MicroKnowledge> keyWordMicroKnowledgesList = searchMicroknowledgeService.searchMicroknowledge(keywords);
            return keyWordMicroKnowledgesList.isEmpty() ?
                    new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                    new ResponseEntity<>(keyWordMicroKnowledgesList, HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/viewmicroknowledge")
    public ResponseEntity<MicroKnowledge> getMicroKnowledgeById(@RequestParam String microId){
        Optional<MicroKnowledge> microKnowledgeFound = searchMicroknowledgeService.viewMicroknowledge(microId);
        return microKnowledgeFound.map(microKnowledge -> new ResponseEntity<>(microKnowledge, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/listmicroknowledgeofuser")
    public List<MicroKnowledge> getAllMicroKnowledgeOfUser(@RequestParam("userId") String userId) {
        log.info("User Id received for Search is:"+userId);
        List<MicroKnowledge> micros = listMicroKnowledgeOfUserService.listMicroknowledgeOfUser(userId);
        return micros;
    }

    @PostMapping("/starmicroknowledge")
    public ResponseEntity<MicroKnowledge> addStarToMicroKnowledge(@RequestParam("microId") String microId, @RequestParam("userId") String userId){
        log.info("microId is:"+microId +" and userId is:"+userId);
        MicroKnowledge microKnowledgeSaved = microKnowledgeSystem.starMicroknowledge(microId, userId);
        return (microKnowledgeSaved != null) ?  new ResponseEntity<>(microKnowledgeSaved, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/liststaredmicroknowledge")
    public ResponseEntity<List<MicroKnowledge>> getAllStarredMicroKnowledge(@RequestParam("userId") String userId){
            List<MicroKnowledge> starredMicroKnowledgesList = microKnowledgeSystem.listStaredMicroknowledgeOfUser(userId);
            return (starredMicroKnowledgesList== null || starredMicroKnowledgesList.isEmpty()) ?
                    new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                    new ResponseEntity<>(starredMicroKnowledgesList, HttpStatus.OK);
    }

}
