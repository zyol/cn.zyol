<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLower?replace(rootPagefloder,'')>

'use strict';
angular.module('app')
    .controller('${rootPagefloder?cap_first}${clasNameWithoutPrefix?cap_first}ListController',[ '$rootScope', '$scope', '$http','$stateParams', '$state', function($rootScope, $scope, $http,$stateParams, $state) {
        var opts={
            loadUrl: '/${apibaseurl}/${rootPagefloder}${clasNameWithoutPrefix}/list',
            deleteUrl:'/${apibaseurl}/${rootPagefloder}${clasNameWithoutPrefix}/delete'
        };
        $.initPaged($scope,opts);
    } ]);