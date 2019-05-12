package com.itu.keystroke.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.dto.keystroke.KeystrokeRequestDTO;
import com.itu.keystroke.enums.KeystrokeType;
import com.itu.keystroke.model.core.Keystroke;
import com.itu.keystroke.model.core.User;
import com.itu.keystroke.repository.IKeystrokeRepository;
import com.itu.keystroke.repository.IUserRepository;
import com.itu.keystroke.service.Interface.IKeystrokeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class KeystrokeService implements IKeystrokeService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IKeystrokeRepository iKeystrokeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeystrokeService.class);

    @Override
    public BaseDTO save(String email, String keystrokeType, int enrollmentNumber, String keystrokes) {

        BaseDTO baseDTO = new BaseDTO();

        try {

            User user = iUserRepository.findByEmail(email);
            KeystrokeType keystrokeTypeEnum = KeystrokeType.valueOf(keystrokeType.toUpperCase());

            if (user != null) {

                if (keystrokes.length() <= 65_500) {

                    Keystroke keystrokeFound = iKeystrokeRepository.findFirstByUserAndAndRecordNumberAndAndKeystrokeType(user, enrollmentNumber, keystrokeTypeEnum);

                    if (keystrokeFound == null) {

                        Keystroke keystroke = new Keystroke();

                        keystroke.setUser(user);
                        keystroke.setKeyStrokesData(keystrokes);
                        keystroke.setRecordNumber(enrollmentNumber);
                        keystroke.setKeystrokeType(keystrokeTypeEnum);

                        iKeystrokeRepository.save(keystroke);

                        baseDTO.setAppErrorCode(200);
                        baseDTO.setStatus(HttpStatus.OK);
                        baseDTO.setMessage("User Keystrokes Saved Successfully.");

                    } else {

                        baseDTO.setAppErrorCode(409);
                        baseDTO.setStatus(HttpStatus.CONFLICT);
                        baseDTO.setMessage("User Keystroke Already Exists!");
                    }

                } else {

                    baseDTO.setAppErrorCode(400);
                    baseDTO.setStatus(HttpStatus.BAD_REQUEST);
                    baseDTO.setMessage("Too Much KeyStrokes Data: " + keystrokes.length());
                }

            } else {

                baseDTO.setAppErrorCode(400);
                baseDTO.setStatus(HttpStatus.BAD_REQUEST);
                baseDTO.setMessage("User Not Found with Email: " + email);
            }

        } catch (Exception ex) {

            LOGGER.info("Some Error Occurred while Saving User Keystrokes. See Error Logs for More Details.");
            LOGGER.error("Exception Occurred while Saving User Keystrokes: ", ex);

            baseDTO.setAppErrorCode(500);
            baseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            baseDTO.setMessage("Some Error Occurred while Saving User Keystrokes. See Error Logs for More Details.");
        }

        return baseDTO;
    }

    @Override
    public List<File> download() {

        List<File> files = new ArrayList<>();

        try {

            List<User> users = iUserRepository.findAll();
            ObjectMapper objectMapper = new ObjectMapper();

            for (User user : users) {

                for (int i = 1; i <= 5; i++) {

                    Keystroke fixedKeyStroke = iKeystrokeRepository.findFirstByUserAndAndRecordNumberAndAndKeystrokeType(user, i, KeystrokeType.FIX);

                    if (fixedKeyStroke != null) {

                        String fileName = user.getId() + "_" + i + "_" + KeystrokeType.FIX.name() + ".txt";

                        File fout = new File(fileName);
                        FileOutputStream fos = new FileOutputStream(fout);

                        OutputStreamWriter osw = new OutputStreamWriter(fos);

                        List<KeystrokeRequestDTO> keystrokesData = objectMapper.readValue(fixedKeyStroke.getKeyStrokesData(), new TypeReference<List<KeystrokeRequestDTO>>() {
                        });

                        for (KeystrokeRequestDTO keystrokeData : keystrokesData) {

                            String line = keystrokeData.getKeyTyped() + " " + keystrokeData.getKeystrokeEvent() + " " + keystrokeData.getTimeStamp() + "\n";
                            osw.write(line);
                        }

                        osw.close();

                        files.add(fout);
                    }
                }

                for (int i = 1; i <= 5; i++) {

                    Keystroke freeKeyStroke = iKeystrokeRepository.findFirstByUserAndAndRecordNumberAndAndKeystrokeType(user, i, KeystrokeType.FREE);

                    if (freeKeyStroke != null) {

                        String fileName = user.getId() + "_" + i + "_" + KeystrokeType.FREE.name() + ".txt";
                        File fout = new File(fileName);
                        FileOutputStream fos = new FileOutputStream(fout);

                        OutputStreamWriter osw = new OutputStreamWriter(fos);

                        List<KeystrokeRequestDTO> keystrokesData = objectMapper.readValue(freeKeyStroke.getKeyStrokesData(), new TypeReference<List<KeystrokeRequestDTO>>() {
                        });

                        for (KeystrokeRequestDTO keystrokeData : keystrokesData) {

                            String line = keystrokeData.getKeyTyped() + " " + keystrokeData.getKeystrokeEvent() + " " + keystrokeData.getTimeStamp() + "\n";
                            osw.write(line);
                        }

                        osw.close();

                        files.add(fout);
                    }
                }
            }

        } catch (Exception ex) {

            LOGGER.error("Exception Occurred while Creating Text Files: ", ex);
        }

        return files;
    }
}