package com.mychery.android.lib.http;

import java.io.Serializable;

/**
 * @author:: wangjianchi
 * @time: 2018/1/3  16:44.
 * @description:
 */

public class AppResultData<T> implements Serializable{


    /**
     * code : {"name":"处理成功","value":10000}
     * data : {"id":56,"editFlag":null,"editTime":null,"department":"公共帐户/临时帐户（guest）","email":"","empNo":"l000435","msgId":null,"realName":"刘峰麟","show":1,"sex":null,"tel":null,"username":"L000435","emnum":null,"name":null}
     */

    private CodeBean code;
    private T data;

    public CodeBean getCode() {
        return code;
    }

    public void setCode(CodeBean code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class CodeBean {
        /**
         * name : 处理成功
         * value : 10000
         */

        private String name;
        private int value;

        public CodeBean() {
        }

        public CodeBean(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



    public interface CODE_TYPE{
        int SUCCESS = 10000;
    }


}
