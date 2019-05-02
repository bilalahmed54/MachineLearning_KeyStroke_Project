package com.itu.keystroke.controller;

import com.itu.keystroke.dto.user.UserDTO;
import com.itu.keystroke.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserDTO save(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "email", required = true) String email) {

        return iUserService.save(username, email);
    }
}