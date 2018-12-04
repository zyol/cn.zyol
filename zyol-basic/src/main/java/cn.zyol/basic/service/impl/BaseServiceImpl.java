package cn.zyol.basic.service.impl;

import cn.zyol.basic.dao.MyMapper;
import cn.zyol.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {
    @Autowired
    protected MyMapper<T> mapper;

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }
    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     */
    @Override
    public int delete(T record) {
        return mapper.delete(record);
    }


    /**
     * 根据主键更新
     *
     * @param record
     * @return
     */
    @Override
    public int update(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<T> select(T record) {
        return mapper.select(record);
    }

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     */
    @Override
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     */
    @Override
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }


    // IdsMapper

    /**
     * 根据主键@Id进行查询，多个Id以逗号,分割
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> selectByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }


}
