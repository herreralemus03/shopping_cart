package com.hlstudios.auth.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users", schema = "shopping")
public class User {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @Getter @Setter
    @Column(name = "username")
    String username;

    @Getter @Setter
    @Column(name = "user_password")
    String password;

}
