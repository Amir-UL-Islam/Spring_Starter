package com.example.demo.registration.token;

import com.example.demo.appuser.AppUser;
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
            allocationSize = 1 // This 1 is to INCREMENT/AutoGenerate ID by 1. FOR EXAMPLE: 1, 2, 3, 4 ...
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Generating values for Sequence WE CREATED.
            generator = "conformation_token_sequence"
    )
    @Column(nullable = false)
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
//                             LocalDateTime confirmedAt,
                             AppUser appUser) {
        this.token = token;
        this.tokenCreatedAt = tokenCreatedAt;
        this.tokenExpiredAt = tokenExpiredAt;
//        this.confirmedAt = confirmedAt;
        this.appUser = appUser;
    }
}
