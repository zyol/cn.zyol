<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLower?replace(rootPagefloder,'')>
/**
 * Created by lfz on 2017/5/31.
 */
'use strict';
angular.module('app')
    .controller('${rootPagefloder?cap_first}${clasNameWithoutPrefix?cap_first}UpdateController',[ '$rootScope', '$scope', '$http','$stateParams', '$state', function($rootScope, $scope, $http,$stateParams, $state) {
        var id = $stateParams.id;
        var opts={
            id:id,
            getUrl:'/${apibaseurl}/${rootPagefloder}${clasNameWithoutPrefix}/get/'+id,
            updateUrl:"/${apibaseurl}/${rootPagefloder}${clasNameWithoutPrefix}/update",
            createUrl:"/${apibaseurl}/${rootPagefloder}${clasNameWithoutPrefix}/create",
            goback:"${ngrout}.${rootPagefloder}.${clasNameWithoutPrefix}.list"
        };
        $.initUpdateView($state,$scope,opts);
    } ]);