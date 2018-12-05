<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import ${basepackage}.entity.${className};
import cn.pinming.pmsuite.common.search.PagedSearchRequest;
import cn.pinming.pmsuite.persistence.PagedList;
import cn.pinming.pmsuite.service.BusiService;



<#include "/copyright_service.include" >
public interface ${className}Service extends BusiService<${className}>{



}
