package com.manager.nacelle_rent.entity;

import java.util.Date;

public class Project {
    private String projectId;
    private int projectState;
    private Date projectStart;
    private Date projectEnd;
    private String projectContactUrl; //项目合同Url
    private String projectCertUrl;    //项目证书Url

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getProjectState() {
        return projectState;
    }

    public void setProjectState(int projectState) {
        this.projectState = projectState;
    }

    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
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
}
