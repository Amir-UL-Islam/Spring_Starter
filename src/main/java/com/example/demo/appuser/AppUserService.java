package com.example.demo.appuser;

import com.example.demo.registration.token.ConformationToken;
import com.example.demo.registration.token.ConformationTokenService;
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
    // Error Message for UsernameNotFoundException
    private final String USER_NOT_FOUND = "User with Email %s NOT Found.";
    // For AppUser Data
    private final AppUserRepository appUserRepository;
    // For Password Encryption
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConformationTokenService conformationTokenService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new  UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    // SignUp User and Validate if User is Present or Not
    public String signUpUser(AppUser appUser) throws IllegalAccessException {
        boolean isPresent = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(isPresent){
            throw new IllegalAccessException("Already Exist");
        }

        // Encoding the Password and Storing it to DB
        String encodePass = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePass);
        appUserRepository.save(appUser); // Saving the User into  database

//        Send ConformationToken
        // Generating Tokens
        String token = UUID.randomUUID().toString();
        ConformationToken conformationToken = new ConformationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                appUser
        );
        conformationTokenService.saveConformationToken(conformationToken);

        // SEND Email
        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
