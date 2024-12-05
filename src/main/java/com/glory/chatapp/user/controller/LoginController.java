package com.glory.chatapp.user.controller;

import com.glory.chatapp.user.model.dto.request.LoginRequest;
import com.glory.chatapp.user.model.dto.response.LoginResponse;
import com.glory.chatapp.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping
    public LoginResponse signup(@Valid @RequestBody LoginRequest loginRequest) {
        memberService.signup(loginRequest.toServiceDto());

    }

}

