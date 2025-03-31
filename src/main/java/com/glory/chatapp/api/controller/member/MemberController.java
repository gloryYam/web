package com.glory.chatapp.api.controller.member;

import com.glory.chatapp.api.ApiResponse;
import com.glory.chatapp.api.controller.member.request.RegisterRequest;
import com.glory.chatapp.api.service.member.MemberService;
import com.glory.chatapp.api.service.member.response.SignResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/potato/register")
    public ApiResponse<SignResponse> emailRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        log.info("emailRegister request @@@@@ : {}", registerRequest);
        return ApiResponse.ok(memberService.emailRegister(registerRequest.toServiceDto()));
    }
}

