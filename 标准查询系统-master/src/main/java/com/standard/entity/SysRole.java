package com.standard.entity;

import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_role", schema = "library", catalog = "")
public class SysRole extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = 20)
    private String name;
    @Basic
    @Column(name = "code", nullable = true, length = 100)
    private String code;
    @Basic
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time", nullable = true)
    private Timestamp updateTime;
    @Basic
    @Column(name = "remark", nullable = true, length = 500)
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole sysRole = (SysRole) o;
        return id == sysRole.id && Objects.equals(name, sysRole.name) && Objects.equals(code, sysRole.code) && Objects.equals(createTime, sysRole.createTime) && Objects.equals(updateTime, sysRole.updateTime) && Objects.equals(remark, sysRole.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createTime, updateTime, remark);
    }
}
