package com.itu.keystroke.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itu.keystroke.model.core.User;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserSummaryDTO implements Serializable {

    private long id;
    private String name;
    private String email;

    public UserSummaryDTO() {
    }

    public UserSummaryDTO(long id, String name, String email) {

        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserSummaryDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}