package com.example.beamo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.beamo.repository.users.UsersRepository;
import com.nimbusds.jose.shaded.json.JSONObject;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    String SECRET_KEY;

    @Autowired
    UsersRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtHeader = ((HttpServletRequest)request).getHeader(JwtProperties.HEADER_STRING);

        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "");

        Long userCode = null;

        try {
            userCode = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token)
                    .getClaim("id").asLong();
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", ExceptionCode.WRONG_TOKEN.getCode());
        } catch (TokenExpiredException e) {
            request.setAttribute("exception", ExceptionCode.EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", ExceptionCode.UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", ExceptionCode.IllegalArgumentException.getCode());
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("userCode : "+String.valueOf(userCode));
            log.error("token : {}", token);
            log.error("Exception Message : {}", e.getMessage());
            log.error("exception : {}", request.getAttribute("exception"));
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
        }
        request.setAttribute("userCode", userCode);
        log.error("jwt last exception : {},{}", request.getAttribute("exception"), request.getAttribute("userCode"));
        filterChain.doFilter(request, response);
    }
}

