package com.meteocontrol.client.models;

import com.meteocontrol.client.models.annotation.ModelProperty;

import java.util.Date;

public class Comment extends BaseModel {
    private int commentId;
    private Date date;
    private String comment;
    private String username;

    public Comment() {
    }

    public Comment(int commentId, Date date, String comment, String username) {
        this.commentId = commentId;
        this.date = date;
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

    @ModelProperty
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

}
