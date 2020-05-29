package com.dossen.metadata.controller;

import com.dossen.metadata.bean.User;
import com.dossen.metadata.util.Result;
import com.dossen.metadata.util.SQLUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器(实现请求分发,一般会在控制器当中定义接口)
 */
@RestController //控制器类
@RequestMapping("/user") //映射路径
public class UserController {

    //登录接口 http://ip:端口/user/login
    @RequestMapping(value = "/login")
    public Result login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        //1.用户名为空 提示 "用户名不能为空"
        if (username == null || username.trim().length() == 0) {
            return new Result("0", "用户名不能为空");
        }
        //2.密码为空 提示 "密码不能为空"
        if (password == null || password.trim().length() == 0) {
            return new Result("0", "密码不能为空");
        }
        //3.用户名和密码不为空的情况下 jdbc完成数据库查询验证
        if (username != null && password != null) {
            int num = SQLUtil.findSQL(user);
            System.out.println("-------------------------" + num + "-------------------------");
            if (num > 0) {
                return new Result("1", "登陆成功");
            }
        }
        return new Result("0", "用户名或者密码不正确");
    }
}
