package com.qy25.sm.service;

import com.qy25.sm.vo.PageVo;

import java.util.List;

/**
 * baseService 用来实现统一拥有的功能
 * 其余的接口用来设计独有的功能
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T,ID> {
    /**
     * 添加
     * @param entity 实体类
     * @return
     */
    int addEntity(T entity);

    /**
     * 批量添加
     * @param entities  实体类集合
     * @return
     */
    int addEntities(List<T> entities);

    /**
     * 删出
     * @param id 删除  通过id
     * @return
     */
    int delEntity(ID id);

    /**
     * 批量删除
     * @param ids id集合
     * @return
     */
    int delEntities(List<ID> ids);

    /**
     * 通过条件删除
     * @param example  条件
     * @return
     */
    int delEntityByExample(Object example);

    /**
     * 更改
     * @param entity 实体类
     * @return
     */
    int updateEntity(T entity);

    /**
     * 批量更改
     * @param entities
     * @return
     */
    int updateEntities(List<T> entities);

    /**
     * 通过条件更改
     * @param example  条件
     * @return
     */
    int updateEntityByExample(T entity,Object example);

    /**
     * 查询
     * @return
     */
    PageVo<T> selectEntities();

    /**
     * 通过条件查询
     * @param example  条件
     * @return
     */
    PageVo<T> selectEntitiesByExample(Object example);

    /**
     * 查询单个
     * @param id 查询  通过id
     * @return
     */
    T selectEntity(ID id);
}
