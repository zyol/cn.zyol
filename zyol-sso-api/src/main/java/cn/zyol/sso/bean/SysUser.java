package cn.zyol.sso.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode()
//@Table(name = "t_sys_user_role")//设置数据库中表名字
public class SysUser implements Serializable {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "log_in_name")
    private String logInName;
}
