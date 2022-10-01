package com.example.beamo.service.users;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.beamo.jwt.JwtProperties;
import com.example.beamo.model.KakaoProfile;
import com.example.beamo.model.OauthToken;
import com.example.beamo.repository.users.Users;
import com.example.beamo.repository.users.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    UsersRepository userRepository; //(1)

    public String saveUserAndGetToken(String token) {

        //(1)
        KakaoProfile profile = findProfile(token);

        //(2)
        Users user = userRepository.findByEmail(profile.getKakao_account().getEmail());

        //(3)
        if(user == null) {
            user = Users.builder()
                    .seq(profile.getId())
                    //(4)
                    .profile(profile.getKakao_account().getProfile().getProfile_image_url())
                    .name(profile.getKakao_account().getProfile().getNickname())
                    .email(profile.getKakao_account().getEmail())
                    //(5)
                    .userRole("ROLE_USER").build();

            userRepository.save(user);
        }

        return createToken(user); //(2)
    }

    public String createToken(Users user) { //(2-1)

        //(2-2)
        String jwtToken = JWT.create()

                //(2-3)
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))

                //(2-4)
                .withClaim("id", user.getSeq())
                .withClaim("nickname", user.getName())

                //(2-5)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken; //(2-6)
    }


    //(1-1)
    public KakaoProfile findProfile(String token) {

        //(1-2)
        RestTemplate rt = new RestTemplate();

        //(1-3)
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //(1-5)
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        //(1-6)
        // Http 요청 (POST 방식) 후, response 변수에 응답을 받음
        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        //(1-7)
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;
    }
    public Users getUser(HttpServletRequest request) { //(1)
        //(2)
        Long userCode = (Long) request.getAttribute("userCode");
        System.out.println("userCode : " + userCode);

        //(3)
        Users user = userRepository.findBuU_seq(userCode);

        //(4)
        return user;
    }
}
