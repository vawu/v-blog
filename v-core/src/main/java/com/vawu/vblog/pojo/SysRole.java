package com.vawu.vblog.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Persistent;

import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_menu")
@Persistent
public class SysRole extends BaseDo {

    private String sysName;
    private String sysPassword;
    private String createBy;
    private String updateBy;

}
