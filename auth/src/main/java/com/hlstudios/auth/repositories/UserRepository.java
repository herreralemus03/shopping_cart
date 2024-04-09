package com.hlstudios.auth.repositories;

import com.hlstudios.auth.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select user from User user where user.username = :username")
    Optional<User> findByUsername(
            @Param("username") String username
    );

}
