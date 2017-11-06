/*
* SysPermission.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

import com.cjd.ssm.base.BaseEntity;

public class SysPermission extends BaseEntity<SysPermission> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** */
    private String name;
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

}