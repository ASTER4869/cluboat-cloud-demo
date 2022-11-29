package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("club_admin")
public class ClubAdmin {

    /* === 数据成员 ===*/
    private long userId;
    private long clubId;
    private long permission;

    /* === 方法 === */
    public long getUserId() {
    return userId;
  }

    public void setUserId(long userId) {
    this.userId = userId;
  }


    public long getClubId() {
    return clubId;
  }

    public void setClubId(long clubId) {
    this.clubId = clubId;
  }


    public long getPermission() {
    return permission;
  }

    public void setPermission(long permission) {
    this.permission = permission;
  }

}
