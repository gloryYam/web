package com.glory.chatapp.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.config.security.UserPrincipal;
import com.glory.chatapp.config.security.process.LoginSuccessProcessor;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.jwt.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationSuccessHandlerTest {

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private LoginSuccessProcessor loginSuccessProcessor;

    @InjectMocks
    private JwtAuthenticationSuccessHandler successHandler;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("인증 발급시 JWT 발급 및 JSON 응답")
    void issueJwtAndWriteJsonResponse() throws ServletException, IOException {

        MockHttpServletResponse response = new MockHttpServletResponse();
        HttpServletRequest request = mock(HttpServletRequest.class);

        UserPrincipal userPrincipal = new UserPrincipal(new Member(
                "testUsername", "test"),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

        when(jwtProvider.createAccessToken(any(), any())).thenReturn("ACCESS-TOKEN");
        when(jwtProvider.createRefreshToken(any(), any())).thenReturn("REFRESH-TOKEN");

        successHandler = new JwtAuthenticationSuccessHandler(objectMapper, jwtProvider, loginSuccessProcessor);

        // when
        successHandler.onAuthenticationSuccess(request, response, authentication);

        // then
        String result = response.getContentAsString();
        System.out.println("응답 JSON: " + result);

        assertThat(result).contains("ACCESS-TOKEN");
        assertThat(result).contains("REFRESH-TOKEN");
        assertEquals(200, response.getStatus());
    }

}