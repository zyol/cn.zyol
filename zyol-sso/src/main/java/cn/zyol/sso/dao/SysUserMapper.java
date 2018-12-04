package cn.zyol.sso.dao;

import cn.zyol.basic.dao.MyMapper;
import cn.zyol.sso.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends MyMapper<SysUser> {

}
