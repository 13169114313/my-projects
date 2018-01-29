package com.blogger.controller.base;

import com.blogger.utils.ReType;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 通用service层
 */
public interface BaseDao<T,E extends Serializable>{
  /**
   * 根据id删除
   * @param id
   * @return
   */
  int deleteByPrimaryKey(E id);

  /**
   * 插入
   * @param record
   * @return
   */
  int insert(T record);

  /**
   *插入非空字段
   * @param record
   * @return
   */
  int insertSelective(T record);

  /**
   * 根据id查询
   * @param id
   * @return
   */
  T selectByPrimaryKey(E id);

  /**
   * 更新非空数据
   * @param record
   * @return
   */
  int updateByPrimaryKeySelective(T record);

  /**
   * 更新
   * @param record
   * @return
   */
  int updateByPrimaryKey(T record);


  List<T> selectListByPage(T record);

  public Page<T> show(T t, int page, int limit);

}
