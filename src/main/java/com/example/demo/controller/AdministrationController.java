package com.example.demo.controller;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.interfaces.AdminRepository;
import com.example.demo.interfaces.HomeRepository;
import com.example.demo.interfaces.PositionRepository;
import com.example.demo.interfaces.TeamRepository;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@CrossOrigin(origins = "*")
public class AdministrationController {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/admin-page")
    @ResponseBody
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage");
        return modelAndView;
    }

    @PostMapping("/delete")
    @ResponseBody
    public void deleteUser(@RequestBody CreateUserRequest request) {
        delete(request);
    }

    @PostMapping("/change-role")
    @ResponseBody
    public void changeUserRole(@RequestBody CreateUserRequest request) {
        setRole(request);
    }

    @PostMapping("/change-key")
    @ResponseBody
    public void changeAdminKey(@RequestBody CreateUserRequest request) throws IOException {
        setAdminKey(request);
    }

    @PostMapping("/new-position")
    @ResponseBody
    public void newPosition(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        positionRepository.save(new PositionEntity(request.getPositionName()));
    }

    @PostMapping("/assign-position")
    @ResponseBody
    public void assignPosition(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        if (positionRepository.getAllPositions() != null) {
            homeRepository.updatePosId(request.getPositionId(), request.getUsername());
        }
    }

    @PostMapping("/new-team")
    @ResponseBody
    public void newTeam(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        teamRepository.save(new TeamEntity(request.getTeamName()));
    }



    public void delete(CreateUserRequest request) {
        String userToDelete = request.getUsername();

        homeRepository.deleteUser(userToDelete);
    }

    public void setRole(CreateUserRequest request) {
        String username = request.getUsername();

        if (homeRepository.getUserRole(username).equals("adminRole")) {
            homeRepository.setUserRole(username, "userRole");
        } else if (homeRepository.getUserRole(username).equals("userRole")) {
            homeRepository.setUserRole(username, "adminRole");
        }
    }

    public void setAdminKey(CreateUserRequest request) {
        String newAdminKey = request.getAdminKey();

        if (adminRepository.getAllKeys().size() < 1) {
            adminRepository.save(new AdminEntity(newAdminKey));
        } else if (adminRepository.getAllKeys().size() == 1) {
            adminRepository.updateKey(newAdminKey, 1);
        }
    }
}





