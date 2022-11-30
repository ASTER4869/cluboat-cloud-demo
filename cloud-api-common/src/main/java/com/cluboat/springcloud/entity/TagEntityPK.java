package com.cluboat.springcloud.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TagEntityPK implements Serializable {
    @Column(name = "club_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clubId;
    @Column(name = "tag_name")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tagName;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntityPK that = (TagEntityPK) o;
        return clubId == that.clubId && Objects.equals(tagName, that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, tagName);
    }
}
