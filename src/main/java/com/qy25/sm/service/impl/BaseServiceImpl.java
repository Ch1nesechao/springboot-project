package com.qy25.sm.service.impl;

import com.github.pagehelper.PageInfo;
import com.qy25.sm.mapper.BaseMapper;
import com.qy25.sm.service.BaseService;
import com.qy25.sm.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    @Autowired
    private BaseMapper<T, ID> baseMapper;

    @Override
    public int addEntity(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int addEntities(List<T> entities) {
        entities.forEach(entity -> {
            baseMapper.insert(entity);
        });
        return 1;
    }

    @Override
    public int delEntity(ID id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delEntities(List<ID> ids) {
        ids.forEach(id -> {
            baseMapper.deleteByPrimaryKey(id);
        });
        return 1;
    }

    @Override
    public int delEntityByExample(Object example) {
        return baseMapper.deleteByExample(example);
    }

    @Override
    public int updateEntity(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateEntities(List<T> entities) {
        entities.forEach(entity -> {
            baseMapper.updateByPrimaryKey(entity);
        });
        return 1;
    }

    @Override
    public int updateEntityByExample(T entity, Object example) {
        return baseMapper.updateByExample(entity,example);
    }

    @Override
    public PageVo<T> selectEntities() {
        List<T> list = baseMapper.selectByExample(null);
        return setPageVo(list);
    }

    @Override
    public PageVo<T> selectEntitiesByExample(Object example) {
        List<T> list = baseMapper.selectByExample(example);
        return setPageVo(list);
    }

    @Override
    public T selectEntity(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取pagevo
     *
     * @param list
     * @return
     */
    protected PageVo<T> setPageVo(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setList(list);
        pageVo.setTotal(total);
        return pageVo;
    }
}
