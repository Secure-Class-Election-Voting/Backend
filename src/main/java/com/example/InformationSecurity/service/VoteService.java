//package com.example.InformationSecurity.service;// VoteService.java
//
//import com.example.InformationSecurity.crypto.CryptoUtils;
//import com.example.InformationSecurity.model.VoteRequest;
//import com.example.InformationSecurity.model.VoteResponse;
//import org.springframework.stereotype.Service;
//
//import java.security.KeyPair;
//
//@Service
//public class VoteService {
//
//    private final KeyPair collectorKeyPair;
//
//    public VoteService() throws Exception {
//        collectorKeyPair = CryptoUtils.generateRSAKeyPair();
//        CryptoUtils.log("Collector's RSA Key Pair generated.");
//    }
//
//    public VoteResponse processVote(VoteRequest request) throws Exception {
//        VoteResponse response = new VoteResponse();
//
//        String token = CryptoUtils.hash(request.username + request.password);
//        CryptoUtils.log("Generating token using: " + request.username + request.password);
//        CryptoUtils.log("Hashed Input (" + request.username + request.password + "): " + token);
//        response.token = token;
//
//        CryptoUtils.log("Hashing vote choice: " + request.vote);
//        String hashedVote = CryptoUtils.hash(request.vote);
//        CryptoUtils.log("Hashed Input (" + request.vote + "): " + hashedVote);
//        response.hashedVote = hashedVote;
//
//        CryptoUtils.log("Encrypting hashed vote...");
//        String encryptedVote = CryptoUtils.encrypt(hashedVote, collectorKeyPair.getPublic());
//        response.encryptedVote = encryptedVote;
//
//        CryptoUtils.log("Receiving vote with token: " + token);
//        CryptoUtils.log("Decrypting vote...");
//        String decryptedVote = CryptoUtils.decrypt(encryptedVote, collectorKeyPair.getPrivate());
//        response.decryptedVote = decryptedVote;
//
//        response.verification = decryptedVote.equals(hashedVote) ?
//                "Vote received and verified (hash): " + decryptedVote :
//                "Verification failed.";
//
//        return response;
//    }
//}
