package com.example.demo.services;

import com.example.demo.repositories.AppUserRepository;
import com.example.demo.entities.AppUser;
import com.example.demo.entities.ConformationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor // For Creating Constructor for AppUserRepository
public class AppUserService implements UserDetailsService {
    // For AppUser Data
    private final AppUserRepository appUserRepository;
    // For Password Encryption
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConformationTokenService conformationTokenService;

//    01 for this file
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Error Message for UsernameNotFoundException
        return appUserRepository.findByEmail(email).orElseThrow(() -> new  UsernameNotFoundException(String.format("User with Email %s NOT Found.", email)));
    }


    // SignUp User and Validate if User is Present or Not

    // First locate the User by Email
    public String signUpUser(AppUser appUser) throws IllegalAccessException {
        // Checking if User is Present or Not
        boolean Present = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if(Present){
            throw new IllegalAccessException("Email Already Exist");
        }

        // Encoding the Password and Storing it to DB
        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());


        appUser.setPassword(encodePassword);
        appUserRepository.save(appUser); // Saving the User into  database

//        Send ConformationToken
        // Generating Tokens
        String token = UUID.randomUUID().toString();

        ConformationToken conformationToken = new ConformationToken(
                // Storing token. The one sent to user for conformation
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                appUser // for maintaining the relationship between User and Token

                // confirmedAt will be null for now
        );

        // Sending the Token to DB
        conformationTokenService.saveConformationToken(conformationToken);

        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
