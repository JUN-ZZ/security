package com.jun.reservation.response;


/**
 * @author jun
 * @date 2020/12/1
 */
public class ResponseResult {

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){

        Result result = new Result(data,ResponseCode.RESPONSE_CODE_200.getValue(),ResponseCode.RESPONSE_CODE_200.getCode());

        return result;

    }

    public static <T> Result<T> success(){

        Result result = new Result(null,ResponseCode.RESPONSE_CODE_200.getValue(),ResponseCode.RESPONSE_CODE_200.getCode());

        return result;

    }
    /**
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data,String message){

        Result result = new Result(data,message,ResponseCode.RESPONSE_CODE_200.getCode());

        return result;

    }

    /**
     *
     * @param data
     * @param message
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data,String message,String code){

        Result result = new Result(data,message,code);

        return result;

    }

    /**
     *
     * @param data
     * @param message
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(T data,String message,String code){

        Result result = new Result(data,message,code);

        return result;

    }

    /**
     *
     * @param message
     * @param code
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String message,String code){

        Result result = new Result(null,message,code);

        return result;

    }

    /**
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(){

        Result result = new Result(null,ResponseCode.RESPONSE_CODE_500.getValue(),ResponseCode.RESPONSE_CODE_500.getCode());


        return result;

    }

    public static <T> Result<T> failure_not_found(){

        Result result = new Result(null,ResponseCode.RESPONSE_CODE_404.getValue(),ResponseCode.RESPONSE_CODE_404.getCode());


        return result;

    }

    public static <T> Result<T> failure_not_found(String message,String code){

        Result result = new Result(null,message,code);

        return result;

    }


}
