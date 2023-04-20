package com.standard.Service.Imp;


import com.standard.Jpa.SysRoleJpa;
import com.standard.Service.SysRoleService;
import com.standard.entity.SysRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SysRoleServiceImp implements SysRoleService {
    SysRoleJpa sysRoleJpa;
    @Override
    public List<SysRole> ListRole(Long id) {
        return sysRoleJpa.ListRole(id);
    }
}
