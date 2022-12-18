package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRoll;
import com.example.demo.appuser.AppUserService;
import com.example.demo.registration.token.ConformationToken;
import com.example.demo.registration.token.ConformationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final ConformationTokenService conformationTokenService;

    // Checking -> Either the New user Email/User Present or Not
    public String register(RegistrationRequest request) throws IllegalAccessException {
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid){
            throw new IllegalAccessException("Email Not Valid");
        }
        // Sending New User Information to DB. by -> AppUserService Logics
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRoll.USER
                )
        );
    }
    // Confirming Token And also Validating Data Before Inserting to DB
    @Transactional
    public String confirmToken(String token){
        // Searching the Token
        ConformationToken conformationToken = conformationTokenService.getToken(token)
                .orElseThrow(()-> new IllegalStateException("Token Not Found!"));

        if(conformationToken.getConfirmedAt() != null){ // Calling the getConfirmedAt() from Auto Generated in ConformationToken.java file using @Getter Annotation
             throw new IllegalStateException("This Email already Taken");
        }

        // Getting the  Expired Time and Holding it to expiredAt
        LocalDateTime expiredAt = conformationToken.getTokenExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Sorry! Times up. Token Expired");
        }

        conformationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                conformationToken.getAppUser().getEmail());


        return "Token Confirmed";
    }
}