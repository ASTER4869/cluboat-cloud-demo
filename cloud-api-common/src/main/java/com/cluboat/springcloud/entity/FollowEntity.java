package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "follow", schema = "cluboat", catalog = "")
@IdClass(FollowEntityPK.class)
public class FollowEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "activity_id")
    private int activityId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowEntity that = (FollowEntity) o;
        return activityId == that.activityId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, userId);
    }
}
