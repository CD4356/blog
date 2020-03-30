package com.cd.blog.web.front;

import com.cd.blog.entity.Comment;
import com.cd.blog.service.BlogService;
import com.cd.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author: 落叶无痕
 * @Date： 2020/3/24 16:47
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;


    @GetMapping("/comments/{blogId}")
    public String comments(Model model, @PathVariable("blogId") Integer blogId){
        List<Comment> commentList = commentService.getCommentListByBlogIdAndParentIdNull(blogId);
        model.addAttribute("commentList", commentList);
        return "front/blog_detail :: blog_comment_list";  //这里的意思是：返回front目录下的blog_detail页面中的commentList片段，以实现局部刷新
    }


    @PostMapping("/comments")
    public String postComment(Comment comment){
        Integer blogId = comment.getBlog().getBlogId();
        comment.setBlog(blogService.getBlogById(blogId));
        comment.setAvatar(avatar);
        //插入评论信息
        commentService.addComment(comment);
        return "redirect:/comments/" + blogId;
    }

}
