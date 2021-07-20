package com.example.demo.model.controller;


import com.example.demo.model.*;
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

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TeamRepository teamRepository;


    @GetMapping("/info")
    @ResponseBody
    public ModelAndView profilePage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("info");
        return modelAndView;
    }

    @GetMapping("/admin")
    @ResponseBody
    public ModelAndView adminPage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }


    @PostMapping("/users")
    @ResponseBody
    public void generateUserTable(HttpServletResponse response) throws IOException {
        userTableGenerator(response);

    }

    @PostMapping("/positions")
    @ResponseBody
    public void generatePositionTable(HttpServletResponse response) throws IOException {
        positionTableGenerator(response);
    }


    @GetMapping("/rolecheck")
    @ResponseBody
    public void roleCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
                        + loggedContact + "\"," + "\"userrole\":" + "\"" + cookieRole + "\"}");
            }
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteUser(@RequestBody HomeController.CreateUserRequest request) {

        String deletedUser = request.getUsername();
        usersRepository.deleteUser(deletedUser);

    }

    @PostMapping("/changerole")
    @ResponseBody
    public void changeRole(@RequestBody HomeController.CreateUserRequest request) {

        if (usersRepository.getUserRole(request.getUsername()).equals("adminRole")) {
            usersRepository.setUserRole(request.getUsername(), "userRole");
        } else if (usersRepository.getUserRole(request.getUsername()).equals("userRole")) {
            usersRepository.setUserRole(request.getUsername(), "adminRole");
        }

    }

    @PostMapping("/changekey")
    @ResponseBody
    public void changeKey(@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (adminRepository.getAllKeys().size() < 1) {
            adminRepository.save(new AdminEntity(request.getadminkey()));
        } else if (adminRepository.getAllKeys().size() == 1) {
            adminRepository.updateKey(request.getadminkey(), 1);
        }
    }

    @PostMapping("/newpos")
    @ResponseBody
    public void newPos(@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {
        positionRepository.save(new PositionEntity(request.getpositionname()));
    }

    @PostMapping("/assignpos")
    @ResponseBody
    public void assignPos(@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (positionRepository.getAllPositions() != null) {
            usersRepository.updatePosId(request.getPositionid(), request.getUsername());
        }

    }

    @PostMapping("/newteam")
    @ResponseBody
    public void newTeam(@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        teamRepository.save(new TeamEntity(request.getTeamname()));
    }


    public void userTableGenerator(HttpServletResponse response) throws IOException {

        String tableFirstN;
        String tableContact;
        String userrole;
        String userPosition;
        String userTeam;

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> contacts = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> positions = new ArrayList<>();
        ArrayList<String> teams = new ArrayList<>();

        for (int i = 0; i <= usersRepository.getAllUsers().size() - 1; i++) {

            tableFirstN = usersRepository.getAllUsers().get(i).getUsername();
            tableContact = usersRepository.getAllUsers().get(i).getContact();
            userrole = usersRepository.getAllUsers().get(i).getUserRole();



            if (usersRepository.getAllUsers().get(i).getUserPositionname() != null) {
                userPosition = usersRepository.getAllUsers().get(i).getUserPositionname().getPositionname();
            } else {
                userPosition = "pending";
            }

            if (usersRepository.getAllUsers().get(i).getTeamname() != null) {
                userTeam = usersRepository.getAllUsers().get(i).getUserPositionname().getPositionname();
            } else {
                userTeam = "pending";
            }

            names.add(tableFirstN);
            contacts.add(tableContact);
            roles.add(userrole);
            positions.add(userPosition);
            teams.add(userTeam);
        }

        for (int i = 0; i <= names.size(); i++) {

            if (i == 0) {

                if (usersRepository.count() == 1) {

                    response.getWriter().write("[{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                            + "\"," + "\"userrole\":" + "\"" + roles.get(i) + "\"," + "\"position\":" + "\"" + positions.get(i) + "\"}]");
                } else {
                    response.getWriter().write("[{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                            + "\"," + "\"userrole\":" + "\"" + roles.get(i) + "\"," + "\"position\":" + "\"" + positions.get(i) + "\"},");
                }

            } else if (i > 0 && i < names.size() - 1) {
                response.getWriter().write("{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                        + "\"," + "\"userrole\":" + "\"" + roles.get(i) + "\"," + "\"position\":" + "\"" + positions.get(i) + "\"},");

            } else if (i == names.size() - 1) {
                response.getWriter().write("{\"name\":" + "\"" + names.get(i) + "\"," + "\"contact\":" + "\"" + contacts.get(i)
                        + "\"," + "\"userrole\":" + "\"" + roles.get(i) + "\"," + "\"position\":" + "\"" + positions.get(i) + "\"}]");

            }
        }


    }

    public void positionTableGenerator(HttpServletResponse response) throws IOException {

        String positionName;

        int positionId;

        ArrayList<String> positions = new ArrayList<>();
        ArrayList<Integer> positionIds = new ArrayList<>();

        for (int i = 0; i <= positionRepository.getAllPositions().size() - 1; i++) {

            positionName = positionRepository.getAllPositions().get(i).getPositionname();
            positionId = positionRepository.getAllPositions().get(i).getId();
            positions.add(positionName);
            positionIds.add(positionId);
        }

        for (int i = 0; i < positions.size(); i++) {

            if (i == 0) {

                if (positionRepository.count() == 1) {

                    response.getWriter().write("[{\"positionname\":" + "\"" + positions.get(i) +
                            "\"," + "\"id\":" + "\"" + positionIds.get(i) + "\"}]");
                } else {
                    response.getWriter().write("[{\"positionname\":" + "\"" + positions.get(i) +
                            "\"," + "\"id\":" + "\"" + positionIds.get(i) + "\"},");
                }

            } else if (i > 0 && i < positions.size() - 1) {
                response.getWriter().write("{\"positionname\":" + "\"" + positions.get(i) +
                        "\"," + "\"id\":" + "\"" + positionIds.get(i) + "\"},");

            } else if (i == positions.size() - 1) {
                response.getWriter().write("{\"positionname\":" + "\"" + positions.get(i) +
                        "\"," + "\"id\":" + "\"" + positionIds.get(i) + "\"}]");

            }
        }


    }


}





