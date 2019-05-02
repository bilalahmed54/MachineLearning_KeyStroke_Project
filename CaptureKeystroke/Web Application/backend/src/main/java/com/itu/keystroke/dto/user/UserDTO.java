package com.itu.keystroke.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.model.core.User;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO extends BaseDTO {

    private UserSummaryDTO user;

    public UserDTO() {
        super();
    }

    public UserDTO(HttpStatus status, String message, int appErrorCode, String authToken, User user) {
        super(status, message, appErrorCode);
        this.user = new UserSummaryDTO(user);
    }

    public void setUser(User user) {
        this.user = new UserSummaryDTO(user);
    }

    public UserSummaryDTO getUser() {
        return user;
    }

    public void setUser(UserSummaryDTO user) {
        this.user = user;
    }
}