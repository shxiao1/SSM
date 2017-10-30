/*
* SysAccountRole.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

import com.cjd.ssm.base.BaseEntity;
public class SysAccountRole extends BaseEntity<SysAccountRole> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /** */
    private String accountid;

    /** */
    private String roleid;



    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public String getroleid() {
        return roleid;
    }

    public void setroleid(String roleid) {
        this.roleid = roleid;
    }

}