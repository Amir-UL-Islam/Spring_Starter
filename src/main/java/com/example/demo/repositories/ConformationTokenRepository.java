package com.example.demo.repositories;

import com.example.demo.entities.ConformationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConformationTokenRepository extends JpaRepository<ConformationToken, Long> {
    // Getting the Token(if any in DB) Generated by Automated System for Registration Process
    Optional<ConformationToken> findByToken(String token);



    // TODO:
    //??
    @Transactional
    @Modifying
    @Query("UPDATE ConformationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")

   int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt); // Setting the ConfirmedTime

}
