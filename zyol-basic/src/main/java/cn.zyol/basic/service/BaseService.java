package cn.zyol.basic.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends Serializable> {

    //BaseMapper

    /**
     * 保存一个实体，null属性也会保存
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 保存一个实体，null属性不会保存
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     */
    int delete(T record);

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 根据主键更新属性不为null的值
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    List<T> select(T record);

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     */
    T selectOne(T record);

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     */
    int selectCount(T record);


    // IdsMapper

    /**
     * 根据主键@Id进行查询，多个Id以逗号,分割
     *
     * @param ids
     * @return
     */
    List<T> selectByIds(String ids);

    /**
     * 根据主键@Id进行删除，多个Id以逗号,分割
     *
     * @param ids
     * @return
     */
    int deleteByIds(String ids);


}
