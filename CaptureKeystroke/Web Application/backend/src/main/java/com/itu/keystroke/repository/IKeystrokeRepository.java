package com.itu.keystroke.repository;

import com.itu.keystroke.model.core.Keystroke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKeystrokeRepository extends JpaRepository<Keystroke, Long> {
    
}