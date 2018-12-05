import org.springframework.web.bind.annotation.PostMapping;

<#assign className = table.className>
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign rootBasepackage = basepackage?substring(0,basepackage?last_index_of("."))>
<#assign pkJavaType = table.idColumn.javaType>
<#assign clasNameWithoutPrefix = classNameLower?replace(rootPagefloder,'')>
package  ${basepackage}.web;


import cn.pinming.pmsuite.WebContext;
import cn.pinming.pmsuite.api.ApiResult;
import cn.pinming.pmsuite.common.search.PagedSearchRequest;
import cn.pinming.pmsuite.persistence.PagedList;
import cn.pinming.pmsuite.utils.DateUtils;
import cn.pinming.pmsuite.utils.TypeUtils;
import cn.pinming.pmsuite.web.security.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
<#assign myParentDir="${table.classNameFirstLower}">


<#include "/copyright_controller.include" >
<#if (basepackage?last_index_of('.') > 0)>
@Controller("${ basepackage?substring((basepackage?last_index_of('.'))+1)}_${classNameLowerCase}Controller")
@RequestMapping(value="/${rootPagefloder}/${classLowerNameWithoutPrefix}")
<#else>
@Controller
@RequestMapping(value="/${rootPagefloder}/${classLowerNameWithoutPrefix}")
</#if>
public class ${className}Controller  {
    private   final Logger logger = LoggerFactory.getLogger(${className}Controller.class);
	 @Autowired
	private ${className}Service ${classNameLower}Service;

    /**
    * 首页
    *
    * @return
    */
    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/${rootPagefloder}/${classLowerNameWithoutPrefix}/list");
    }



    /**
     * 获取单条记录
     *
     * @return
     */
    @RequestMapping("/get")
    public ModelAndView get(String id, boolean edit, Model model) {
        //应用基本信息
        ${className} viewModel;
        if (null != id) {
            viewModel = ${classNameLower}Service.getByKey(id);
        } else {
            viewModel = new ${className}();
        }
        model.addAttribute("data", viewModel);
        if (null == id || edit) {
            return new ModelAndView("/${rootPagefloder}/${classLowerNameWithoutPrefix}/edit");
        }
        return new ModelAndView("/${rootPagefloder}/${classLowerNameWithoutPrefix}/detail");
    }



    /**
     *  分页数据
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PagedList<${className}> list(PagedSearchRequest<${className}> request) {
        return ${classNameLower}Service.getPaged(request);
    }



    /**
     * 保存更新
     *
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ApiResult save(@Valid ${className} data, HttpServletRequest request) {
        UserAccount sessionUser = WebContext.getUserAccount( );
        ApiResult result;
        try {
            if (StringUtils.isNullOrEmpty(data.getId())) {
                data.setCreateTime(DateUtils.now());
                result = ${classNameLower}Service.create(data);
            } else {
                result = ${classNameLower}Service.update(data);
            }
        } catch (Exception e) {
            logger.debug("保存信息异常：{}", e.getMessage());
            result=ApiResult.create();
            result.error("保存信息失败");
        }
        return result;
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ResponseBody
    public ApiResult delete(String ids) {
        ApiResult result =ApiResult.create();
        try {
            List<String> idList = TypeUtils.parseToStrList(ids);
            if (idList.size() == 0) {
                return result.error("请选中一条记录删除");
            }
            for (String id : idList) {
                result = ${classNameLower}Service.deleteByKey(id);
            }
        } catch (Exception e) {
            logger.debug("删除异常：{}", e.getMessage());

            result=ApiResult.create();
            result.error("删除失败");
        }
        return result;
    }
}
