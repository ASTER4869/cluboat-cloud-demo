package com.cluboat.springcloud.entity.apply;

<<<<<<< Updated upstream
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

=======
>>>>>>> Stashed changes
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
<<<<<<< Updated upstream
@TableName("join_apply")
=======
>>>>>>> Stashed changes
@Table(name = "join_apply", schema = "cluboat", catalog = "")
public class JoinApplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
<<<<<<< Updated upstream
    @TableId(value="join_apply_id",type = IdType.AUTO)
=======
>>>>>>> Stashed changes
    @Column(name = "join_apply_id")
    private int joinApplyId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "join_club_id")
    private int joinClubId;
    @Basic
    @Column(name = "join_apply_content")
    private String joinApplyContent;
    @Basic
    @Column(name = "join_apply_time")
    private Timestamp joinApplyTime;
    @Basic
    @Column(name = "join_apply_is_pass")
    private byte joinApplyIsPass;

    public int getJoinApplyId() {
        return joinApplyId;
    }

    public void setJoinApplyId(int joinApplyId) {
        this.joinApplyId = joinApplyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJoinClubId() {
        return joinClubId;
    }

    public void setJoinClubId(int joinClubId) {
        this.joinClubId = joinClubId;
    }

    public String getJoinApplyContent() {
        return joinApplyContent;
    }

    public void setJoinApplyContent(String joinApplyContent) {
        this.joinApplyContent = joinApplyContent;
    }

    public Timestamp getJoinApplyTime() {
        return joinApplyTime;
    }

    public void setJoinApplyTime(Timestamp joinApplyTime) {
        this.joinApplyTime = joinApplyTime;
    }

    public byte getJoinApplyIsPass() {
        return joinApplyIsPass;
    }

    public void setJoinApplyIsPass(byte joinApplyIsPass) {
        this.joinApplyIsPass = joinApplyIsPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinApplyEntity that = (JoinApplyEntity) o;
        return joinApplyId == that.joinApplyId && userId == that.userId && joinClubId == that.joinClubId && joinApplyIsPass == that.joinApplyIsPass && Objects.equals(joinApplyContent, that.joinApplyContent) && Objects.equals(joinApplyTime, that.joinApplyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinApplyId, userId, joinClubId, joinApplyContent, joinApplyTime, joinApplyIsPass);
    }
}
