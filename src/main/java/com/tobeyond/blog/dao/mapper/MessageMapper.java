package com.tobeyond.blog.dao.mapper;

import com.tobeyond.blog.dao.domain.Message;
import org.apache.ibatis.annotations.Insert;

public interface MessageMapper {


    @Insert("insert into message(name,contact,message,created_at) values(#{name},#{contact},#{message},#{created_at})")
     int insertMessage(Message message);
}
