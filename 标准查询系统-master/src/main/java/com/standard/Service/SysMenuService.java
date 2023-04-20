package com.standard.Service;

import com.standard.entity.SysMenu;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> List(Long id);

    List<SysMenu> buildTreeMenu(List<SysMenu> sysMenus);
}
