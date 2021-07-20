package com.example.demo.model.controller;
import com.example.demo.model.AdminRepository;
import com.example.demo.model.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.model.controller.CookieHandler.*;

@Controller
@CrossOrigin(origins = "*")

public class HomeController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AdminRepository adminRepository;

   @GetMapping ("/home")
    @ResponseBody
    public ModelAndView registrationPage (){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("home");
        return modelAndView;

    }

    @PostMapping
    @RequestMapping("/home")
    public void loginUser(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (request.getUsername().equals(usersRepository.getUserPassMatch(request.getUsername(), request.getPassword()))
        ){
            //Login success;
            response.getWriter().write("{\"status\":\"loginSuccessful\"}");

            String userCookie = usersRepository.getNameForCookie(request.getUsername());
            String passCookie = request.getPassword();

            String userRole = usersRepository.getUserRole(request.getUsername());

                response.addCookie(cookieCreator(userCookie, userRole));


            ProfileController cO = new ProfileController();
            cO.setCookieCheck("created");


        } else {
            //Login failed;
            response.getWriter().write("{\"status\":\"loginFailed\"}");
        }


    }

         static class CreateUserRequest {
             private String username;

             private String password;

             private String firstname;

             private String lastname;

             private String contact;

             private String userrole;

             private int id;

             private String adminkey;

             private String positionname;

             private int positionid;

             private String teamname;

             private String teamid;

             private String projectname;

             private int projectid;

             private String clientname;

             private String clientcontact;

             private int clientid;


             public CreateUserRequest() {

             }

             public String getUsername() {
                 return username;
             }

             public void setUsername(String username) {
                 this.username = username;
             }

             public String getPassword() {
                 return password;
             }

             public void setPassword(String password) {
                 this.password = password;
             }

             public String getfirstname() {
                 return firstname;
             }

             public void setfirstname(String firstname) {
                 this.firstname = firstname;
             }

             public String getlastname() {
                 return lastname;
             }

             public void setlastname(String lastName) {
                 this.lastname = lastName;
             }

             public String getContact() {
                 return contact;
             }

             public void setContact(String contact) {
                 this.contact = contact;
             }

             public String getuserrole() {
                 return userrole;
             }

             public void setuserrole(String userrole) {
                 this.userrole = userrole;
             }

             public int getid() {
                 return id;
             }

             public void setid(int id) {
                 this.id = id;
             }

             public String getadminkey() {
                 return adminkey;
             }

             public void setadminkey(String adminkey) {
                 this.adminkey = adminkey;
             }

             public String getpositionname() {
                 return positionname;
             }

             public void setPositionname(String positionname) {
                 this.positionname = positionname;
             }

             public int getPositionid() {
                 return positionid;
             }

             public void setPositionid(int positionid) {
                 this.positionid = positionid;
             }

             public String getTeamname() {
                 return teamname;
             }

             public void setTeamname(String teamname) {
                 this.teamname = teamname;
             }

             public String getTeamid() {
                 return teamid;
             }

             public void setTeamid(String teamid) {
                 this.teamid = teamid;
             }

             public String getProjectname() {
                 return projectname;
             }

             public void setProjectname(String projectname) {
                 this.projectname = projectname;
             }

             public int getProjectid() {
                 return projectid;
             }

             public void setProjectid(int projectid) {
                 this.projectid = projectid;
             }

             public String getClientname() {
                 return clientname;
             }

             public void setClientname(String clientname) {
                 this.clientname = clientname;
             }

             public String getClientcontact() {
                 return clientcontact;
             }

             public void setClientcontact(String clientcontact) {
                 this.clientcontact = clientcontact;
             }

             public int getClientid() {
                 return clientid;
             }

             public void setClientid(int clientid) {
                 this.clientid = clientid;
             }
         }




     }