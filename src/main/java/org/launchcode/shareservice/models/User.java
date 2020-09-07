package org.launchcode.shareservice.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String pwHash;

//    We use it to create and verify hashes.
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String name, String password) {
        super(name);
//        We use encoder to create a hash from the given password.
//        We should never store passwords.
        this.pwHash = encoder.encode(password);
    }


//    To determine if a given password is a match for the hash stored by the object.
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
