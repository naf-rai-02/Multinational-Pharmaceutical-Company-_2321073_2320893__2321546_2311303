package com.example.Shoyeb_2311303.ClinicalTrialCoordinator.ModelClasses;

import java.util.List;

public class ClinicalResearchCoordinator2 {

    private String coordinatorId;
    private String name;
    private String email;
    private String phone;
    private List<String> assignedTrials;


    public ClinicalResearchCoordinator2(String coordinatorId, String name, String email, String phone, List<String> assignedTrials) {
        this.coordinatorId = coordinatorId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.assignedTrials = assignedTrials;
    }


    public ClinicalResearchCoordinator2() {}


    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getAssignedTrials() {
        return assignedTrials;
    }

    public void setAssignedTrials(List<String> assignedTrials) {
        this.assignedTrials = assignedTrials;
    }
}

