package com.tobeyond.blog.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tobeyond.blog.dao.mapper.UserMapper;
import com.tobeyond.blog.model.po.User;
import com.tobeyond.blog.service.ITokenService;
import com.tobeyond.blog.util.DateKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    UserMapper userMapper;

    /**
     * user id + 时间戳 生成token
     * @param id
     * @return
     */
    public String getToken(Integer id) {
        long tokenTime = DateKit.getUnixTimeLong();
        String token =  JWT.create().withAudience(String.valueOf(id)).sign(Algorithm.HMAC256(String.valueOf(id) + String.valueOf(tokenTime)));
        User user = new User();
        user.setId(id);
        user.setToken_time((int) tokenTime);
        userMapper.updateTokenTimeById(user);
        return token;
    }

}
