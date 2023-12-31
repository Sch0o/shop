package com.example.shop.controller;

import com.example.shop.bean.ProductBean;
import com.example.shop.mapper.CategoryMapper;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @RequestMapping("/list")
    public String list(HttpServletRequest req){
        System.out.println("list");
        req.setAttribute("retList",productMapper.selectList(null));
        return "/product/list";
    }

    @RequestMapping("/del")
    public String del(Integer id){
        productMapper.deleteById(id);
        return "redirect:/product/list";
    }
    @GetMapping("/add")
    public String add(Integer id,HttpServletRequest req){
        req.setAttribute("cateList",categoryMapper.selectList(null));
        req.setAttribute("bean",id==null?null:productMapper.selectById(id));
        return "/product/add";
    }
    @PostMapping("/add")
    public String add(ProductBean bean, HttpServletResponse resp){
        if(NotNullUtil.isBlank(bean)){
            return jsAlert("请完善信息",resp);
        }
        System.out.println("1表单");
        if(bean.id==null){
            bean.ctime=new Date();
            productMapper.insert(bean);
        }else
            productMapper.updateById(bean);
        return "redirect:/product/list";
    }
}
