package com.tobeyond.blog.model.dto;

public class ReturnJson {

    private String code;
    private String message;
    private Object data;
    private Boolean success;
    private String timestamp;
    private int unixtime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(int unixtime) {
        this.unixtime = unixtime;
    }

    public static ReturnJson error(String message){
        ReturnJson returnJson = new ReturnJson();
        returnJson.setCode("400");
        returnJson.setMessage(message);
        returnJson.setSuccess(false);
//        returnJson.setData(new Object());
        return  returnJson;
    }

    public static ReturnJson error(String message,String code){
        ReturnJson returnJson = ReturnJson.error(message);
        returnJson.setCode(code);
        return  returnJson;
    }

    public static ReturnJson success(String message){
        ReturnJson returnJson = new ReturnJson();
        returnJson.setCode("200");
        returnJson.setMessage(message);
        returnJson.setSuccess(true);
//        returnJson.setData(new Object());
        return  returnJson;
    }

    public static ReturnJson success(String message,Object data){
        ReturnJson returnJson = ReturnJson.success(message);
        returnJson.setData(data);
        return  returnJson;
    }
}
