/*
* SysAccount.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;

import com.cjd.ssm.base.BaseEntity;

public class SysAccount extends BaseEntity<SysAccount> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** */
    private String username;

    /** */
    private String password;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public String getSysAccount()
	{
		return (String) SecurityUtils.getSubject().getPrincipal();
	}
    
}