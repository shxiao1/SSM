package com.cjd.ssm.base;

import java.util.List;


public interface CrudDao<T> {
    int deleteByPrimaryKey(String id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(String id);
    List<T> selectAll(T record);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
    int batchDelete(T record);
}