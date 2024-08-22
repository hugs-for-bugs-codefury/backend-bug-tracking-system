package org.example.util;

public class DummyData {

    public static String getPersonEmail(){
        return "john" + Math.floor(Math.random() * 1000) + "doe" + Math.floor(Math.random() * 1000) + "@mail.com";
    }
    public static String getPersonEmail(String role){
        return role + "_john" + Math.floor(Math.random() * 1000) + "doe" + Math.floor(Math.random() * 1000) + "@mail.com";
    }



}
