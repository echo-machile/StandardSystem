package com.standard.Jpa;

import com.standard.entity.SysMenu;
import com.standard.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuJpa extends JpaRepository<SysMenu,Integer> {

    @Query(value="select * from sys_menu where id in (select menu_id from sys_role_menu where role_id = ?1)",nativeQuery = true)
    List<SysMenu> ListMenu(Long id);
}
