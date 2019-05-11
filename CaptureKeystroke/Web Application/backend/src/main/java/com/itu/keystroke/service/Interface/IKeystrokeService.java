package com.itu.keystroke.service.Interface;

import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.dto.keystroke.KeystrokeRequestDTO;

import java.io.File;
import java.util.List;

public interface IKeystrokeService {

    public BaseDTO save(String email, String keystrokeType, int enrollmentNumber, List<KeystrokeRequestDTO> keystrokes);

    public List<File> download();
}