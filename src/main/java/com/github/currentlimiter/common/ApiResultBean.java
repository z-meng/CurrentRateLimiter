package com.github.currentlimiter.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Api 接口返回数据包装类
 * @author wl
 * @param <T>
 */
@Data
@Slf4j
public class ApiResultBean<T> implements Serializable {

    private static final long serialVersionUID = 123456L;

    public static final int SUCCESS = 1;
    public static final int ERROR = 0;
    public static final Object EMPTY_OBJECT = new HashMap<>(1);

    private String msg = "success";

    private StatusInfo statusInfo = new StatusInfo();

    private int code = 200;

    private int status = 1;

    private T data;

    public ApiResultBean() {
        super();
    }

    public ApiResultBean(T data) {
        this();
        this.data = data;
    }

    public ApiResultBean(int status, int code, String msg, T data) {
        this.msg = msg;
        this.code = code;
        this.status = status;
        this.data = data;
        this.statusInfo = new StatusInfo(code, msg);
    }

    public static ApiResultBean successResult() {
        return new ApiResultBean<>(EMPTY_OBJECT);
    }


    public static ApiResultBean errorResult(int code, String msg) {
        return new ApiResultBean<>(ERROR, code, msg, EMPTY_OBJECT);
    }

    @Override
    public String toString() {
        return "ApiResultBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}

class StatusInfo {

    private Object errorCode;
    private Object errorMsg;
    private Object errorLevel;
    private Object errorData;

    public StatusInfo() {

    }

    public StatusInfo(Object errorCode, Object errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.errorLevel = 1;
        this.errorData = "";
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(Object errorLevel) {
        this.errorLevel = errorLevel;
    }

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }
}

