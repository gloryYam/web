package com.glory.chatapp.api.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.api.controller.auth.request.RegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * https://velog.io/@qwd101/Spring-Boot-%EC%97%90%EC%84%9C-MemberController-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0
 * contentType 과 accept 의 차이
 * SpringBootTest 와 WebMvcTest 의 차이에 대해서 알아보자.
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    @DisplayName("신규 회원 등록")
    void createMember() throws Exception {
        // given
        RegisterRequest request = RegisterRequest.builder()
                .username("testUser")
                .password("password123")
                .build();

        // when & then
        mockMvc.perform(post("/sign")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request))
                        .accept(APPLICATION_JSON)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }
}