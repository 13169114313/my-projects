package com.blogger.controller.base.impl;

import com.alibaba.fastjson.JSON;
import com.blogger.controller.base.BaseDao;
import com.blogger.controller.base.BaseMapper;
import com.blogger.core.MyException;
import com.blogger.utils.ReType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDaoImpl<T,E extends Serializable> implements BaseDao<T,E> {

  private static Logger logger= LoggerFactory.getLogger(BaseDaoImpl.class);

  public abstract BaseMapper<T,E> getMapper();

  @Override
  public int deleteByPrimaryKey(E id) {
    return getMapper().deleteByPrimaryKey(id);
  }

  @Override
  public int insert(T record) {
    record=addValue(record,true);
    return getMapper().insert(record);
  }

  /**
   * 通用注入创建 更新信息 可通过super调用
   * @param record
   * @param flag
   * @return
   */
  public T  addValue(T record,boolean flag){
//    CurrentUser currentUser= new CurrentUser();
//    //统一处理公共字段
//    Class<?> clazz=record.getClass();
//    try {
//      if(flag){
//        Field field=clazz.getDeclaredField("createBy");
//        field.setAccessible(true);
//        field.set(record,currentUser.getId());
//        Field fieldDate=clazz.getDeclaredField("createDate");
//        fieldDate.setAccessible(true);
//        fieldDate.set(record,new Date());
//      }else{
//        Field field=clazz.getDeclaredField("updateBy");
//        field.setAccessible(true);
//        field.set(record,currentUser.getId());
//        Field fieldDate=clazz.getDeclaredField("updateDate");
//        fieldDate.setAccessible(true);
//        fieldDate.set(record,new Date());
//      }
//    } catch (NoSuchFieldException e) {
//      //无此字段
//    } catch (IllegalAccessException e) {
//      e.printStackTrace();
//    }
    return record;
  }

  @Override
  public int insertSelective(T record) {
    record=addValue(record,true);
    return getMapper().insertSelective(record);
  }

  @Override
  public T selectByPrimaryKey(E id) {
    return getMapper().selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(T record) {
    record=addValue(record,false);
    return getMapper().updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(T record) {
    record=addValue(record,false);
    return getMapper().updateByPrimaryKey(record);
  }

  @Override
  public List<T> selectListByPage(T record){
      return getMapper().selectListByPage(record);
  }

  /**
   * 公共展示类
   * @param t 实体
   * @param page 页
   * @param limit 行
   * @return
   */
  @Override
  public Page<T> show(T t,int page,int limit){
    return PageHelper.startPage(page,limit,true);

//    List<T> tList=null;
//    Page<T> tPage= PageHelper.startPage(page,limit,true);
//
//    try{
//      tList= getMapper().selectListByPage(t);
//    }catch (MyException e){
//      logger.error("class:BaseServiceImpl ->method:show->message:"+e.getMessage());
//      e.printStackTrace();
//    }
//    ReType reType=new ReType(tPage.getTotal(),tList);
//    return reType;
  }

}
