package com.standard.Common.Security;

import cn.hutool.json.JSONUtil;
import com.standard.untils.Res;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream outputStream = response.getOutputStream();


        outputStream.write(JSONUtil.toJsonStr(Res.error(HttpServletResponse.SC_UNAUTHORIZED,"认证失败，请登录！")).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
