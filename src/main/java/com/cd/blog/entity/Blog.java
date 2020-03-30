package com.cd.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Blog {

    /*自增id*/
    private Integer blogId;
    /*文章标题*/
    private String title;
    /*文章内容*/
    private String content;
    /*文章描述*/
    private String description;
    /*首图*/
    private String firstPicture;
    /*文章标识: 原创、转载、翻译*/
    private String flag;
    /*浏览次数*/
    private int views;
    /*打赏开启*/
    private boolean appreciation;
    /*版权开启*/
    private boolean shareStatement;
    /*评论开启*/
    private boolean commentStatus;
    /*发布*//*博客状态：0 草稿，1 发布*/  /*1是true，0是false*/
    private boolean published;
    /*是否推荐*/
    private boolean recommend;
    /*创建时间*/
    //使用@JsonFormat注解格式化日期，才能按指定格式在前端页面  /*具体参考：https://www.jb51.net/article/144235.htm */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date createTime;
    /*最近更新时间*/
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastEditTime;


//下面这些属性用来在mybatis中进行关联查询
    /*作者：多对一关系*/
    private User user;
    /*分类：多对一关系*/
    private Type type;
    /*标签: 一对多关系*/
    private List<Tag> tagList;
    /*评论：一对多关系*/
    private List<Comment> commentList;

    /*获取多个标签的id*/
    private String tagIds;


    //{1,2,3} --> "1,2,3"
    public String tagsToIds(List<Tag> tags){
        if(tags != null){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag : tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getTagId());
            }
            return ids.toString();
        }else {
            return "tagIds";
        }
    }


    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

}
