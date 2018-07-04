package com.smarthome.iot.utils;

import java.util.ArrayList;
import java.util.List;

public class Flash {
    private static List<String> errors = new ArrayList<>();

    private static List<String> messages = new ArrayList<>();

    public static void pushError(String error){
        errors.add(error);
    }

    public static String getErrors(){
        StringBuffer buffer = new StringBuffer();
        for (String error : errors) {
            buffer.append(error);
        }
        return buffer.toString();
    }

    public static void clearErrors(){
        errors.clear();
    }

    public static void pushMessage(String message){
        messages.add(message);
    }

    public static String getMessages(){
        StringBuffer buffer = new StringBuffer();
        for (String message : messages) {
            buffer.append(message);
        }
        return buffer.toString();
    }

    public static void clearMessage(){
        messages.clear();
    }
}
