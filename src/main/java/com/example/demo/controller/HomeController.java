package com.example.demo.controller;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.interfaces.Cookies;
import com.example.demo.interfaces.Status;
import com.example.demo.interfaces.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.example.demo.controller.CookieHandler.newCookie;

@Controller
@CrossOrigin(origins = "*")
public class HomeController implements Status, Cookies {

    @Autowired
    private HomeRepository homeRepository;

    @GetMapping("/home-page")
    @ResponseBody
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homePage");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping("/home-page")
    public void logInUser(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        String logInAttemptUsername = request.getUsername();
        String logInAttemptPassword = request.getPassword();
        String userRole = getUserRole(logInAttemptUsername);

        if (logInInformationIsCorrect(logInAttemptUsername, logInAttemptPassword)) {
            setStatus(response, Status.logInSuccess);
            createCookie(response, logInAttemptUsername, userRole);
        } else {
            setStatus(response, Status.logInFailed);
        }
    }

    @Override
    public void createCookie(HttpServletResponse response, String cookieUsername, String cookieRole) {
        response.addCookie(newCookie(cookieUsername, cookieRole));
    }

    public boolean logInInformationIsCorrect(String username, String password) {
        return homeRepository.getUsernameIfLogInCorrect(username, password) != null;
    }

    public void setStatus(HttpServletResponse response, String status) throws IOException {
        response.getWriter().write(status);
    }

    public String getUserRole(String username) {
        return homeRepository.getUserRole(username);
    }
}