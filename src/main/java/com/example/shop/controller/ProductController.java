package com.example.shop.controller;

import com.example.shop.mapper.CategoryMapper;
import com.example.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/product")
public class ProductController {
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
        req.setAttribute("cateList",categoryMapper);
        req.setAttribute("bean",id==null?null:productMapper.selectById(id));
        return "/product/add";
    }
    @PostMapping("/add")
    public String add(){
        System.out.println("1表单");
        return null;
    }
}
