package com.housing.finance.authentication;

import com.housing.finance.common.JWTManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthenticationAspect {

    private final JWTManager jwtManager;

    public AuthenticationAspect(JWTManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    @Before(value = "execution(public * com.housing.finance.bank.controller.HousingFinanceController.*(..))")
    public void checkJWT() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes())
                        .getRequest();

        String token = request.getHeader("Authorization");

        jwtManager.authenticate(token);
    }
}
