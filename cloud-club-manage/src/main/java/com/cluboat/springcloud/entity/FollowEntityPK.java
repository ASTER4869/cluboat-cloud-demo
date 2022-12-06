package com.cluboat.springcloud.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FollowEntityPK implements Serializable {
    @Column(name = "activity_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        FollowEntityPK that = (FollowEntityPK) o;
        return activityId == that.activityId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, userId);
    }
}
