package com.cluboat.springcloud.entity;

<<<<<<< Updated upstream
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.ClubParam;

=======
>>>>>>> Stashed changes
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
<<<<<<< Updated upstream
@TableName("club")
=======
>>>>>>> Stashed changes
@Table(name = "club", schema = "cluboat", catalog = "")
public class ClubEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
<<<<<<< Updated upstream
    @TableId(value="club_id",type = IdType.AUTO)
=======
>>>>>>> Stashed changes
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "club_name")
    private String clubName;
    @Basic
<<<<<<< Updated upstream
    @Column(name = "club_information")
    @TableField(value="club_information")
    private String clubInformation;
    @Basic
    @Column(name = "club_imageURL")
    @TableField(value="club_imageURL")
=======
    @Column(name = "clun_information")
    private String clunInformation;
    @Basic
    @Column(name = "club_imageURL")
>>>>>>> Stashed changes
    private String clubImageUrl;
    @Basic
    @Column(name = "club_create_time")
    private Timestamp clubCreateTime;

<<<<<<< Updated upstream
    public void setClub(ClubParam clubParam) {
        this.clubName = clubParam.clubName;
        this.clubInformation = clubParam.clubInformation;
        this.clubImageUrl = clubParam.clubImageUrl;
        this.clubCreateTime = clubParam.clubCreateTime;
    }
=======
>>>>>>> Stashed changes
    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

<<<<<<< Updated upstream
    public String getClubInformation() {
        return clubInformation;
    }

    public void setClubInformation(String clunInformation) {
        this.clubInformation = clunInformation;
=======
    public String getClunInformation() {
        return clunInformation;
    }

    public void setClunInformation(String clunInformation) {
        this.clunInformation = clunInformation;
>>>>>>> Stashed changes
    }

    public String getClubImageUrl() {
        return clubImageUrl;
    }

    public void setClubImageUrl(String clubImageUrl) {
        this.clubImageUrl = clubImageUrl;
    }

    public Timestamp getClubCreateTime() {
        return clubCreateTime;
    }

    public void setClubCreateTime(Timestamp clubCreateTime) {
        this.clubCreateTime = clubCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubEntity that = (ClubEntity) o;
<<<<<<< Updated upstream
        return clubId == that.clubId && Objects.equals(clubName, that.clubName) && Objects.equals(clubInformation, that.clubInformation) && Objects.equals(clubImageUrl, that.clubImageUrl) && Objects.equals(clubCreateTime, that.clubCreateTime);
=======
        return clubId == that.clubId && Objects.equals(clubName, that.clubName) && Objects.equals(clunInformation, that.clunInformation) && Objects.equals(clubImageUrl, that.clubImageUrl) && Objects.equals(clubCreateTime, that.clubCreateTime);
>>>>>>> Stashed changes
    }

    @Override
    public int hashCode() {
<<<<<<< Updated upstream
        return Objects.hash(clubId, clubName, clubInformation, clubImageUrl, clubCreateTime);
=======
        return Objects.hash(clubId, clubName, clunInformation, clubImageUrl, clubCreateTime);
>>>>>>> Stashed changes
    }
}
