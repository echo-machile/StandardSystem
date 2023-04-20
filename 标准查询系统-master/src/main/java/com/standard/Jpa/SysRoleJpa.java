package com.standard.Jpa;

import com.standard.entity.Admin;
import com.standard.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleJpa extends JpaRepository<SysRole,Integer> {

    @Query(value="select * from sys_role where id in (select role_id from sys_user_role where user_id = ?1)",nativeQuery = true)
    List<SysRole> ListRole(Long id);
}
