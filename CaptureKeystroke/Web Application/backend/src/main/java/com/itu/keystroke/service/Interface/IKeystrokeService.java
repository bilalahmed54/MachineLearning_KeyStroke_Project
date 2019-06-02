package com.itu.keystroke.service.Interface;

import com.itu.keystroke.dto.BaseDTO;

import java.io.File;
import java.util.List;

public interface IKeystrokeService {

    public List<File> download();
    public void removeKeystrokeFiles();
    public BaseDTO save(String email, String keystrokeType, String keystrokeMode, int enrollmentNumber, String keystrokes);
}