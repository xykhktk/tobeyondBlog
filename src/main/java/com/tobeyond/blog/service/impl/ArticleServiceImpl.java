package com.tobeyond.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.config.QiniuConfig;
import com.tobeyond.blog.dao.mapper.ArticleMapper;
import com.tobeyond.blog.dao.mapper.TagMapper;
import com.tobeyond.blog.model.bo.ArticleBo;
import com.tobeyond.blog.model.bo.ArticleTagBo;
import com.tobeyond.blog.model.po.*;
import com.tobeyond.blog.dao.mapper.ArticleMapperHistory;
import com.tobeyond.blog.dao.mapper.ArticleTagsMapper;
import com.tobeyond.blog.service.IArticleService;
import com.tobeyond.blog.util.DateKit;
//import org.markdownj.MarkdownProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    QiniuConfig qiniuConfig;
    @Autowired
    ArticleMapperHistory articleMapperHistory;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagsMapper articleTagsMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    ArticleExample articleExample;

    @Override
    public List<ArticleTagsPo> getTagListByTagId(Long tag_id) {
        return articleTagsMapper.getTagsListByTagId(tag_id);
    }

    @Override
    public PageInfo<ArticleBo> articleList(Integer page, Integer limit, Integer tag_id,Byte isShow) {
        if(page == null) page = 1;

        List<ArticleTagsPo> articleTagsPoList = new ArrayList<>();
        if(tag_id != null){
            ArticleTagsExample articleTagsExample = new ArticleTagsExample();
            articleTagsExample.createCriteria().andTagIdEqualTo(tag_id);
           articleTagsPoList = articleTagsMapper.selectByExample(articleTagsExample);
        }

        articleExample.clear();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        if(isShow != null) criteria.andIsShowEqualTo(isShow);
        if(articleTagsPoList.size() > 0){
            List<Integer> articleIds = new ArrayList<>();
            for (ArticleTagsPo articleTagsPo : articleTagsPoList){
                articleIds.add(articleTagsPo.getArticleId());
            }
            criteria.andIdIn(articleIds);
        }
        List<ArticlePo> articlePoList =  articleMapper.selectByExample(articleExample);

        List<ArticleBo> articleBoList = new ArrayList<>();
        for(ArticlePo articlePo : articlePoList){
            ArticleBo articleBo = new ArticleBo();
            BeanUtils.copyProperties(articlePo,articleBo);
            articleBo.setPageImage(qiniuConfig.getPath() +  articleBo.getPageImage());

            ArrayList<ArticleTagBo> articleTagBo =  articleTagsMapper.getTagsByArticleId(articleBo.getId());
            articleBo.setTagList(articleTagBo);

            articleBoList.add(articleBo);
        }

        PageHelper.startPage(page, limit);
        return new PageInfo<>(articleBoList);
    }

    @Override
    public ArticleBo articleDetail(Integer articleId) {
        ArticlePo articleTagsPo =  articleMapper.selectByPrimaryKey(articleId);
        ArticleBo articleBo = new ArticleBo();
        BeanUtils.copyProperties(articleTagsPo,articleBo);
        ArrayList<ArticleTagBo> articleTagBo =  articleMapperHistory.getArticleTags(articleBo.getId());
        articleBo.setTagList(articleTagBo);
        return articleBo;
    }

    @Override
    @Transactional
    public Boolean articleAdd(ArticlePo article,String tagIds) {
        Date dateNow = DateKit.getNow();
        try {
            article.setCreatedAt(dateNow);
            article.setUpdatedAt(dateNow);
            articleMapperHistory.articleAdd(article);

            //批量插入
            String[] tagArray =  tagIds.split(",");
            List<ArticleTagsPo> articleTagPoList = new ArrayList<>();
            for(String tagId : tagArray){
                ArticleTagsPo articleTagPo = new ArticleTagsPo();
                articleTagPo.setArticleId(article.getId());
                articleTagPo.setTagId(Integer.valueOf(tagId));
                articleTagPo.setCreatedAt(dateNow); //代码生成器生成的代码是Date类型
                articleTagPo.setUpdatedAt(dateNow);
                articleTagPoList.add(articleTagPo);
            }
            articleTagsMapper.insertBatch(articleTagPoList);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Boolean articleDel(Integer articleId) {
        try {
            ArticlePo article = new ArticlePo();
            article.setIsDel(Byte.valueOf("1"));
            articleExample.clear();
            articleExample.createCriteria().andIdEqualTo(articleId);
            articleMapper.updateByExample(article,articleExample);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean changeShow(Integer articleId, Byte isShow) {
        try {
            ArticlePo article = new ArticlePo();
            article.setIsShow(isShow);
            //要么 @Autowired，然后articleExample每次都clear()一次，
            //要么每次都重新new一个 example
            //有待优化
            articleExample.clear();
            articleExample.createCriteria().andIdEqualTo(articleId);
            articleMapper.updateByExampleSelective(article,articleExample);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean articleEditSave(ArticlePo article, String tagIds) {
        Date dateNow = DateKit.getNow();
        try {
            article.setUpdatedAt(dateNow);
            articleExample.clear();
//            articleMapperHistory.articleUpdate(article);
            articleExample.createCriteria().andIdEqualTo(article.getId());
            articleMapper.updateByExample(article,articleExample);

            ArrayList<ArticleTagBo> articleTagBo =  articleMapperHistory.getArticleTags(article.getId());

            //求并集 交集 差级 https://blog.csdn.net/u011595939/article/details/74348216/
            List<String> selectedTag = new ArrayList<>();   //页面上选择的tag
            List<String> dbTag = new ArrayList<>(); //数据库里记录的tag
//            for(String selectTag : selectTags){ newTag.add(selectTag); }
            selectedTag.addAll(Arrays.asList(tagIds.split(",")));
            for(ArticleTagBo tag : articleTagBo){ dbTag.add(String.valueOf(tag.getTagId())); }

            List<String> insertTag = new ArrayList<>(selectedTag);   //要新增的tag
            insertTag.removeAll(dbTag);
            List<String> delTag = new ArrayList<>(dbTag);   //要删除的tag
            delTag.removeAll(selectedTag);

            if(insertTag.size() > 0){
                List<ArticleTagsPo> articleTagPoList = new ArrayList<>();
                for(String insertTagId : insertTag){
                    ArticleTagsPo articleTagPo = new ArticleTagsPo();
                    articleTagPo.setArticleId(article.getId());
                    articleTagPo.setTagId(Integer.valueOf(insertTagId));
                    articleTagPo.setCreatedAt(dateNow);
                    articleTagPo.setUpdatedAt(dateNow);
                    articleTagPoList.add(articleTagPo);
                }
                articleTagsMapper.insertBatch(articleTagPoList);
            }

            if(delTag.size() > 0){
                HashMap<String,Object> params = new HashMap<>();
                params.put("article_id",article.getId());
                params.put("whereInTagIds",delTag); //在mapper试使用where in，collection="whereInTagIds"要与这里对应
//                params.put("deleted_at",now);
                articleTagsMapper.delArticleTags(params);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    // 其实是  select * from tags as t left join article_tags as at on t.id = at.tagId
    // where at.article_id = XXX and at.deleteAt is not null and ...
    @Override
    public List<TagPo> getTagListByArticleId(Integer id) {
        ArticleTagsExample articleTagsExample = new ArticleTagsExample();
        articleTagsExample.createCriteria().andArticleIdEqualTo(id).andDeletedAtIsNull();
        List<ArticleTagsPo> articleTagsPos = articleTagsMapper.selectByExample(articleTagsExample);

        TagExample tagExample = new TagExample();
        List<Integer> inIds = new ArrayList<>();
        for(ArticleTagsPo articleTagsPo : articleTagsPos){
            inIds.add(articleTagsPo.getTagId());
        }
        TagExample.Criteria criteria = tagExample.createCriteria().andDeletedAtIsNull();
        if(inIds.size() > 0)  criteria.andIdIn(inIds);
        return tagMapper.selectByExample(tagExample);
    }


}
