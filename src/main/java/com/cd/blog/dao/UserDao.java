package com.cd.blog.dao;

import com.cd.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User findByUsername(@Param("username")String username);
    /*有多个参数时，需要使用@Param注解进行绑定关联*/

}
