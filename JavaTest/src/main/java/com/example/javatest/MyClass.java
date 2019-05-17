package com.example.javatest;

import java.util.Calendar;

public class MyClass {

    public static void main(String[] args){

        int month = 3;
        if((month + "").matches("[0-9]{4}[0-9]{2}")) {
            System.out.println(true);
        }else {
            System.out.println(false);
        }

    }
}
