package com.example.beamo.controller.users;

import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/user" , produces = "application/json")
public class UserController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return ResponseEntity.ok(usersList);
    }
}
