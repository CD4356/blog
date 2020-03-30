package com.cd.blog.service.impl;

import com.cd.blog.dao.TagDao;
import com.cd.blog.entity.Tag;
import com.cd.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;


    @Transactional
    @Override
    public List<Tag> getTagList() {
        return tagDao.queryTagList();
    }

    @Transactional
    @Override
    public int removeTag(Integer tagId) {
        return tagDao.deleteTag(tagId);
    }


    @Transactional
    @Override
    public int modifyTag(Tag tag) {
        return tagDao.updateTag(tag);
    }


    @Transactional
    @Override
    public int addTag(Tag tag) {
        return tagDao.insertTag(tag);
    }


    @Transactional
    @Override
    public Tag getTagByName(String tagName) {
        return tagDao.queryTagByName(tagName);
    }


    @Transactional
    @Override
    public Tag getTagById(Integer tagId) {
        return tagDao.queryTagById(tagId);
    }

    @Override
    public List<Tag> get6Tag() {
        return tagDao.query6Tag();
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.queryAllTag();
    }
}