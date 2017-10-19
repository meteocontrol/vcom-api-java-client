package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.Date;

public class CommentDetail extends BaseModel {
    private int commentId;
    @Deprecated
    private Date date;
    private Date createdAt;
    private String comment;
    private String username;

    public CommentDetail() {
    }

    public CommentDetail(int commentId, Date createdAt, String comment, String username) {
        this.commentId = commentId;
        this.createdAt = this.date = createdAt;
        this.comment = comment;
        this.username = username;
    }

    @ModelProperty
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Deprecated
    @ModelProperty
    public Date getDate() {
        return date;
    }

    @Deprecated
    public void setDate(Date date) {
        this.createdAt = this.date = date;
    }

    @ModelProperty
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = this.date = createdAt;
    }

    @ModelProperty
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ModelProperty
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid() {
        return !"".equals(this.comment);
    }
}
