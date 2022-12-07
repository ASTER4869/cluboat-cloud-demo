package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("pubnot")
@Table(name = "pubnot", schema = "cluboat", catalog = "")
public class PubnotEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("pubnot_id")
    @Column(name = "pubnot_id")
    private int pubnotId;
    @Basic
    @Column(name = "admin_id")
    private int adminId;
    @Basic
    @Column(name = "pubnot_title")
    private String pubnotTitle;
    @Basic
    @Column(name = "pubnot_time")
    private Timestamp pubnotTime;
    @Basic
    @Column(name = "pubnot_content")
    private String pubnotContent;

    public int getPubnotId() {
        return pubnotId;
    }

    public void setPubnotId(int pubnotId) {
        this.pubnotId = pubnotId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPubnotTitle() {
        return pubnotTitle;
    }

    public void setPubnotTitle(String pubnotTitle) {
        this.pubnotTitle = pubnotTitle;
    }

    public Timestamp getPubnotTime() {
        return pubnotTime;
    }

    public void setPubnotTime(Timestamp pubnotTime) {
        this.pubnotTime = pubnotTime;
    }

    public String getPubnotContent() {
        return pubnotContent;
    }

    public void setPubnotContent(String pubnotContent) {
        this.pubnotContent = pubnotContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PubnotEntity that = (PubnotEntity) o;
        return pubnotId == that.pubnotId && adminId == that.adminId && Objects.equals(pubnotTitle, that.pubnotTitle) && Objects.equals(pubnotTime, that.pubnotTime) && Objects.equals(pubnotContent, that.pubnotContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pubnotId, adminId, pubnotTitle, pubnotTime, pubnotContent);
    }
}
