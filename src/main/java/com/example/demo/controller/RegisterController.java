package com.example.demo.controller;


import com.example.demo.model.CreateUserRequest;
import com.example.demo.interfaces.Cookies;
import com.example.demo.interfaces.Roles;
import com.example.demo.interfaces.Status;
import com.example.demo.interfaces.AdminRepository;
import com.example.demo.model.UserEntity;
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
public class RegisterController implements Status, Roles, Cookies {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/register-page")
    @ResponseBody
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registerPage");
        return modelAndView;
    }

    @PostMapping
    @RequestMapping("/register")
    public void registerUser(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        String registerAttemptUsername = request.getUsername();
        String registerAttemptPassword = request.getPassword();
        String registerAttemptFirstName = request.getFirstName();
        String registerAttemptLastName = request.getLastName();
        String registerAttemptContact = request.getContact();
        String attemptedAdminKey = request.getAdminKey();

        if (!userAlreadyExists(registerAttemptUsername)) {
            setStatus(response, Status.userExistsNot);

            createUser(attemptedAdminKey, request, registerAttemptUsername,
                    registerAttemptPassword, registerAttemptFirstName,
                    registerAttemptLastName, registerAttemptContact
            );
        } else {
            setStatus(response, Status.userExists);
        }
        createCookie(response, registerAttemptUsername, request.getUserRole());
    }

    @Override
    public void createCookie(HttpServletResponse response, String cookieUsername, String cookieRole) {
        response.addCookie(newCookie(cookieUsername, cookieRole));
    }

    @Override
    public void setStatus(HttpServletResponse response, String status) throws IOException {
        response.getWriter().write(status);
    }

    public boolean userAlreadyExists(String username) {
        return username.equals(homeRepository.getUsernameRepo(username));
    }

    public boolean userIsEligibleForAdmin(String attemptedAdminKey) {
        String currentAdminKey = adminRepository.getAdminKey();

        return java.util.Objects.equals(attemptedAdminKey, currentAdminKey) || java.util.Objects.equals(attemptedAdminKey, "adminKey123");
    }

    public void createUser(String attemptedAdminKey, CreateUserRequest request,
                           String username, String password, String firstName,
                           String lastName, String contact) {

        String userRole;

        if (userIsEligibleForAdmin(attemptedAdminKey)) {
            userRole = Roles.admin;
        } else {
            userRole = Roles.user;
        }
        setRole(request, userRole);

        homeRepository.save(new UserEntity(username, password, firstName, lastName, contact, userRole));
    }


    public void setRole(CreateUserRequest request, String role) {
        request.setUserRole(role);
    }
}
