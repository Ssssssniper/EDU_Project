package com.hu.message;


import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/** 实体
 *  返回前端消息格式：Json格式
 * @Time 2020-07-09
 */

@Data
public class ResponseMessage {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data= new HashMap<String,Object>();

    // 构造方法私有化
    private ResponseMessage() {
    }

    // 外部使用方法
    public static ResponseMessage ok() {
        ResponseMessage message = new ResponseMessage();
        message.setSuccess(StatusCode.Success.getStatus());
        message.setCode(StatusCode.Success.getCode());
        message.setMessage("数据接收成功");
        return message;
    }
    public static ResponseMessage error(){
        ResponseMessage message = new ResponseMessage();
        message.setSuccess(StatusCode.Failed.getStatus());
        message.setCode(StatusCode.Failed.getCode());
        message.setMessage("数据接收失败");
        return message;
    }
    // 实现链式操作赋值
    public ResponseMessage success(boolean success){
        this.success = success;
        return this;
    }
    public ResponseMessage code(int code){
        this.code = code;
        return this;
    }
    public ResponseMessage message(String message){
        this.message = message;
        return this;
    }
    public ResponseMessage data(Map<String,Object> data){
        this.setData(data);
        return this;
    }
    public ResponseMessage data(String key,Object value){
        this.data.put(key, value);
        return this;
    }
}
