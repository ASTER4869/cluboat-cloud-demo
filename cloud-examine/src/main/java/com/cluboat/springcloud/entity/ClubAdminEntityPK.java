package com.cluboat.springcloud.entity;

<<<<<<< Updated upstream
import lombok.Data;

=======
>>>>>>> Stashed changes
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
<<<<<<< Updated upstream
@Data
=======

>>>>>>> Stashed changes
public class ClubAdminEntityPK implements Serializable {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "club_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clubId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubAdminEntityPK that = (ClubAdminEntityPK) o;
        return userId == that.userId && clubId == that.clubId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, clubId);
    }
}
