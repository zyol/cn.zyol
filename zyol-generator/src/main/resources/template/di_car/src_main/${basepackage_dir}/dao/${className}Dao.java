<#assign myParentDir="dao">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;

import cn.pinming.pmsuite.persistence.mapper.MyMapper;
import cn.pinming.pmsuite.persistence.Pagination;
import ${basepackage}.entity.${className};

<#include "/copyright_dao.include" >
@Repository
public interface ${className}Dao extends MyMapper<${className}> {
	/**
	 * 分页查找
	 *
	 * @param ${classNameLower}
	 * @param pagination
	 * @param keywords 关键字 提供模糊查询的内容，该字段需要sql注入处理
	 * @param map 扩展字段
	 * @return
	 */
	List<${className}> getPaged(@Param("${classNameLower}") ${className} ${classNameLower},
								@Param("keywords") String keywords,
								@Param("page") Pagination pagination,
								@Param("map") HashMap<String, Object> map);
}
