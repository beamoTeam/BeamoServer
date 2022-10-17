package com.example.beamo.controller.users;

import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import com.example.beamo.service.users.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/api/user" , produces = "application/json")
public class UserController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserService userService;

    @ApiOperation(value = "사용자 전체 출력")
    @GetMapping
    ResponseEntity getUsers() {
        List<Users> usersList = usersRepository.findAll();
        return ResponseEntity.ok(usersList);
    }

    @ApiOperation(value = "JWT 사용자 상세정보 출력")
    @GetMapping("/info")
    ResponseEntity getUsersBySeq(HttpServletRequest request) {
        if (userService.getUser(request) == null) {
            System.out.println("jwt controller start");
            System.out.println(request.getAttribute("userCode"));
            System.out.println(request);
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED);
        }
        Users users = usersRepository.findBuU_seq(userService.getUser(request).getSeq());
        return ResponseEntity.ok(users);
    }
}
