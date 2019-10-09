package com.housing.finance.authentication;

import com.housing.finance.common.JWTManager;
import com.housing.finance.authentication.exception.FailAuthenticationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("execution(public * com.housing.finance.supportamount.ui.SupportAmountController.*(..))")
    public void housingFinanceController() {
    }

    @Before(value = "housingFinanceController()")
    public void checkJWT() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes())
                        .getRequest();

        String token = request.getHeader("Authorization");

        if (isEmptyToken(token)) {
            throw new FailAuthenticationException();
        }

        jwtManager.authenticate(token);
    }

    private boolean isEmptyToken(String token) {
        return token == null;
    }
}
