package com.example.demo.controller;

import com.example.demo.interfaces.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@CrossOrigin(origins = "*")
public class UsersInfoController {

    @Autowired
    private HomeRepository homeRepository;

    @GetMapping("/users-info-page")
    @ResponseBody
    public ModelAndView profilePage() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersInfoPage");
        return modelAndView;
    }

    @PostMapping("/users")
    @ResponseBody
    public void generateUserTable(HttpServletResponse response) throws IOException {
        userTableGenerator(response);
    }

    public void userTableGenerator(HttpServletResponse response) throws IOException {
        String tableUsername;
        String tableContact;
        String tableRole;

        for (int i = 0; i <= homeRepository.getAllUsers().size() - 1; i++) {
            tableUsername = homeRepository.getAllUsers().get(i).getUsername();
            tableContact = homeRepository.getAllUsers().get(i).getContact();
            tableRole = homeRepository.getAllUsers().get(i).getUserRole();

            String status = ("{\"name\":" + "\"" + tableUsername + "\"," + "\"contact\":" + "\"" + tableContact
                    + "\"," + "\"userrole\":" + "\"" + tableRole + "\"," + "\"position\":" + "\"" + "positionTest" + "\"}");

            if (i == 0) {
                if (homeRepository.count() == 1) {
                    response.getWriter().write("[]");
                } else {
                    response.getWriter().write("[" + status + ",");
                }
            } else if (i > 0 && i < homeRepository.getAllUsers().size() - 1) {
                response.getWriter().write(status + ",");
            } else if (i == homeRepository.getAllUsers().size() - 1) {
                response.getWriter().write(status + "]");
            }
        }
    }
}
