package com.glory.chatapp.controller.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.controller.auth.request.LoginRequest;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAllInBatch();
        Member member = Member.of("glory", "test", passwordEncoder.encode("test123"));
        memberRepository.save(member);
        System.out.println("@@@@   member: " + member.getUsername());
    }

    @Test
    @DisplayName("JWT 로그인 성공 시 accessToken, refreshToken 반환")
    void loginSuccessTest() throws Exception {

        LoginRequest loginRequest = new LoginRequest("test", "test123");

        mockMvc.perform(post("/potato/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists())
                .andExpect(jsonPath("$.username").value("test"))
                .andExpect(jsonPath("$.roles").isArray());
    }
}
