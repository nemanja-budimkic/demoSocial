package com.example.demo.spring.controller;


import com.example.demo.spring.AdminEntity;
import com.example.demo.spring.AdminRepository;
import com.example.demo.spring.UserEntity;
import com.example.demo.spring.UsersRepository;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@CrossOrigin(origins = "*")
public class DisplayerController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AdminRepository adminRepository;


    @GetMapping("/info")
    @ResponseBody
    public ModelAndView profilePage () throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");
        return modelAndView;
    }

    @GetMapping("/admin")
    @ResponseBody
    public ModelAndView adminPage () throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }


@PostMapping ("/users")
@ResponseBody
public void trying (HttpServletResponse response) throws IOException {
   tableGenerator(response);
}

@GetMapping ("/rolecheck")
@ResponseBody
public void roleCheck (HttpServletRequest request, HttpServletResponse response) throws IOException {

    Cookie[] cookies = request.getCookies();

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

    @PostMapping ("/delete")
    @ResponseBody
    public void deleteUser (@RequestBody HomeController.CreateUserRequest request) {

        String deletedUser = request.getUsername();
        int deletedUserId = usersRepository.getId(deletedUser);
        usersRepository.deleteUser(deletedUser);

    }

    @PostMapping("/changerole")
    @ResponseBody
    public void changeRole(@RequestBody HomeController.CreateUserRequest request){

        if (usersRepository.getUserRole(request.getUsername()).equals("adminRole")){
            usersRepository.setUserRole(request.getUsername(), "userRole");
        } else if (usersRepository.getUserRole(request.getUsername()).equals("userRole")){
            usersRepository.setUserRole(request.getUsername(), "adminRole");
        }

    }

    @PostMapping("/changekey")
    @ResponseBody
    public void changeKey (@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (adminRepository.getAllKeys().size()<1){
            adminRepository.save(new AdminEntity(request.getadminkey()));
        } else if (adminRepository.getAllKeys().size()==1) {
            adminRepository.updateKey(request.getadminkey(), 1);
        }
    }




    public void tableGenerator (HttpServletResponse response) throws IOException {

        String tableFirstN;
        String tableLastN;
        String tableContact;
        String userrole;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> lastNames = new ArrayList<>();
        ArrayList<String> contacts = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();






        for (int i=0; i<= usersRepository.getAllUsers().size()-1; i++){

            tableFirstN =  usersRepository.getAllUsers().get(i).getUsername();
           //tableLastN = usersRepository.getLastTable(i);
            tableContact = usersRepository.getAllUsers().get(i).getContact();
            userrole = usersRepository.getAllUsers().get(i).getUserRole();

            names.add(tableFirstN);
            contacts.add(tableContact);
            roles.add(userrole);
        }

        for (int i = 0; i<=names.size(); i++){

            if (i==0) {

                if (usersRepository.count() == 1) {

                    response.getWriter().write("[{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                                                   + "\"," +  "\"userrole\":" + "\"" + roles.get(i)  + "\"}]");
                } else {
                    response.getWriter().write("[{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                                                    + "\"," + "\"userrole\":" + "\"" + roles.get(i)  + "\"},");
                }

            } else if (i>0 && i<names.size()-1){
                response.getWriter().write("{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                                                +  "\"," + "\"userrole\":" + "\"" + roles.get(i) + "\"},");

            } else if (i==names.size()-1){
                response.getWriter().write("{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":"  + "\"" + contacts.get(i)
                                                + "\"," + "\"userrole\":" + "\"" + roles.get(i)+ "\"}]");

            }
        }


    }





}


