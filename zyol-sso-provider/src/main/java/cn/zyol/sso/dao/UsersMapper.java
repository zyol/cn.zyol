package cn.zyol.sso.dao;

import cn.zyol.basic.dao.MyMapper;
import cn.zyol.sso.bean.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper extends MyMapper<Users> {

}
