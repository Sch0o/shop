package com.example.shop.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data  //生成set、get
@TableName("tbl_user")
public class UserBean {
    @TableId(type = IdType.AUTO)
    public Integer id;
    public String username;
    public String password;
    public String user;
    public String status;
    public String store;
}
