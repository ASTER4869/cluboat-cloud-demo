package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("sys_admin")
@Table(name = "sys_admin", schema = "cluboat", catalog = "")
public class SysAdminEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("admin_id")
    @Column(name = "admin_id")
    private int adminId;
    @Basic
    @Column(name = "admin_password")
    private String adminPassword;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysAdminEntity that = (SysAdminEntity) o;
        return adminId == that.adminId && Objects.equals(adminPassword, that.adminPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, adminPassword);
    }
}
