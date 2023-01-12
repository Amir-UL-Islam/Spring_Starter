package com.example.demo.services;

import com.example.demo.entities.ConformationToken;
import com.example.demo.repositories.ConformationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConformationTokenService {
    private final ConformationTokenRepository conformationTokenRepository;
    // Using ConformationToken.java Logic Getting and Setting the Data
    // And Using The ConformationTokenRepository.java Logic for Generating SQL Queries for Creating, Inserting Data into DB
    public void saveConformationToken(ConformationToken token){
        // Saving the tokens into DB
        conformationTokenRepository.save(token);
    }


    // Getting the Token (if any in DB between Times)
    public Optional<ConformationToken> getToken(String token) {
        return conformationTokenRepository.findByToken(token);
    }



    public int setConfirmedAt(String token) {
        return conformationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now()); // Passing the Confirmed Time as LocalDateTime.now()
    }
}
