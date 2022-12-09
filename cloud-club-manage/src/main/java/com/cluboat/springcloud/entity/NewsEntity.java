package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cluboat.springcloud.entity.param.ClubParam;
import com.cluboat.springcloud.entity.param.NewsParam;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@TableName("news")
@Table(name = "news", schema = "cluboat", catalog = "")
public class NewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId(value="news_id",type = IdType.AUTO)
    @Column(name = "news_id")
    private int newsId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "club_id")
    private int clubId;
    @Basic
    @Column(name = "news_title")
    private String newsTitle;
    @Basic
    @Column(name = "news_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp newsTime;
    @Basic
    @Column(name = "news_content")
    private String newsContent;

    /* ===================== */

    public void setNews(NewsParam newsParam) {
        this.userId = newsParam.userId;
        this.clubId = newsParam.clubId;
        this.newsTitle = newsParam.newsTitle;
        this.newsContent = newsParam.newsContent;
        this.newsTime = newsParam.newsTime;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

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

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Timestamp getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(Timestamp newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return newsId == that.newsId && userId == that.userId && clubId == that.clubId && Objects.equals(newsTitle, that.newsTitle) && Objects.equals(newsTime, that.newsTime) && Objects.equals(newsContent, that.newsContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, userId, clubId, newsTitle, newsTime, newsContent);
    }
}
