package com.itu.keystroke.service.Impl;

import com.itu.keystroke.dto.BaseDTO;
import com.itu.keystroke.dto.keystroke.KeystrokeRequestDTO;
import com.itu.keystroke.enums.KeystrokeEvent;
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

import java.util.List;

@Service
public class KeystrokeService implements IKeystrokeService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IKeystrokeRepository iKeystrokeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(KeystrokeService.class);

    @Override
    public BaseDTO save(String email, String keystrokeType, int enrollmentNumber, List<KeystrokeRequestDTO> keystrokes) {

        BaseDTO baseDTO = new BaseDTO();

        try {

            User user = iUserRepository.findByEmail(email);

            KeystrokeType keystrokeTypeEnum = KeystrokeType.valueOf(keystrokeType);

            if (user != null) {

                for (KeystrokeRequestDTO keystrokeParams : keystrokes) {

                    Keystroke keystroke = new Keystroke();

                    keystroke.setUser(user);
                    keystroke.setKeystrokeType(keystrokeTypeEnum);
                    keystroke.setRecordNumber(enrollmentNumber);
                    keystroke.setKeyTyped(keystrokeParams.getKeyTyped());
                    keystroke.setTimeStamp(keystrokeParams.getTimeStamp());

                    KeystrokeEvent keystrokeEventEnum;

                    try {
                        keystrokeEventEnum = KeystrokeEvent.valueOf(keystrokeParams.getKeystrokeEvent());
                    } catch (Exception e) {
                        keystrokeEventEnum = KeystrokeEvent.PRESSED;
                    }

                    keystroke.setKeystrokeEvent(keystrokeEventEnum);

                    iKeystrokeRepository.save(keystroke);
                }

                baseDTO.setAppErrorCode(200);
                baseDTO.setStatus(HttpStatus.OK);
                baseDTO.setMessage("User Keystrokes Saved Successfully.");

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
}