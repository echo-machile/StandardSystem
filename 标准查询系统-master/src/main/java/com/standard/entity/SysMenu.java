package com.standard.entity;

import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sys_menu", schema = "library", catalog = "")
public class SysMenu extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "name", nullable = true, length = 20)
    private String name;
    @Basic
    @Column(name = "icon", nullable = true, length = 100)
    private String icon;
    @Basic
    @Column(name = "parent_id", nullable = true)
    private Long parentId;
    @Basic
    @Column(name = "order_num", nullable = true)
    private Integer orderNum;
    @Basic
    @Column(name = "path", nullable = true, length = 200)
    private String path;
    @Basic
    @Column(name = "component", nullable = true, length = 200)
    private String component;
    @Basic
    @Column(name = "menu_type", nullable = true, length = 1)
    private String menuType;
    @Basic
    @Column(name = "perms", nullable = true, length = 100)
    private String perms;
    @Basic
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;
    @Basic
    @Column(name = "update_time", nullable = true)
    private Timestamp updateTime;
    @Basic
    @Column(name = "remark", nullable = true, length = 500)
    private String remark;


    @Transient
    private ArrayList<SysMenu> children = new ArrayList<SysMenu>();

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
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
        SysMenu sysMenu = (SysMenu) o;
        return id == sysMenu.id && Objects.equals(name, sysMenu.name) && Objects.equals(icon, sysMenu.icon) && Objects.equals(parentId, sysMenu.parentId) && Objects.equals(orderNum, sysMenu.orderNum) && Objects.equals(path, sysMenu.path) && Objects.equals(component, sysMenu.component) && Objects.equals(menuType, sysMenu.menuType) && Objects.equals(perms, sysMenu.perms) && Objects.equals(createTime, sysMenu.createTime) && Objects.equals(updateTime, sysMenu.updateTime) && Objects.equals(remark, sysMenu.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, icon, parentId, orderNum, path, component, menuType, perms, createTime, updateTime, remark);
    }

    public List<SysMenu> getChildren() {
        return this.children;
    }
}
