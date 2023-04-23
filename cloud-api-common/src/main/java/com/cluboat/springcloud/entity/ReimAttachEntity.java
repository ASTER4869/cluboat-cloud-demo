package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("reim_attach")
@Table(name = "reim_attach", schema = "cluboat", catalog = "")
public class ReimAttachEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("reim_attach_id")
    @Column(name = "reim_attach_id")
    private int reimAttachId;
    @Basic
    @Column(name = "reim_id")
    private int reimId;
    @Basic
    @Column(name = "attach_url")
    private String attachUrl;

    public int getReimAttachId() {
        return reimAttachId;
    }

    public void setReimAttachId(int reimAttachId) {
        this.reimAttachId = reimAttachId;
    }

    public int getReimId() {
        return reimId;
    }

    public void setReimId(int reimId) {
        this.reimId = reimId;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimAttachEntity that = (ReimAttachEntity) o;
        return reimAttachId == that.reimAttachId && reimId == that.reimId && Objects.equals(attachUrl, that.attachUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimAttachId, reimId, attachUrl);
    }
}
