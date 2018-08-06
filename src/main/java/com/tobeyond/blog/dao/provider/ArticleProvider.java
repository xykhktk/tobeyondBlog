package com.tobeyond.blog.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ArticleProvider {

    public String articleList(Map<String, Object> param){

        SQL sql = new SQL(){{
            SELECT("*");
            FROM("articles");
        }};
        System.out.print("---");
        System.out.print(param.get("in_ids"));
        System.out.print("---");
        if(null != param.get("in_ids") && !param.get("in_ids").equals("")){
            sql.WHERE("id in " + param.get("in_ids"));
        }

        return sql.toString();
    }

}
