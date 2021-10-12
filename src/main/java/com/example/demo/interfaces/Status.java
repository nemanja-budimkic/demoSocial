package com.example.demo.interfaces;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Status {

    String logInSuccess = ("{\"status\":\"loginSuccessful\"}");
    String logInFailed = ("{\"status\":\"loginFailed\"}");

    String userExists = ("{\"status\":\"false\"}");
    String userExistsNot = ("{\"status\":\"true\"}");

    String cookieErased = ("{\"cookieStatus\":\"erased\"}");
    String cookieCreated = ("{\"cookieStatus\":\"created\"}");

    String restrictAccess = ("{\"status\":\"restrict\"}");
    String allowAccess = ("{\"status\":\"allow\"}");



    public void setStatus(HttpServletResponse response, String status) throws IOException;

}
