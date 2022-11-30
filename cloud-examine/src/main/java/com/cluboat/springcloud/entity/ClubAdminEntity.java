package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "club_admin", schema = "cluboat", catalog = "")
@IdClass(ClubAdminEntityPK.class)
public class ClubAdminEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "permission")
    private byte permission;

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

    public byte getPermission() {
        return permission;
    }

    public void setPermission(byte permission) {
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
