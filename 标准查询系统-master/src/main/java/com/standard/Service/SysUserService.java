package com.standard.Service;

import com.standard.entity.Admin;
import com.standard.entity.ReaderInfo;

import java.sql.Date;
import java.util.List;

public interface SysUserService {

    void update(String name, String sex, Date birth, String address, String phone, Long readerId) ;

    void add(Long readerId, String name, String sex, Date birth, String address, String phone) ;

    void delete(Long serNum);

    List<ReaderInfo> List() ;



    Admin getByUserName(String username);


    String getAuthorityInfo(long userId);
}
