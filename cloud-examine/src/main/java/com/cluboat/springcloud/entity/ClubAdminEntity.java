package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "club_admin", schema = "cluboat", catalog = "")
@TableName("club_admin")
@IdClass(ClubAdminEntityPK.class)
public class ClubAdminEntity {

    @MppMultiId
    @TableField(value = "user_id")
    @Column(name = "user_id")
    private int userId;

    @MppMultiId
    @TableField(value = "club_id")
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @TableField(value = "permission")
    @Column(name = "permission")
    private int permission;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubAdminEntity that = (ClubAdminEntity) o;
        return userId == that.userId && clubId == that.clubId && permission == that.permission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, clubId, permission);
    }
}
