package com.vawu.vblog.controller;

import com.vawu.vblog.pojo.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Api(value = "登录", tags = "用户用户登录")
@Controller
public class LoginController {
    @ApiOperation(value = "/login", httpMethod = "POST", notes = "登录")
    @PostMapping("/login")
    public String sysLogin(SysRole role) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(role.getSysName(), role.getSysPassword());
        if (!subject.isAuthenticated())
            subject.login(token);
        return "login";
    }

}
