package com.example.shop.controller;

import com.example.shop.bean.UserBean;
import com.example.shop.mapper.UserMapper;
import com.example.shop.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLEncoder;

@Controller // 使这个类成为控制器，使@RequestMapping发挥作用
public class UserController {
    @Autowired  // 注入,就像是new
    UserMapper userMapper;

    // http://localhost:8080/login?username=xxx&password=xxx
    @RequestMapping("/login")   // 将错误抛出给Tomcat吸收
    public String login(UserBean bean, HttpServletRequest req) throws Exception{
        UserBean user = userMapper.getUser(bean);
        if (user == null) {
            System.out.println("用户名或密码错误");
            return "redirect:/index.html?msg=" + URLEncoder.encode("用户名或密码错误", "utf-8"); // 重定向
        } else {
            return "redirect:/main?uid=" + user.id;
//            System.out.println("登陆成功");
//            req.setAttribute("user", user);
//            return "/main"; //转发,会自动给我们加“.html”
        }
    }

    @RequestMapping("/main")    // 见到Request，转发带参
    public String main(int uid, HttpServletRequest req) {
//        System.out.println("打印uid：" + uid);
        // 按照id查询这条记录
        req.setAttribute("user", userMapper.selectById(uid));
        return "/main"; // 不能加.html后缀，freemarker会自动帮我们添加

    }

    // localhost:8080/upload
    @ResponseBody  // 底层是json技术，依赖包是jackson
    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        // 保存file
        FileUtil.createFile("N:/create/shop/upload");
        file.transferTo(new File("N:/create/shop/upload/" + fileName));
        // 1.转发   2.重定向     3.
        return "/shop/upload/" + fileName;  // 将字符串返回给网页
    }

}