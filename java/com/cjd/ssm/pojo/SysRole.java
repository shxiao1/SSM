/*
* SysRole.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;
import java.util.Date;

import com.cjd.ssm.base.BaseEntity;

/**
* @Author marcle
* @version 1.0 2017-10-26
 */
public class SysRole extends BaseEntity<SysRole> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** */
    private String rolename;


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

}