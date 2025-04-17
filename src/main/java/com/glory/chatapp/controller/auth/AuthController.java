package com.glory.chatapp.controller.auth;

import com.glory.chatapp.controller.ApiResponse;
import com.glory.chatapp.controller.auth.request.RegisterRequest;
import com.glory.chatapp.service.Auth.MemberService;
import com.glory.chatapp.service.Auth.response.SignResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Getter
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/potato/register")
    public ApiResponse<SignResponse> emailRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        return ApiResponse.ok(memberService.emailRegister(registerRequest.toServiceDto()));
    }

}

