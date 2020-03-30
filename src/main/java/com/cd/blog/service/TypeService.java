package com.cd.blog.service;

import com.cd.blog.entity.Type;

import java.util.List;

public interface TypeService {

    /* 分类列表 */
    List<Type> getTypeList();

    /* 删除分类 */
    int removeType(Integer typeId);

    /* 修改分类 */
    int modifyType(Type type);

    /* 修改分类 */
    int addType(Type type);

    /* 根据分类名称查询分类 */
    Type getTypeByName(String typeName);

    /* 根据id查询分类 */
    Type getTypeById(Integer typeId);

    //----------------------------------------------

    /*查询六个对应博客数最多的分类*/
    List<Type> get6Type();

    /* 获取所有分类及其对应的文章信息 */
    List<Type> getAllType();

}
