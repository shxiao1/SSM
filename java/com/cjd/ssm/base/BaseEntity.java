package com.cjd.ssm.base;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class BaseEntity<T> implements Serializable
{

	private static final long serialVersionUID = 1L;

	protected String id;
	protected String orderStr="created asc";
	/** */
	private String creater;

	/** */
	@JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")  
	private Date created;

	/** */
	private String updater;

	/** */
	@JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")  
	private Date updated;

	protected String sort;// datagrid的参数

	protected String order;// datagrid的参数

	/** */
	private String delFlag="0";

    
	public String getCreater()
	{
		return creater;
	}

	public void setCreater(String creater)
	{
		this.creater = creater;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public String getUpdater()
	{
		return updater;
	}

	public void setUpdater(String updater)
	{
		this.updater = updater;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}


	public String getOrderStr()
	{
		if (sort!=null&&sort.length()>0 && order!=null && order.length()>0 ) {
			this.orderStr = sort+" "+order;
		}
		
		return orderStr;
	}

	public void setOrderStr(String orderStr)
	{
		
		this.orderStr = orderStr;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(String delFlag)
	{
		this.delFlag = delFlag;
	}

}
