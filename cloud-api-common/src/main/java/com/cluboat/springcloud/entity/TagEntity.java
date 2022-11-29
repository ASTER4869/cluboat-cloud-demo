package com.cluboat.springcloud.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag", schema = "cluboat", catalog = "")
@IdClass(TagEntityPK.class)
public class TagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "club_id")
    private int clubId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tag_name")
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
        TagEntity tagEntity = (TagEntity) o;
        return clubId == tagEntity.clubId && Objects.equals(tagName, tagEntity.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubId, tagName);
    }
}
