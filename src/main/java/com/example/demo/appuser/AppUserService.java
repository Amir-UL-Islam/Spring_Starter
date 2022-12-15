package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor // For Creating Constructor for AppUserRepository
public class AppUserService implements UserDetailsService {
    // Error Message for UsernameNotFoundException
    private final String USER_NOT_FOUND = "User with Email %s NOT Found.";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new  UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(AppUser appUser) throws IllegalAccessException {
        boolean isPresent = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if(isPresent){
            throw new IllegalAccessException("Already Exist");
        }

        String encodePass = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePass);
        appUserRepository.save(appUser); // Saving the User into  database
//        TODO: Send Conformation
        return "Works";
    }
}
