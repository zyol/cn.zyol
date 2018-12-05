<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLower?replace(rootPagefloder,'')>
var mytitle;
var title;
var saveState=false;
$(function(){
    mytitle = parent.giveTitle();
    title = $(".app-label").text();
    //加载数据
    loadData();
});

//加载数据
function loadData(){
    var size=getGridPageSize('#myBody',1);
    jQuery("#mygrid").datagrid({
        url: zy.contextPath+'/${rootPagefloder}/${clasNameWithoutPrefix}/list',
        idField:'id',
        pageList: [size,size*10,size*20,size*30,size*40,size*50,size*100],
        pageSize: size,
        pagination:true,
        rownumbers:true,
        remoteSort: false,
        queryParams:{
            filter:{
            }
        },
        frozenColumns:[[
            {field:'CK',checkbox: true},
            {field:'id',title:'编号',hidden:'true'},
            {field:'opt',title: '操作',halign:'center', align:'left',width:"115px",formatter :
                function(value, row, index) {
                    var htm='';
                    htm+="<a href='javascript:show(\""+row.id+"\")' title='查看' class='small-btn small-eye'><em class='pm-eye'></em></a>&nbsp;&nbsp;";

                    htm+="<a href='javascript:edit(\""+row.id+"\")' title='修改' class='small-btn small-edit'><em class='pm-edit'></em></a>&nbsp;&nbsp;";

                    htm+="<a href='javascript:delOne(\""+row.id+"\")' title='删除' class='small-btn small-del'><em class='pm-del'></em></a>";

                    return htm;
                }
            },
            {field:'name',title:'模块名称',halign:'center',halign:'center',align:'left',width:"120px",sortable:true}
        ]],
        columns:[[
        <#list table.columns as column><#if !column.isPk()>
    {field:'${column.camelName}',title:'${column.columnAlias}',halign:'center',align:'center',width:"150px",sortable:true},
        </#if></#list>
        ]],
        onDblClickRow:function(index,row){
            show(row.id);
        }
    });
}

//添加
function add(){
    var src=zy.contextPath+"/${rootPagefloder}/${clasNameWithoutPrefix}/get";
    window.parent.addPanel("新增基本信息",src);
}

//添加修改
function save(){
    $("#myForm").form('submit',{
        ajax:true,
        onSubmit: function(){
            //防止重复提交
            var isValid = $(this).form('validate');
            if (isValid){
                //等待处理...
                showLoading();
            }
            return isValid;	// 返回false终止表单提交
        },
        success:function(result){
            hidenLoading();
            if(zy.success == result.code){
                parent.messages(result.message);
                saveState = true;
                back();
            }else {
                $.messager.alert('系统消息',result.message,'error');
                return;
            }
        }
    });
}

//返回
function back(){
    parent.backParentWindow(saveState,mytitle);
}

//查看
function show(id){
    var src = getEditSrc(id,false);
    parent.addPanel("基本信息",src);
}

//编辑
function edit(id){
    parent.addPanel("修改基本信息",getEditSrc(id,true));
}

//变更
function change(id){
    parent.addPanel("变更基本信息",getEditSrc(id,true));
    parent.closeTab(title);
}

function getEditSrc(id,edit){
    var src=zy.contextPath+"/${rootPagefloder}/${clasNameWithoutPrefix}/get?id="+id;
    if(edit){
        src+="&edit="+true;
    }
    return src;
}

//删除
function delOne(id){
    var ids = new Array();
    ids.push(id);
    $.messager.confirm('系统消息','确认要删除选中的数据吗？', function(ok){
        if (ok) {
            showLoading();
            $.post(zy.contextPath+'/${rootPagefloder}/${clasNameWithoutPrefix}/delete', {
                ids:ids.toString()
            }, function (result){
                //关闭等待
                hidenLoading();
                if(zy.success  == result.code){
                    parent.messages(result.message);
                    //重新加载
                    reloadPage();
                }else {
                    parent.messages(result.message);
                    return;
                }
            },'json');
        }
    });
}

