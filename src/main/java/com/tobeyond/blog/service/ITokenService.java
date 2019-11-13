package com.tobeyond.blog.service;


import com.tobeyond.blog.model.Dto.UserToken;

public interface ITokenService {


    String getToken(Integer id);

}
