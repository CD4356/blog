package com.cd.blog.service.impl;

import com.cd.blog.dao.CommentDao;
import com.cd.blog.entity.Comment;
import com.cd.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/24 17:15
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;


    @Transactional
    @Override
    public int addComment(Comment comment) {
        Integer parentId = comment.getParent().getCommentId();
        if(parentId != -1){
            comment.setParent(commentDao.queryCommentById(parentId));
        }else {
            comment.setParent(null);
        }
        comment.setCreateTime(new Date());

        int effectNum = commentDao.insertComment(comment);
        if(effectNum < 1){
            throw new RuntimeException("评论内容添加失败!");
        }
        return effectNum;
    }


    @Override
    public List<Comment> getCommentListByBlogIdAndParentIdNull(Integer blogId) {
        //获取顶级(一级)评论节点（即parentId为null的评论）
        List<Comment> comments = commentDao.queryCommentListByBlogIdAndParentIdNull(blogId);
        //合并评论的各层子代到第一级子代集合中
        return mergeChildren(comments);
    }


    //定义一个集合，临时存储每个一级评论下的所有后代评论
    private List<Comment> childrenComment = new ArrayList<>();


    //将后代节点合并到root根节点
    private List<Comment> mergeChildren(List<Comment> comments){
        //在迭代一级评论时，将其它各子层级的评论合并到一级评论中
        for(Comment comment : comments){  //遍历一级评论
            getAllChildren(comment);
            //将该一级评论的所有后代评论，都设置进Comment实体类的replyComments属性中
            comment.setReplyComments(childrenComment);
            //清除临时存放区，再用来存储下一个一级评论下的所有后代评论
            childrenComment = new ArrayList<>();
        }
        return comments;
    }


    //将后代节点合并到root根节点
    private void getAllChildren(Comment comment){
        //获取后代评论，添加到二级评论列表
        List<Comment> tempReplys = commentDao.queryCommentByParentId(comment.getCommentId());
        //这个for循环用于设置parent的昵称
        for(Comment comment1 : tempReplys){
            String parentNickName = commentDao.queryParentNickName(comment.getCommentId());
            Comment parentComment = new Comment();
            parentComment.setNickName(parentNickName);
            comment1.setParent(parentComment);
            childrenComment.add(comment1);
            if(tempReplys.size() > 0){
                getAllChildren(comment1);
            }
        }
    }

}
