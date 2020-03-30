package com.cd.blog.service.impl;

import com.cd.blog.dao.TypeDao;
import com.cd.blog.entity.Type;
import com.cd.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;


    @Transactional
    @Override
    public List<Type> getTypeList() {
        return typeDao.queryTypeList();
    }

    @Transactional
    @Override
    public int removeType(Integer typeId) {
        return typeDao.deleteType(typeId);
    }

    @Transactional
    @Override
    public int modifyType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public int addType(Type type) {
        return typeDao.insertType(type);
    }

    @Transactional
    @Override
    public Type getTypeByName(String typeName) {
        return typeDao.queryTypeByName(typeName);
    }

    @Transactional
    @Override
    public Type getTypeById(Integer typeId) {
        return typeDao.queryTypeById(typeId);
    }

    @Override
    public List<Type> get6Type() {
        return typeDao.query6Type();
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.queryAllType();
    }
}
