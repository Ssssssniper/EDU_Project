package com.hu.message;

public enum StatusCode {
    Success(222,true),
    Failed(444,false);
    private int code;
    private boolean status;

    StatusCode(int code, boolean status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
