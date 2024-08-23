package org.example.util;

public class DummyData {

    public static String getPersonEmail(){
        return "john" + Math.floor(Math.random() * 1000) + "doe" + Math.floor(Math.random() * 1000) + "@mail.com";
    }
    public static String getPersonEmail(String role){
        return role + "_john" + Math.floor(Math.random() * 1000) + "doe" + Math.floor(Math.random() * 1000) + "@mail.com";
    }
    public static  String getProjectName(){
        return "Project_" + Math.floor(Math.random() * 1000);
    }
    public static  String getBugTitle(){
        return "Bug_" + Math.floor(Math.random() * 1000);
    }
    public static  String getBugDescription(){
        return "lorem ipsum dolor sit amet";
    }

    public static String getBugSeverity(){
        String[] severities = {"low", "medium", "high", "critical"};
        return severities[(int) Math.floor(Math.random() * severities.length)];
    }


}
