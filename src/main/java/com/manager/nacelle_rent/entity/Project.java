package com.manager.nacelle_rent.entity;

public class Project {
    private String projectId;
    private String projectName;
    private String projectState;
    private String projectStart;
    private String projectEnd;
    private String projectContactUrl; //项目合同Url
    private String projectCertUrl;    //项目证书Url
    private String adminAreaId;
    private String adminRentId;
    private String boxList;
    private String projectBuilders;

    public String getAdminAreaId() {
        return adminAreaId;
    }

    public void setAdminAreaId(String adminAreaId) {
        this.adminAreaId = adminAreaId;
    }

    public String getAdminRentId() {
        return adminRentId;
    }

    public void setAdminRentId(String adminRentId) {
        this.adminRentId = adminRentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(String projectStart) {
        this.projectStart = projectStart;
    }

    public String getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(String projectEnd) {
        this.projectEnd = projectEnd;
    }

    public String getProjectContactUrl() {
        return projectContactUrl;
    }

    public void setProjectContactUrl(String projectContactUrl) {
        this.projectContactUrl = projectContactUrl;
    }

    public String getProjectCertUrl() {
        return projectCertUrl;
    }

    public void setProjectCertUrl(String projectCertUrl) {
        this.projectCertUrl = projectCertUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBoxList() {
        return boxList;
    }

    public void setBoxList(String boxList) {
        this.boxList = boxList;
    }

    public String getProjectBuilders() {
        return projectBuilders;
    }

    public void setProjectBuilders(String projectBuilders) {
        this.projectBuilders = projectBuilders;
    }
}
