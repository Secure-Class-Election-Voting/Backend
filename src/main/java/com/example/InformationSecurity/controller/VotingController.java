package com.example.InformationSecurity.controller;

import com.example.InformationSecurity.crypto.CryptoUtils;
import com.example.InformationSecurity.model.Collector;
import com.example.InformationSecurity.model.Voter;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/vote")
public class VotingController {

    private Collector collector;
    private KeyPair keyPair;
    private final Map<String, String> voterRegistry = new HashMap<>();
    private final Map<String, String> candidateHashMap = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        keyPair = CryptoUtils.generateRSAKeyPair();
        collector = new Collector(keyPair.getPrivate());

        // Dummy registry
        voterRegistry.put("dilen", "dilen123");
        voterRegistry.put("sam", "sam123");
        voterRegistry.put("sayuri", "sayuri123");
        voterRegistry.put("imasha", "imasha123");

        // Candidate setup
        for (String name : List.of("Alice", "Bob", "Charlie")) {
            candidateHashMap.put(CryptoUtils.hash(name), name);
        }
    }

    @PostMapping("/cast")
    public String castVote(@RequestParam String username, @RequestParam String password, @RequestParam String voteLetter) throws Exception {
        Map<String, String> voteLetterToName = Map.of(
                "A", "Alice",
                "B", "Bob",
                "C", "Charlie"
        );

        if (!voteLetterToName.containsKey(voteLetter)) {
            return "❌ Invalid vote letter";
        }

        if (!voterRegistry.containsKey(username) || !voterRegistry.get(username).equals(password)) {
            return "❌ Invalid credentials";
        }

        String voteName = voteLetterToName.get(voteLetter);
        Voter voter = new Voter(username, password, voteName);
        String token = voter.getToken();
        String encryptedVote = voter.encryptVote(keyPair.getPublic());

        boolean result = collector.receiveVote(encryptedVote, token);
        return result ? "✅ Vote successfully cast" : "❌ Duplicate or invalid vote";
    }

    @GetMapping("/results")
    public String getResults() {
        return collector.showResults(candidateHashMap);
    }
}