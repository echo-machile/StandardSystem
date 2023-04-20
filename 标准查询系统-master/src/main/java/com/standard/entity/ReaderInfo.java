package com.standard.entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reader_info", schema = "library", catalog = "")
public class ReaderInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reader_id", nullable = false)
    private long readerId;
    @Basic
    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @Basic
    @Column(name = "sex", nullable = false, length = 2)
    private String sex;
    @Basic
    @Column(name = "birth", nullable = false)
    private Date birth;
    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Basic
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderInfo that = (ReaderInfo) o;
        return readerId == that.readerId && Objects.equals(name, that.name) && Objects.equals(sex, that.sex) && Objects.equals(birth, that.birth) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerId, name, sex, birth, address, phone);
    }
}
