package com.standard.Service.Imp;

import com.standard.Jpa.LibUserJpa;
import com.standard.Jpa.SysMenuJpa;
import com.standard.Jpa.SysRoleJpa;
import com.standard.Jpa.SysUserJpa;
import com.standard.Service.SysUserService;
import com.standard.entity.Admin;
import com.standard.entity.ReaderInfo;
import com.standard.entity.SysMenu;
import com.standard.entity.SysRole;
import com.standard.untils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SysUserServiceImp implements SysUserService {

    SysUserJpa sysUserJpa;

    LibUserJpa libUserJpa;

    SysRoleJpa sysRoleJpa;

    SysMenuJpa sysMenuJpa;

    @Override
    public void update(String name, String sex, Date birth, String address, String phone, Long readerId) {
        sysUserJpa.update(name,sex,birth,address,phone,readerId);
    }

    @Override
    public void add(Long readerId, String name, String sex, Date birth, String address, String phone) {

        sysUserJpa.add(readerId,name,sex,birth,address,phone);
    }

    @Override
    public void delete(Long serNum) {

        sysUserJpa.deleteOne(serNum);

    }

    @Override
    public List<ReaderInfo> List() {
        return sysUserJpa.List();
    }



    @Override
    public Admin getByUserName(String username) {
        return libUserJpa.findByName(username);
    }

    @Override
    public String getAuthorityInfo(long userId) {
        StringBuffer authority = new StringBuffer();
        authority.append("ROLE_admin,ROLE_common,system:user:resetPwd,system:role:delete,sytem:user:list,system:menu:query");
        //根据用户id获取所有角色信息
//        List<SysRole> roleList = sysRoleJpa.ListRole(userId);

//        if(roleList.size()>0){
//            String roleCode = roleList.stream().map(r ->"ROLE_"+r.getCode()).collect(Collectors.joining(","));
//            authority.append(roleCode);
//        }
        //遍历所有角色，获取所有菜单权限，而且不重复

        Set<String> menuCode =new HashSet<String>();
//        for(SysRole sysRole:roleList){
//
//            List<SysMenu> menus = sysMenuJpa.ListMenu(sysRole.getId());
//            for(SysMenu menu:menus){
//                String perms = menu.getPerms();
//                if(StringUtil.isNotEmpty(perms)){
//                    menuCode.add(perms);
//                }
//            }
//        }

//        if(menuCode.size()>0){
//            authority.append(",");
//
//            String menuCodeStrs = menuCode.stream().collect(Collectors.joining(","));
//
//            authority.append(menuCodeStrs);
//        }
//        System.out.println("authority: "+authority.toString());
        return authority.toString();
    }
}
