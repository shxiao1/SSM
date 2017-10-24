/*
* sysPermission.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-21 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

public class sysPermission implements Serializable {
    /** */
    private Integer id;

    /** */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}