package com.example.demo.model.controller;


import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@CrossOrigin(origins = "*")
public class ProjectsControllers {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/projects")
    @ResponseBody
    public ModelAndView projectsPage (){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("projects");
        return modelAndView;
    }

    @PostMapping("/newproject")
    @ResponseBody
    public void newProject (@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {
        projectRepository.save(new ProjectEntity(request.getProjectname()));
    }

    @PostMapping("/assignproject")
    @ResponseBody
    public void assignProject (@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (teamRepository.getAllTeams()!=null) {
            teamRepository.updatePosId(request.getProjectid(), request.getTeamname());
        }
    }

    @PostMapping("/newclient")
    @ResponseBody
    public void newClient (@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {
        clientRepository.save(new ClientEntity(request.getClientname(), request.getClientcontact()));
    }

    @PostMapping("/assignclient")
    @ResponseBody
    public void assignClient (@RequestBody HomeController.CreateUserRequest request, HttpServletResponse response) throws IOException {

        if (projectRepository.getAllProjects()!=null) {
            projectRepository.updateProjId(request.getClientid(), request.getProjectname());
        }
    }


}
