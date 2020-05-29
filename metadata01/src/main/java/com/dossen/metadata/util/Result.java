package com.dossen.metadata.util;

public class Result {
    //接口返回状态标志：1 代表接口正常处理，返回成功； 2 代表处理异常，返回失败

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Result(String status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
    public Result() {
        super();
    }
    @Override
    public String toString() {
        return "Result [status=" + status + ", message=" + message + "]";
    }

}
