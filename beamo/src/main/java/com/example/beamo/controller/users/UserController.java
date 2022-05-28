package com.example.beamo.controller.users;

import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/user")
    ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return ResponseEntity.ok(usersList);
    }
}
