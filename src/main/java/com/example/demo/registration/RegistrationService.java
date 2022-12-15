package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRoll;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;

    public String register(RegistrationRequest request) throws IllegalAccessException {
        boolean isValid = emailValidator.test(request.getEmail());
        if (!isValid){
            throw new IllegalAccessException("Email Not Valid");
        }
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
}
