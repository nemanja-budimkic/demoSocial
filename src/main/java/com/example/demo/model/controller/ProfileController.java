package com.example.demo.model.controller;


import com.example.demo.model.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@CrossOrigin(origins = "*")
public class ProfileController {

    static String cookieCheck;


    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/profile")
    @ResponseBody
    public ModelAndView profilePage (){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;

    }

    @GetMapping ("/profile/logoff")
    @ResponseBody
    public void cookieEraser (HttpServletRequest request, HttpServletResponse response) throws IOException {
     Cookie [] cookies = request.getCookies();

     if (cookies != null) {

         Cookie cookie = new Cookie(cookies[0].getName(), null);
         cookie.setMaxAge(0);
         cookie.setPath("/");

         response.addCookie(cookie);

         cookieCheck = "erased";

         response.getWriter().write("{\"cookieStatus\":" + "\"" + cookieCheck + "\"}");
     }
    }

    @RequestMapping("/profile/{username}")
    @ResponseBody
    public Object listUser (@PathVariable String username){

        String jsonUsername = usersRepository.getUsernameRepo(username);
        String jsonFirstName = usersRepository.getNameForCookie(username);
        String jsonLastName = usersRepository.getLastNfromUsern(username);
        String jsonContact = usersRepository.getContactfromUsern(username);
        String jsonRole = usersRepository.getUserRole(username);

        username = usersRepository.getUsernameRepo(username);

        return "{\"username\":" + "\"" + jsonUsername + "\"," +
                 "\"firstname\":" + "\"" + jsonFirstName + "\"," +
                "\"lastname\":" + "\"" + jsonLastName + "\"," +
                "\"contact\":" +  "\"" + jsonContact +  "\"," +
                "\"userrole\":" + "\"" + jsonRole + "\"}";

    }


    @PostMapping
    @RequestMapping("/profile")
    public void profileDisplay (HttpServletRequest request, HttpServletResponse response) throws IOException {

        Cookie [] cookies = request.getCookies();

        if (cookies != null) {

            String cookieName = cookies[0].getName();
            String cookieRole = cookies[0].getValue();
            String loggedName;
            String loggedLastName;
            String loggedUsername;
            String loggedContact;

            if (cookieName.equals(usersRepository.getfirstnameRepo(cookieName))) {

                loggedName = cookieName;
                loggedLastName = usersRepository.getlastnameCookie(cookieName);
                loggedUsername = usersRepository.getUsernameCookie(cookieName);
                loggedContact = usersRepository.getContactCookie(cookieName);
                response.getWriter().write("{\"firstname\":" + "\"" + loggedName + "\"," + "\"lastname\":" + "\"" + loggedLastName + "\"," + "\"username\":" + "\"" + loggedUsername + "\"," + "\"contact\":" + "\""
                        + loggedContact +  "\"," + "\"userrole\":" + "\"" + cookieRole + "\"}");

            }

        }

    }


    @GetMapping ("/restrict")
    @ResponseBody
    public void pageRestrict (HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getCookies()==null){
            response.getWriter().write("{\"status\":\"restrict\"}");
        } else{
            response.getWriter().write("{\"status\":\"allow\"}");}
    }


    public void setCookieCheck(String cookieCheck) {
        ProfileController.cookieCheck = cookieCheck;
    }
}
