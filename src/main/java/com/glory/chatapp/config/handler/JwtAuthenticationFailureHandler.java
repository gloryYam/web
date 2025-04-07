package com.glory.chatapp.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.exception.ErrorCode_401;
import com.glory.chatapp.exception.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.error("인증오류 : 아이디 혹은 비밀번호가 올바르지 않습니다.");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("401")
                .message(ErrorCode_401.INVALID_CREDENTIALS_EXCEPTION.getMessage())
                .status(ErrorCode_401.INVALID_CREDENTIALS_EXCEPTION.getStatus())
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
