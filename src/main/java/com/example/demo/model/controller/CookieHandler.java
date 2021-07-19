package com.example.demo.model.controller;

import javax.servlet.http.Cookie;

public class CookieHandler {


    public static String cookieEncoder (String cipher, int s){

        String result ="";

        for (int i=0; i<cipher.length(); i++)
        {
            if (Character.isUpperCase(cipher.charAt(i)))
            {
                char ch = (char)(((int)cipher.charAt(i) +
                        s - 65) % 26 + 65);
                result.concat(String.valueOf(ch));
            }
            else
            {
                char ch = (char)(((int)cipher.charAt(i) +
                        s - 97) % 26 + 97);
                result.concat(String.valueOf(ch));
            }
        }
        return result;

    }

    public static Cookie cookieCreator (String cipher1, String userRole){
        Cookie cookie = new Cookie(cipher1, userRole);

        return new Cookie(cipher1, userRole);
    }





}
