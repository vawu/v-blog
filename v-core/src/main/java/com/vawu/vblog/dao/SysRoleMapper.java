package com.vawu.vblog.dao;

import com.vawu.vblog.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysRoleMapper extends Mapper<User> {

}
