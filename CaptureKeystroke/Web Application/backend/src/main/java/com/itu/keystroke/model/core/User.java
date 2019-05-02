package com.itu.keystroke.model.core;

import com.itu.keystroke.model.BaseDomainModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseDomainModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Keystroke> keystrokes;

    public User() {
        this.keystrokes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Keystroke> getKeystrokes() {
        return keystrokes;
    }

    public void setKeystrokes(List<Keystroke> keystrokes) {
        this.keystrokes = keystrokes;
    }
}