package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.FollowParam;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TableName("follow")
@Table(name = "follow", schema = "cluboat", catalog = "")
@IdClass(FollowEntityPK.class)
public class FollowEntity {
    @MppMultiId
    @TableField(value = "activity_id")
    @Column(name = "activity_id")
    private int activityId;
    @MppMultiId
    @TableField(value = "user_id")
    @Column(name = "user_id")
    private int userId;

    public void setFollow(FollowParam followParam) {
        this.activityId = followParam.activityId;
        this.userId = followParam.userId;
    }


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
