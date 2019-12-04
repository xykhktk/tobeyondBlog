package com.tobeyond.blog.controller.admin.api;

import com.tobeyond.blog.model.bo.UserCustom;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.service.ITokenService;
import com.tobeyond.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/admin")
public class LoginController {

    @Autowired
    IUserService userService;

    @Autowired
    ITokenService tokenService;

    @PostMapping("/login")
    public ReturnJson Login(@RequestParam String name, @RequestParam String password){
        ReturnJson returnJson;
        try {
            UserCustom user = userService.userLogin(name, password);
            returnJson = ReturnJson.success("登录成功");
            String token = tokenService.getToken(user.getId());
            Map data = new HashMap<String,String>();
            data.put("token",token);//Unchecked call to 'put(K, V)' as a member of raw type 'java.util.Map'
            returnJson.setData(data);
        } catch (Exception e) {
            returnJson = ReturnJson.error(e.getMessage());
        }
        return returnJson;
    }


}
