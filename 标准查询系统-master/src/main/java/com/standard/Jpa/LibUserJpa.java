package com.standard.Jpa;

import com.standard.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LibUserJpa extends JpaRepository<Admin,Integer> {

    @Query(value = "select id from users where name=?1",nativeQuery = true)
    Long getIdByName(String name);


    @Query(value="select * from users where name=?1",nativeQuery = true)
    Admin findByName(String name);
}
