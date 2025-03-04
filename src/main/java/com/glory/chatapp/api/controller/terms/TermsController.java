package com.glory.chatapp.api.controller.terms;


import com.glory.chatapp.api.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TermsController {

    @PostMapping("/potato/terms/agree")
    public ApiResponse<TermsResponse> termsAgree() {

    }
}
