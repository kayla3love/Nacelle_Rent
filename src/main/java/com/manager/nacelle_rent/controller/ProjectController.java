package com.manager.nacelle_rent.controller;

import com.alibaba.fastjson.JSONObject;
import com.manager.nacelle_rent.entity.Project;
import com.manager.nacelle_rent.entity.User;
import com.manager.nacelle_rent.service.ProjectService;
import com.manager.nacelle_rent.service.UserService;
import com.manager.nacelle_rent.utils.CookieUtil;
import com.manager.nacelle_rent.utils.FileUtil;
import com.manager.nacelle_rent.utils.UserCheckUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.context.annotation.PropertySource;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@PropertySource("classpath:application-dev.properties")
@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Value("${spring.servlet.multipart.location}")//配置文件的固定绝对路径
    private String projectContactUrlFixed;

    private static Logger logger=Logger.getLogger(LoginController.class); // 获取logger实例

    @PostMapping("/createProject")
    public JSONObject createProject(@RequestParam("file") MultipartFile file,@RequestParam String userId,
                                    @RequestParam("projectId") String projectId, @RequestParam("adminAreaId")String adminAreaId,
                                    HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String projectString = projectId + adminAreaId;
        Project project = new Project();
        //建立Project
        project.setProjectId(projectId);
        project.setAdminAreaId(adminAreaId);

        String projectContactUrl = project.getProjectId() + ".pdf";
        projectContactUrl = projectContactUrlFixed + projectContactUrl;//确定存储的路径，为绝对路径+项目ID.pdf

        project.setProjectContactUrl(projectContactUrl);
        //查验权限
        String password = CookieUtil.getCookie(request, "token");
        int flag = UserCheckUtil.checkUser(userId, password, "superWebAdmin");
        int areaId =  userService.checkAdminAreaId(adminAreaId);
        if (flag == 1) {//判断是否具有超级管理员权限
            if (areaId == 1) {//判断是否是区域管理员
                if (FileUtil.upload(file, projectContactUrl)) {
                    int resultFlag = projectService.createProject(project);
                    if (resultFlag == 0) {
                        //回复客户端已经有相同的id
                        jsonObject.put("message", "exist");
                    } else if (resultFlag == 1) {
                        logger.info(projectId+"新建成功，管理员为："+adminAreaId);
                        try {
                            //通知前端更新
                            //WebSocketServer.sendInfo(userString,"superWebAdmin");
                            messagingTemplate.convertAndSend("/topic/subscribeTest", projectString);
                            //回复客户端已提交申请请等待
                            jsonObject.put(projectId,"新建成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        jsonObject.put("message", "default");//插入失败,网络或数据库故障
                    }
                }
                return jsonObject;
            } else {
                jsonObject.put("he is not a area amdin", false);
            }
        }else{
            jsonObject.put("isAllowed", false);
        }
        return jsonObject;
    }
    //获取指定项目条目（具体指定由客户端设计）
    @GetMapping("/projectInfo")
    public JSONObject getProjectInfo(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        //进行身份验证，只能通过cookie认证
        String password = CookieUtil.getCookie(request, "token");
        int flag = UserCheckUtil.checkUser(userId, password, "superWebAdmin");
        if(flag == 1){
            List<Project> projectList = projectService.getProjectList();
            if(projectList != null){
                jsonObject.put("projectList",projectList);
            }
        }else{
            jsonObject.put("isAllowed",false);
        }
        return jsonObject;
    }

    //获取被点击项目的详细信息
    @GetMapping("/projectDetailInfo")
    public JSONObject getProjectDetailInfo(@RequestParam String projectId, @RequestParam String userId,
                                           HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        //进行身份验证，只能通过cookie认证
        String password = CookieUtil.getCookie(request, "token");
        int flag = UserCheckUtil.checkUser(userId, password, "superWebAdmin");
        if(flag == 1){
            jsonObject = projectService.getProjectDetail(projectId);
        }else{
            jsonObject.put("isAllowed",false);
        }
        return jsonObject;
    }
}
