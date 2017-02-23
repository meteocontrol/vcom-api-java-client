package com.meteocontrol.client.models;

import java.util.Date;

public class Comment {
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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Comment))
            return false;
        Comment p = (Comment) obj;
        return p.getCommentId() == this.commentId &&
                p.getDate().equals(this.date) &&
                p.getComment().equals(this.comment) &&
                p.getUsername().equals(this.username);
    }
}
