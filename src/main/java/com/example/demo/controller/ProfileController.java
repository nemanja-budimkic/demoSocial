package com.example.demo.controller;

import com.example.demo.interfaces.Status;
import com.example.demo.interfaces.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.demo.interfaces.Cookies;

import static com.example.demo.controller.CookieHandler.newCookie;

@Controller
@CrossOrigin(origins = "*")
public class ProfileController implements Cookies, Status {

    @Autowired
    private HomeRepository homeRepository;

    Cookie[] cookies;

    @GetMapping("/profile-page")
    @ResponseBody
    public ModelAndView profilePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profilePage");
        return modelAndView;
    }

    @GetMapping("/logoff")
    @ResponseBody
    public void logOffUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies = request.getCookies();

        eraseCookie(response, cookies);
    }

    @PostMapping
    @RequestMapping("/profile-page")
    public void loadProfileInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        cookies = request.getCookies();
        String sessionFirstName = cookies[0].getName();
        String sessionLastName = homeRepository.getlastnameCookie(sessionFirstName);
        String sessionUsername = homeRepository.getUsernameCookie(sessionFirstName);
        String sessionContact = homeRepository.getContactCookie(sessionFirstName);
        String sessionRole = cookies[0].getValue();

        if (cookies != null) {
            setStatus(response, "{\"firstname\":" + "\"" + sessionFirstName + "\"," + "\"lastname\":" + "\"" +
                    sessionLastName + "\"," + "\"username\":" + "\"" + sessionUsername +
                    "\"," + "\"contact\":" + "\"" + sessionContact + "\"," +
                    "\"userrole\":" + "\"" + sessionRole + "\"}");
        }
    }

    @GetMapping("/restrict")
    @ResponseBody
    public void checkAccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        determineRestriction(request, response);
    }

    @Override
    public void createCookie(HttpServletResponse response, String cookieUsername, String cookieRole) {
        response.addCookie(newCookie(cookieUsername, cookieRole));
    }

    public void eraseCookie(HttpServletResponse response, Cookie[] cookies) throws IOException {
        if (cookies != null) {
            Cookie cookie = new Cookie(cookies[0].getName(), null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            setStatus(response, Status.cookieErased);
        }
    }

    @Override
    public void setStatus(HttpServletResponse response, String status) throws IOException {
        response.getWriter().write(status);
    }

    public void determineRestriction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getCookies() == null) {
            setStatus(response, Status.restrictAccess);
        } else {
            setStatus(response, Status.allowAccess);
        }
    }
}
