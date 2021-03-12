package com.mooc.moocServer.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {
    private JwtTokenProvider jwtTokenProvider;

    // Jwt Provider 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request 로 들어오는 Jwt Token 의 유효성을 검증(jwtTokenProvider.validateToken) 하는 filter 를 filterChain에 등록
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // get token from request header
        String token  = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // if token is valid
        if(token != null && jwtTokenProvider.validToken(token)){
            // get data from token
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            // store Authentication object at SecurityContext
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
