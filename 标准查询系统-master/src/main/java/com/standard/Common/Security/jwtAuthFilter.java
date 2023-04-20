package com.standard.Common.Security;

import com.standard.Common.Constant.JwtConstant;
import com.standard.Service.SysUserService;
import com.standard.entity.Admin;
import com.standard.entity.checkResult;
import com.standard.untils.JwtUtils;
import com.standard.untils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class jwtAuthFilter  extends BasicAuthenticationFilter {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;


    private static final String[] URL_WHITELiST ={
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/images/**",

    };
    public jwtAuthFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {



        String token = request.getHeader("token");

        System.out.println(token);
        System.out.println("请求url: "+request.getRequestURI());


        //token是空或者url在白名单放行
        if(StringUtil.isEmpty(token) || new ArrayList<String>(Arrays.asList(URL_WHITELiST)).contains(request.getRequestURI())){
            chain.doFilter(request,response);

            return ;
        }

        checkResult check = JwtUtils.validateJWT(token);
        if(!check.isSuccess()){
            switch(check.getErrCode()){
                case JwtConstant.JWT_ERRCODE_NULL: throw new JwtException("token不存在");
                case JwtConstant.JWT_ERRCODE_FAIL: throw new JwtException("token验证不通过");
                case JwtConstant.JWT_ERRCODE_EXPIRE: throw new JwtException("token过期");
            }
        }

        Claims claims = JwtUtils.parseJWT(token);
        String userName = claims.getSubject();
        Admin admin = sysUserService.getByUserName(userName);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,null,myUserDetailService.getAuthority(admin.getAdmin_id()));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request,response);
    }

    public jwtAuthFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }
}
