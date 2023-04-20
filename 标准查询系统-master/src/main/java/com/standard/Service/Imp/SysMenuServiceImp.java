package com.standard.Service.Imp;


import com.standard.Jpa.SysMenuJpa;
import com.standard.Service.SysMenuService;
import com.standard.entity.SysMenu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SysMenuServiceImp implements SysMenuService {
    SysMenuJpa sysMenuJpa;
    @Override
    public List<SysMenu> List(Long id) {
        return sysMenuJpa.ListMenu(id);
    }

    @Override
    public List<SysMenu> buildTreeMenu(List<SysMenu> sysMenus) {

        List<SysMenu> menuList = new ArrayList<>();

        for(SysMenu sysMenu:sysMenus){

            for(SysMenu e: sysMenus){
                if(e.getParentId()==sysMenu.getId()){
                    sysMenu.getChildren().add(e);
                }
            }
            if(sysMenu.getParentId()==0L){
                menuList.add(sysMenu);
            }
        }
        return menuList;
    }
}
