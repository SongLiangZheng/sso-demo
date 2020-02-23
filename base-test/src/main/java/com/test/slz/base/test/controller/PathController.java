package com.test.slz.base.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;

@RestController
@Slf4j
public class PathController {
    @Autowired
    private ServletContext servletContext;
    public static String PROJECT_PATH = new ApplicationHome(PathController.class).getSource()
            .getParentFile().getParentFile().getParentFile().getParentFile().getPath();

    @RequestMapping("path1")
    @ResponseBody
    public String path1(){
        File source = new ApplicationHome(CookieController.class).getSource();
        System.out.println(source.getPath());
        System.out.println(source.getParentFile().getPath());
        return source.getParentFile().toString();
    }
    @RequestMapping("path2")
    @ResponseBody
    public String path2() throws FileNotFoundException {
        File files = new File(PROJECT_PATH +File.separator+ "files");
        if (!files.exists())
            files.mkdir();
        log.info(files.getPath()+"创建成功！");
        return ResourceUtils.getURL("classpath:").getPath();
    }
    @RequestMapping("path3")
    @ResponseBody
    public String path3() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(path);
        return path;
    }

}
