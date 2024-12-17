package com.glory.chatapp.api.controller.member;

import com.glory.chatapp.api.ApiResponse;
import com.glory.chatapp.api.controller.member.request.LoginRequest;
import com.glory.chatapp.api.service.member.response.SignResponse;
import com.glory.chatapp.api.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign")
    public ApiResponse<SignResponse> signup(@Valid @RequestBody LoginRequest loginRequest) {
        return ApiResponse.ok(memberService.signup(loginRequest.toServiceDto()));
    }

}
