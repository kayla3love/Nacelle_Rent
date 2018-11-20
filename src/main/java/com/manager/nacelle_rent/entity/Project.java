package com.manager.nacelle_rent.entity;

public class Project {
    //项目状态
    private String projectId;
    private String projectState;
    private String projectStart;
    private String projectContactUrl;
    private String projectCertUrl;
    private String projectEnd;

    //获取项目状态函数
    public String getProjectId(){return projectId;}

    public String getProjectState(){return projectState;}

    public String getProjectStart(){return projectStart;}

    public String getProjectContactUrl(){return projectContactUrl;}

    public String getProjectCertUrl(){return projectCertUrl;}

    public String getProjectEnd(){return projectEnd;}

}
