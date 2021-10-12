package com.example.demo.model;

public class CreateUserRequest {

    private String username;
    private String password;
    private String firstname;
    private String lastName;
    private String contact;
    private String userRole;
    private String adminKey;
    private String positionName;
    private String teamName;
    private String projectName;
    private String clientName;
    private String clientContact;
    private String teamId;
    private int clientId;
    private int id;
    private int projectId;
    private int positionId;


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

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public String getPositionName() {
        return positionName;
    }

    public int getPositionId() {
        return positionId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public int getClientId() {
        return clientId;
    }



}
