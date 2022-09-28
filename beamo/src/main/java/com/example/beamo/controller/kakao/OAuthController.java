package com.example.beamo.controller.kakao;

import com.example.beamo.jwt.JwtProperties;
import com.example.beamo.model.OauthToken;
import com.example.beamo.repository.users.Users;
import com.example.beamo.service.users.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/oauth", produces = "application/json")
public class OAuthController {

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "로그인 및 JWT 토큰 요청")
    @GetMapping(value="/kakao")
    public ResponseEntity login(@RequestParam("code") String code) {
        System.out.println(code);
        String access_Token = oAuthService.getKakaoAccesToken(code);

        HashMap<String, Object> userInfo = oAuthService.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            String jwtToken = userService.saveUserAndGetToken(access_Token);

            HttpHeaders headers = new HttpHeaders();
            headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
            System.out.println("jwtToken : "+jwtToken);
            return ResponseEntity.ok().body(jwtToken);
        }
        else {
            System.out.println("fail");
            return new ResponseEntity<>("login fail", HttpStatus.NOT_FOUND);
        }
    }

//    @ApiOperation(value = "JWT 로 회원 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<Object> getCurrentUser(HttpServletRequest request) { //(1)

        //(2)
        Users user = userService.getUser(request);
        Long userCode = (Long) request.getAttribute("userCode");
        System.out.println(userCode);
        if(user != null) {
            System.out.println(user.getName());
            System.out.println(user.getEmail());
        }
        //(3)
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value="/logout")
    public Map<String, Object> logout(@RequestHeader Map<String, Object> requestHeader){
        System.out.println(requestHeader);
        System.out.println(requestHeader.get("authorization"));
        System.out.println("로그아웃 시도");
        return requestHeader;
//        oAuthService.kakaoLogout((String)session.getAttribute("access_Token"));
//        session.removeAttribute("access_Token");
//        session.removeAttribute("userId");
    }

}
