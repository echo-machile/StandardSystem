package com.standard.Jpa;


import com.standard.entity.Admin;
import com.standard.entity.ReaderInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SysUserJpa extends JpaRepository<ReaderInfo,Integer>  {






    @Transactional
    @Query(value="update  reader_info set name=?1,sex=?2,birth=?3,address=?4 ,phone=?5 where ser_num=?6",nativeQuery = true)
    void update(String name, String sex, Date birth, String address, String phone, Long readerId);


    @Transactional
    @Query(value="INSERT INTO  reader_info(reader_id,name,sex,birth,address,phone) values (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    void add(Long readerId, String name, String sex, Date birth, String address, String phone);

    @Transactional
    @Query(value="delete from reader_info where reader_id=?1",nativeQuery = true)
    void deleteOne(Long serNum);

    @Query(value="select * from reader_info",nativeQuery = true)
    List<ReaderInfo> List();

}
