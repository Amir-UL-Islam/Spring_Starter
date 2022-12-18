package com.example.demo.registration;

import lombok.*;


@Getter
// Things are already Set in AppUser.java file. So, we Don't Need @Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
// 2. Requesting Data from User/NewUser Set in AppUser file -> AppUser.java
public class RegistrationRequest {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;
}
