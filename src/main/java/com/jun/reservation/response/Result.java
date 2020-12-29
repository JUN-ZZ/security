package com.jun.reservation.response;

/**
 * @author jun 返回数据的封装类
 * @date 2020/12/1
 */
public class Result<T> {


    private T data;

    private String message;

    private String code;


    public Result() {

    }

    public Result(T data,String message,String code) {
        this.data = data;
        this.code = code;
        this.message = message;

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result<?> result = (Result<?>) o;

        if (getData() != null ? !getData().equals(result.getData()) : result.getData() != null) return false;
        if (getMessage() != null ? !getMessage().equals(result.getMessage()) : result.getMessage() != null)
            return false;
        return getCode() != null ? getCode().equals(result.getCode()) : result.getCode() == null;
    }

    @Override
    public int hashCode() {
        int result = getData() != null ? getData().hashCode() : 0;
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        return result;
    }
}
