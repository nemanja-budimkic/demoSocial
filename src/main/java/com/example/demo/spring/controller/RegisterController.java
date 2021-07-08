package com.example.demo.spring.controller;


import com.example.demo.spring.AdminRepository;
import com.example.demo.spring.UserEntity;
import com.example.demo.spring.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.spring.controller.CookieHandler.cookieCreator;

@Controller
@CrossOrigin(origins = "*")

public class RegisterController {



    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AdminRepository adminRepository;



    @GetMapping("/register")
    @ResponseBody
    public ModelAndView registrationPage (){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;

    }

    @PostMapping
    @RequestMapping("/register")
    public void createUser(@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (request.getUsername().equals(usersRepository.getUsernameRepo(request.getUsername()))
        ){
            //User already exists
            response.getWriter().write("{\"status\":\"false\"}");

        } else {
            //User does not exist, create user



            if (request.getuserrole().equals(adminRepository.getAdminKey())){
                request.setuserrole("adminRole");


                usersRepository.save(new UserEntity(request.getUsername(), request.getPassword(), request.getfirstname(), request.getlastname(), request.getContact(), request.getuserrole()));
            } else {
                request.setuserrole("userRole");

                usersRepository.save(new UserEntity(request.getUsername(), request.getPassword(), request.getfirstname(), request.getlastname(), request.getContact(), request.getuserrole()));
            }

            response.getWriter().write("{\"status\":\"true\"}");


            String userCookie = usersRepository.getNameForCookie(request.getUsername());
            String userRole = request.getuserrole();

                response.addCookie(cookieCreator(userCookie, userRole ));

            ProfileController cO = new ProfileController();
            cO.setCookieCheck("created");
        }
    }


}
