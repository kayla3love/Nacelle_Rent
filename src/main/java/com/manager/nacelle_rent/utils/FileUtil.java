package com.manager.nacelle_rent.utils;

import com.manager.nacelle_rent.controller.LoginController;
import com.manager.nacelle_rent.entity.User;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件上传工具包
 */
public class FileUtil {
    private static Logger logger=Logger.getLogger(LoginController.class); // 获取logger实例
    /**
     *
     * @param file 文件
     * @param path 文件存储路径
     * @return
     */
    public static boolean upload(MultipartFile file, String path){
        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(path)));//保存图片到目录下
                out.write(file.getBytes());
                out.flush();
                out.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("图片保存失败" + e.getMessage());
                return false;
            }
        }else{
            logger.info("上传图片文件为空");
            return false;
        }
    }
}
