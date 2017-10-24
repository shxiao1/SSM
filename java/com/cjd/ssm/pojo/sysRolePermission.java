/*
* sysRolePermission.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-21 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

/**
* @Author marcle
* @version 1.0 2017-10-21
 */
public class sysRolePermission implements Serializable {
    /** */
    private Integer id;

    /** */
    private String roleid;

    /** */
    private String permissionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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