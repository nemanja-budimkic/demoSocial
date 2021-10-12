package com.example.demo.controller;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.interfaces.*;
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
    private HomeRepository homeRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/projects-page")
    @ResponseBody
    public ModelAndView projectsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("projectsPage");
        return modelAndView;
    }

    @PostMapping("/new-project")
    @ResponseBody
    public void newProject(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        projectRepository.save(new ProjectEntity(request.getProjectName()));
    }

    @PostMapping("/assign-project")
    @ResponseBody
    public void assignProject(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        if (teamRepository.getAllTeams() != null) {
            teamRepository.updatePosId(request.getProjectId(), request.getTeamName());
        }
    }

    @PostMapping("/new-client")
    @ResponseBody
    public void newClient(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        clientRepository.save(new ClientEntity(request.getClientName(), request.getClientContact()));
    }

    @PostMapping("/assign-client")
    @ResponseBody
    public void assignClient(@RequestBody CreateUserRequest request, HttpServletResponse response) throws IOException {
        if (projectRepository.getAllProjects() != null) {
            projectRepository.updateProjectId(request.getClientId(), request.getProjectName());
        }
    }
}
