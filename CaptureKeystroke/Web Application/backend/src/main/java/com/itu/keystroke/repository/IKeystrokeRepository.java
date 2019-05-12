package com.itu.keystroke.repository;

import com.itu.keystroke.enums.KeystrokeType;
import com.itu.keystroke.model.core.Keystroke;
import com.itu.keystroke.model.core.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IKeystrokeRepository extends JpaRepository<Keystroke, Long> {

    public Keystroke findFirstByUserAndAndRecordNumberAndAndKeystrokeType(User user, int recordNumber, KeystrokeType keystrokeType);
}