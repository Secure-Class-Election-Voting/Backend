//package com.example.InformationSecurity.controller;// VoteController.java
//
//import com.example.InformationSecurity.model.VoteRequest;
//import com.example.InformationSecurity.model.VoteResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import com.example.InformationSecurity.service.VoteService;
//
//@RestController
//@RequestMapping("/api")
//public class VoteController {
//
//    @Autowired
//    private VoteService voteService;
//
//    @PostMapping("/vote")
//    public VoteResponse receiveVote(@RequestBody VoteRequest request) throws Exception {
//        return voteService.processVote(request);
//    }
//}
