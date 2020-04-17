package com.cd.blog.service;

import com.cd.blog.entity.Comment;

import java.util.List;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/24 17:14
 */
public interface CommentService {

    //获取评论列表
//    List<Comment> getCommentListByBlogId(Integer commentId);

    //根据博客id获取该博客的一级评论（即parentId为null的评论）
    List<Comment> getCommentListByBlogIdAndParentIdNull(Integer blogId);

    //保存评论
    int addComment(Comment comment);

    //获取所有评论数量，用于前台首页
    int getCommentCount();
}
