package com.example.databindingdemo.model;

/**
 * Author:BGround
 * Time:4/04/2019
 * Github:https://github.com/BGround/MyStudy
 */

public class User {
    private String name;
    private String pwd;

    public User( String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getPwd() {
        return pwd;
    }

}
