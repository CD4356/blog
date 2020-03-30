package com.cd.blog.dao;

import com.cd.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/24 17:11
 */
@Repository
public interface CommentDao {

    //获取评论列表
    List<Comment> queryCommentListByBlogId(Integer blogId);

    //根据博客id获取该博客的一级评论（即parentId为null的评论）
    List<Comment> queryCommentListByBlogIdAndParentIdNull(Integer blogId);

    //获取父级评论的昵称
    String queryParentNickName(Integer parentId);

    //根据评论id获取评论信息
    Comment queryCommentById(Integer commentId);

    //根据父级评论id获取子级评论列表
    List<Comment> queryCommentByParentId(Integer commentId);

    //保存评论
    int insertComment(Comment comment);

}
