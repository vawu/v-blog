package com.vawu.vblog.pojo;

import org.springframework.data.annotation.Persistent;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Persistent
abstract class BaseDo implements Serializable {
    @Id
    private String id;
    private Date createTime;
    private Date updateTime;
}
