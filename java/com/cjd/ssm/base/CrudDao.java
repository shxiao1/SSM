package com.cjd.ssm.base;

import java.util.List;

public interface CrudDao<T>  {
	/**
	 * ��������ɾ������
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
      
    /**
     * ��������
     */
    int insert(T record);

    /**
 	 *ѡ���ԵĲ�������
     */
    int insertSelective(T record);

    /**
     *����������ȡ����
     *
     */
    T selectByPrimaryKey(String id);
    /***
     * ����ʵ������������в�ѯ
     * @param record
     * @return
     */
    List<T> selectAll(T record);

    /**
     * ��������ѡ���Եĸ�������
     *
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * ����������������
     *
     */
    int updateByPrimaryKey(T record);
    /***
     * ����ɾ��
     * @return
     */
    int batchDelete(T record);

}