package com.tobeyond.blog.model.po;

public class User extends CommField {

    private Integer id;
    private String name;
    private String password;
    private String salt;
    private Integer token_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToken_time() {
        return token_time;
    }

    public void setToken_time(Integer token_time) {
        this.token_time = token_time;
    }
}
