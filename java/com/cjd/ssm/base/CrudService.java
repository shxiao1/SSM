package com.cjd.ssm.base;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cjd.ssm.utils.MyUtils;


@Transactional(readOnly = true)
public  class CrudService<D extends CrudDao<T>, T extends BaseEntity<T> >  {
	

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 持久层对象
	 */
	@Autowired(required=false)
	protected D dao;
	
	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
    public int deleteById(String id){
    	return dao.deleteByPrimaryKey(id);
    }
	
	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
    public int batchDelete(List<String> ids){
		try {
			if(ids!=null && ids.size()>0){
				for(String id:ids){
					dao.deleteByPrimaryKey(id);
				}
				return ids.size();
			}
			return 0;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
    }

    /**
     * 插入数据
     */
	@Transactional(readOnly = false)
    public int insert(T record){
		record.setId(MyUtils.uuid());
		record.setDelFlag("0");
		
    	return dao.insert(record);
    }

    /**
 	 *选择性的插入数据
     */
	@Transactional(readOnly = false)
    public int insertSelective(T record){
    	
    	record.setId(MyUtils.uuid());;	
    	return dao.insertSelective(record);
    }

    /**
     *根据主键获取数据
     *
     */
    public T findById(String id){
    	return dao.selectByPrimaryKey(id);
    }
    /***
     * 根据实体对象条件进行查询
     * @param record
     * @return
     */
    public List<T> findAll(T record){
    	return dao.selectAll(record);
    }

    /**
     * 根据主键选择性的更新数据
     *
     */
    @Transactional(readOnly = false)
    public int updateByIdSelective(T record){

    	return dao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新数据
     *
     */
    @Transactional(readOnly = false)
    public int updateById(T record){
  
    	return dao.updateByPrimaryKey(record);
    }
    
  
    


	
}
