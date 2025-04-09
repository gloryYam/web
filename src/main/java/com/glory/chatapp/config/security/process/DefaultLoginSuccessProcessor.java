package com.glory.chatapp.config.security.process;

import com.glory.chatapp.service.Auth.LoginService;
import com.glory.chatapp.service.Auth.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultLoginSuccessProcessor implements LoginSuccessProcessor {

    private final LoginService loginService;

    @Override
    public void process(String usernmae) {
        loginService.updateLastLoginTime(usernmae);
    }
}
