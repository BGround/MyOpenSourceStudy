package com.mychery.appcloud.view.login;

public class UserBean {


    /**
     * department : 公共帐户/临时帐户（guest）
     * email :
     * empNo : L000435
     * id : 56
     * realName : 刘峰麟
     * show : 1
     * username : L000435
     */

    private String department;
    private String email;
    private String empNo;
    private int id;
    private String realName;
    private int show;
    private String username;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
