package edu.ucmo.devet.db.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jdbi.v3.core.Jdbi;

@Singleton
public class UserDAO {

    private final Jdbi jdbi;

    @Inject
    public UserDAO(final Jdbi jdbi){
        this.jdbi = jdbi;
    }
    
    public void insert(String username){
        boolean exists = jdbi.withHandle(handle -> handle
            .createQuery("SELECT username FROM user WHERE username = :username")
            .bind("username", username)
            .mapTo(String.class)
            .findFirst()
            .isPresent());
            
            
        if(!exists) {
            jdbi.useHandle(handle -> handle
                .createUpdate("INSERT INTO user (username) VALUES (:username)")
                .bind("username", username)
                .execute());
        }
    }
}
