package com.example.demo.interfaces;

import javax.servlet.http.HttpServletResponse;

public interface Cookies {
    public void createCookie(HttpServletResponse response, String cookieUsername, String cookieRole);
}
