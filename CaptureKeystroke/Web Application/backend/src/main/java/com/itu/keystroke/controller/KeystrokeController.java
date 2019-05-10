package com.itu.keystroke.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.dto.keystroke.KeystrokeRequestDTO;
import com.itu.keystroke.service.Interface.IKeystrokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/keystroke")
public class KeystrokeController {

    @Autowired
    IKeystrokeService iKeystrokeService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseDTO save(@RequestParam(value = "email", required = false) String email,
                        @RequestParam(value = "keystrokeType", required = false) String keystrokeType,
                        @RequestParam(value = "enrollmentNumber", required = false) int enrollmentNumber,
                        @Valid @RequestPart(value = "keystrokes", required = false) String keystrokesStr) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        List<KeystrokeRequestDTO> keystrokes = objectMapper.readValue(keystrokesStr, new TypeReference<List<KeystrokeRequestDTO>>() {
        });

        return iKeystrokeService.save(email, keystrokeType, enrollmentNumber, keystrokes);
    }
}