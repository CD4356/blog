package com.cd.blog.entity;

import java.util.Date;
import java.util.List;

public class Comment {

    /*自增id*/
    private Integer commentId;
    /*昵称*/
    private String nickName;
    /*邮箱*/
    private String email;
    /*头像*/
    private String avatar;
    /*评论内容*/
    private String content;
    /*评论时间*/
    private Date createTime;

    /*博文：多对一关系*/
    private Blog blog;
    /*自关联关系（父类）*/
    private Comment parent;
    /*后代评论*/
    private List<Comment> replyComments;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
}
