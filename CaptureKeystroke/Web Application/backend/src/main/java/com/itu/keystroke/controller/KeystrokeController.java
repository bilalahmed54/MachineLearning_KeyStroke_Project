package com.itu.keystroke.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.dto.keystroke.KeystrokeRequestDTO;
import com.itu.keystroke.service.Interface.IKeystrokeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        return "OK";
    }

    @RequestMapping(value = "/download", produces = "application/zip")
    public void download(HttpServletResponse response) throws IOException {

        //setting headers
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

        // create a list to add files to be zipped
        List<File> files = iKeystrokeService.download();

        // package files
        for (File file : files) {

            //new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }

        zipOutputStream.close();
    }
}