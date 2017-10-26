/*
* SysRolePermission.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-26 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

import com.cjd.ssm.base.BaseEntity;

public class SysRolePermission extends BaseEntity<SysRolePermission> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** */
    private String roleid;

    /** */
    private String permissionid;



    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(String permissionid) {
        this.permissionid = permissionid == null ? null : permissionid.trim();
    }

}