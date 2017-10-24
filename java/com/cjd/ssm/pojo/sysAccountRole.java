/*
* sysAccountRole.java
* Copyright(C) 2009-2016 北京盘古世纪科技发展有限公司
* All right Reserved
* 2017-10-21 created
*/
package com.cjd.ssm.pojo;

import java.io.Serializable;

public class sysAccountRole implements Serializable {
    /** */
    private Integer id;

    /** */
    private String accountid;

    /** */
    private String rowid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid == null ? null : rowid.trim();
    }
}