package com.cd.blog.dao;

import com.cd.blog.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao {

    /* 新增分类 */
    int insertType(Type type);

    /* 删除分类 */
    int deleteType(Integer typeId);

    /* 修改分类 */
    int updateType(Type type);

    /* 分类列表 */
    List<Type> queryTypeList();

    /* 根据分类名称查询分类 */
    Type queryTypeByName(String typeName);

    /* 根据id查询分类 */
    Type queryTypeById(Integer typeId);

    //----------------------------------------------

    /* 查询六个对应博客数最多的分类 */
    List<Type> query6Type();

    /* 获取所有分类及其对应的文章信息 */
    List<Type> queryAllType();

}
