package com.standard.Common.Security;

import cn.hutool.json.JSONUtil;
import com.standard.Service.SysMenuService;
import com.standard.Service.SysRoleService;
import com.standard.Service.SysUserService;
import com.standard.entity.Admin;
import com.standard.entity.SysMenu;
import com.standard.entity.SysRole;
import com.standard.untils.JwtUtils;
import com.standard.untils.Res;
import com.standard.untils.StringUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登陆成功处理器
 */
@Component
public class loginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream outputStream = response.getOutputStream();

        String username = authentication.getName();
        String token = JwtUtils.genJwtToken(username);

        System.out.println("最终下发的签名: "+token);
        Admin  admin = sysUserService.getByUserName(username);

        Res r = (Res) Res.ok("登陆成功");
        r.put("authorization", token);
        r.put("currentUser",admin);


        StringBuffer authority = new StringBuffer();
        //根据用户id获取所有角色信息
        //List<SysRole> roleList = sysRoleService.ListRole((long) admin.getAdmin_id());


        //遍历所有角色，获取所有菜单权限，而且不重复

//        Set<SysMenu> menuSet =new HashSet<>();
//        for(SysRole sysRole:roleList){
//
//            List<SysMenu> menus = sysMenuService.List(sysRole.getId());
//            for(SysMenu menu:menus){
//                menuSet.add(menu);
//            }
//        }
//
//        List<SysMenu> sysMenus = new ArrayList<>(menuSet);
//
//        sysMenus.sort(Comparator.comparing(SysMenu::getOrderNum));
//
//
//        List<SysMenu> menuList = sysMenuService.buildTreeMenu(sysMenus);

        r.put("menuList",null);
        outputStream.write(JSONUtil.toJsonStr(r).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
