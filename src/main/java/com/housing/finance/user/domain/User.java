package com.housing.finance.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
@Table(name = "exception")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;

    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isNotEqualToPassword(String password) {
        return !BCrypt.checkpw(password, this.password);
    }
}
