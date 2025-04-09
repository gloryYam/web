package com.glory.chatapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glory.chatapp.api.controller.auth.AuthController;
import com.glory.chatapp.service.Auth.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        AuthController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; // 역직렬화 과정을 할 때 objectMapper 은 기본 생성자를 쓴다.

    @MockBean
    protected MemberService memberService;
}
