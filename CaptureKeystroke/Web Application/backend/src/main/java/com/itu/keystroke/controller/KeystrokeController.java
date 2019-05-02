package com.itu.keystroke.controller;

import com.itu.keystroke.service.Interface.IKeystrokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/keystroke")
public class KeystrokeController {

    @Autowired
    IKeystrokeService iKeystrokeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save() {

        iKeystrokeService.save();
    }
}