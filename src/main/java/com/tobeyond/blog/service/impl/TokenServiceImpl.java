package com.tobeyond.blog.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tobeyond.blog.service.ITokenService;
import com.tobeyond.blog.util.DateKit;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {

    /**
     * user id + 时间戳 生成token
     * @param id
     * @return
     */
    public String getToken(Integer id) {
        return JWT.create().withAudience(String.valueOf(id)).sign(Algorithm.HMAC256(String.valueOf(id) + String.valueOf(DateKit.getUnixTimeLong())));
    }

}
