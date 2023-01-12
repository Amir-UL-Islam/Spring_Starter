package com.example.demo.entities;

import com.example.demo.controllers.AppUserRoll;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "AppUser") // For Hibernate
public class AppUser implements UserDetails {
    @Id
     @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1 // This 1 is to INCREMENT Id by 1. FOR EXAMPLE: 1, 2, 3, 4 ...
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Generating values for Sequence WE CREATED.
            generator = "app_user_sequence"
    )
    @Column(nullable = false)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(
            nullable = false
    )
    private String email;
    @Column(
            nullable = false
    )
    private String password;


    @Enumerated(
            EnumType.STRING
    ) // For Enums
    private AppUserRoll appUserRoll;
    private Boolean locked = false;
    private Boolean enable = false;

    @SuppressWarnings("unused")
    public AppUser(String firstname,
                   String lastname,
                   String email,
                   String password,
                   AppUserRoll appUserRoll) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.appUserRoll = appUserRoll;
    }

// GrantedAuthority as being a "permission" or a "right". Those "permissions" are (normally) expressed as strings (with the getAuthority() method).
// Those strings let you identify the permissions and let your voters decide if they grant access to something.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRoll.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

     public String getFirstname(){
        return firstname;
     }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
