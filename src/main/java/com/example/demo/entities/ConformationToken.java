package com.example.demo.entities;

import com.example.demo.entities.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ConformationToken")
public class ConformationToken {
    @Id
    @SequenceGenerator(
            name = "conformation_token_sequence",
            sequenceName = "conformation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conformation_token_sequence"
    )
    @Column(
            nullable = false
    )
    private Long id;
    private String token;
    private LocalDateTime tokenCreatedAt;
    private LocalDateTime tokenExpiredAt;
    private LocalDateTime confirmedAt;

    // ManyToOne Relationship
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;


    public ConformationToken(String token,
                             LocalDateTime tokenCreatedAt,
                             LocalDateTime tokenExpiredAt,
                             AppUser appUser) {
        this.token = token;
        this.tokenCreatedAt = tokenCreatedAt;
        this.tokenExpiredAt = tokenExpiredAt;
        // will be set when the user click on the link.
//        this.confirmedAt = confirmedAt;
        this.appUser = appUser;
    }
}
