package com.example.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shop.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper extends BaseMapper<UserBean> {
    @Select("select * from tbl_user where username =#{username} and password=#{password} and status='卖家'")
    UserBean getUser(UserBean bean);
}
