package com.glory.chatapp.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.config.security.UserPrincipal;
import com.glory.chatapp.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserPrincipal principalData = (UserPrincipal) authentication.getPrincipal();

        String userId = principalData.getUsername();

        List<String> roles = principalData.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String accessToken = jwtProvider.createAccessToken(userId, roles);
        String refreshToken = jwtProvider.createRefreshToken(userId, roles);



        HashMap<String, Object> body = new HashMap<>();
        body.put("accessToken", accessToken);
        body.put("refreshToken", refreshToken);
        body.put("userId", userId);
        body.put("roles", roles);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), body);
    }
}
