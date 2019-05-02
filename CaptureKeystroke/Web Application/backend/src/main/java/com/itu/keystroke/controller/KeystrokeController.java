package com.itu.keystroke.controller;

import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.dto.keystroke.KeystrokeRequestDTO;
import com.itu.keystroke.service.Interface.IKeystrokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/keystroke")
public class KeystrokeController {

    @Autowired
    IKeystrokeService iKeystrokeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseDTO save(@RequestParam(value = "email", required = true) String email,
                        @RequestParam(value = "keystrokeType", required = true) String keystrokeType,
                        @RequestParam(value = "enrollmentNumber", required = true) int enrollmentNumber,
                        @Valid @RequestPart(value = "keystrokes", required = true) List<KeystrokeRequestDTO> keystrokes) {

        return iKeystrokeService.save(email, keystrokeType, enrollmentNumber, keystrokes);
    }
}