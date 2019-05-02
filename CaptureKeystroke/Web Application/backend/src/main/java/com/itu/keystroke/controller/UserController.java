package com.itu.keystroke.controller;

import com.itu.keystroke.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save() {

        iUserService.save();
    }
}