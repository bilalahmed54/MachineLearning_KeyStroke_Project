package com.itu.keystroke.service.Interface;

import com.itu.keystroke.dto.user.UserDTO;

public interface IUserService {

    public UserDTO save(String username, String email);
}