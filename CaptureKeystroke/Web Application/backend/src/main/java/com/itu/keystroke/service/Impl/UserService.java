package com.itu.keystroke.service.Impl;

import com.itu.keystroke.dto.user.UserDTO;
import com.itu.keystroke.model.core.User;
import com.itu.keystroke.repository.IUserRepository;
import com.itu.keystroke.service.Interface.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDTO save(String username, String email) {

        UserDTO userDTO = new UserDTO();

        try {
            User user = iUserRepository.findByEmail(email);

            if (user == null) {

                user = new User();

                user.setEmail(email);
                user.setName(username);

                iUserRepository.save(user);

                userDTO.setUser(user);
                userDTO.setAppErrorCode(200);
                userDTO.setStatus(HttpStatus.OK);
                userDTO.setMessage("User Registered Successfully.");

            } else {

                userDTO.setAppErrorCode(409);
                userDTO.setStatus(HttpStatus.CONFLICT);
                userDTO.setMessage("User with Given Email is Registered Already: " + email);
            }

        } catch (Exception ex) {

            LOGGER.info("Some Error Occurred while Registering User. See Error Logs for More Details.");
            LOGGER.error("Exception Occurred while Registering User: ", ex);

            userDTO.setAppErrorCode(500);
            userDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            userDTO.setMessage("Some Error Occurred while Registering User. See Error Logs for More Details");
        }

        return userDTO;
    }
}