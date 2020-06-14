package com.ruoyi.framework.web.service;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息获取
 */
@Service("user")
public class UserService {

    @Autowired
    private ISysUserService userService;

    public String getNameById(Long userId) {
        SysUser sysUser = userService.selectUserById(userId);
        return sysUser == null ? "" : sysUser.getUserName();
    }

    public List<SysUser> getAllUser(){
       return userService.selectUserList(new SysUser());
    }
}
