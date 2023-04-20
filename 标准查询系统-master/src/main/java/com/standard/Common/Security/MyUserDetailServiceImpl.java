package com.standard.Common.Security;

import com.standard.Common.Exception.UserCountLockException;
import com.standard.Service.SysUserService;
import com.standard.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = sysUserService.getByUserName(username);

        if(admin==null){
            throw new UsernameNotFoundException("用户名或者密码错误!");
        }else if ("1".equals(admin.getName())){//这里设置一个假的，此处可以代表是否处于封禁状态
            try {
                throw new UserCountLockException("用户账号被封禁!");
            } catch (UserCountLockException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(admin.getName());
        System.out.println(admin.getPassword());

        return new User(admin.getName(), new BCryptPasswordEncoder().encode(admin.getPassword()), getAuthority(admin.getAdmin_id()));
    }

    public List<GrantedAuthority> getAuthority(long userId) {
        String authority = sysUserService.getAuthorityInfo(userId);
        //格式:ROLE_admin,ROLE_common,system:user:resetPwd,system:role:delete,sytem:user:list,system:menu:query
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
