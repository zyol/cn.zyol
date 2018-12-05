<#assign myParentDir="service.impl">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.impl.service;

import java.util.List;
import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pinming.pmsuite.common.search.PagedSearchRequest;
import cn.pinming.pmsuite.persistence.PagedList;
import cn.pinming.pmsuite.persistence.Pagination;
import cn.pinming.pmsuite.utils.ReflectUtils;
import cn.pinming.pmsuite.service.impl.BaseServiceImpl;

import ${basepackage}.dao.${className}Dao;

<#include "/copyright_serviceimpl.include" >
@Service("${classNameLower}Service")
public class ${className}ServiceImpl  extends BaseServiceImpl<${className}> implements ${className}Service {
	@Autowired
    private ${className}Dao ${classNameLower}Dao;

	public ${className}ServiceImpl(){
		super(${className}.class);
	}

	@Override
	public PagedList<${className}> getPaged(PagedSearchRequest<${className}> request){
		if(request==null){
			request=new PagedSearchRequest<${className}>();
		}
		Pagination pagination=request.toPagination();
		${className} filter=new ${className}();
		ReflectUtils.fillObjectByMap(request.getFilter(),null,filter);
		List<${className}> list = ${classNameLower}Dao.getPaged(filter,request.getSearchKey(),pagination,request.getFilter());
		return new PagedList<>(list,pagination);
	}


}
