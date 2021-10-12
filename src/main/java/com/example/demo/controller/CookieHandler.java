package com.example.demo.controller;

import javax.servlet.http.Cookie;

public class CookieHandler {

    public static Cookie newCookie(String cookieUsername, String cookieRole) {
        return new Cookie(cookieUsername, cookieRole);
    }

}

