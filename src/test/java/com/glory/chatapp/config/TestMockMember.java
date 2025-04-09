package com.glory.chatapp.config;

import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = TestMockSecurityContext.class)
public @interface TestMockMember {

    String nickname() default "감자";

    String username() default "testUsername";

    String password() default "";


}
