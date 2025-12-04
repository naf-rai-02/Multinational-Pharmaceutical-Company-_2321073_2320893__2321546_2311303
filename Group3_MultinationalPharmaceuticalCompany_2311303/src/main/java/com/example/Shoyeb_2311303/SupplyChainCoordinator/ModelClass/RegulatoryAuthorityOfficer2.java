package com.example.Shoyeb_2311303.SupplyChainCoordinator.ModelClass;

public class RegulatoryAuthorityOfficer2 {

    private String officerId;
    private String name;
    private String department;
    private String email;
    private String approvalStatus;


    public RegulatoryAuthorityOfficer2() {
    }

    public RegulatoryAuthorityOfficer2(String officerId, String name, String department, String email, String approvalStatus) {
        this.officerId = officerId;
        this.name = name;
        this.department = department;
        this.email = email;
        this.approvalStatus = approvalStatus;
    }


    public String getOfficerId() {
        return officerId;
    }

    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }


    public void approveTrial() {
        this.approvalStatus = "Approved";
    }

    public void reviewDocuments() {
        // Later logic can be added here
    }
}
