package com.jun.reservation.response;


/**
 * @author jun
 * @date 2020/12/1
 */
public enum  ResponseCode {

    RESPONSE_CODE_200("200","success"),
    RESPONSE_CODE_404("404","not found"),
    RESPONSE_CODE_500("500","服务器内部错误")
    ;

    private String code;
    private String value;


    /**
     * @param code
     * @param value
     */
    private ResponseCode(String code, String value){
        this.code = code;
        this.value = value;
    }

    private ResponseCode(){

    }

    /**
     *
     * @return
     */
    public String getCode() {
        return this.code;
    }

    /**
     *
     * @return
     */
    public String getValue(){
        return this.value;
    }




}
