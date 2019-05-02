package com.itu.keystroke.repository;

import com.itu.keystroke.model.core.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}