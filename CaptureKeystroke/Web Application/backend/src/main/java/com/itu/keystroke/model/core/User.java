package com.itu.keystroke.model.core;

import com.itu.keystroke.model.BaseDomainModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseDomainModel {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

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

    public List<Keystroke> getKeystrokes() {
        return keystrokes;
    }

    public void setKeystrokes(List<Keystroke> keystrokes) {
        this.keystrokes = keystrokes;
    }
}