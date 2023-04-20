package com.standard.Common.Security;

import cn.hutool.json.JSONUtil;
import com.standard.untils.JwtUtils;
import com.standard.untils.Res;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登陆成功处理器
 */
@Component
public class loginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream outputStream = response.getOutputStream();

        String message = exception.getMessage();

        System.out.println(message);
        if(exception instanceof BadCredentialsException){
            message = "用户名或者密码错误!2333";
        }

        outputStream.write(JSONUtil.toJsonStr(Res.error(message)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
