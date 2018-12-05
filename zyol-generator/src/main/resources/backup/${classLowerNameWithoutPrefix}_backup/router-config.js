<#assign myParentDir="entity">
<#assign className = table.className>
<#assign classNameLower = className?lower_case>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#assign clasNameWithoutPrefix = classNameLower?replace(rootPagefloder,'')>

//hr 部门开始
    .state('${ngrout}.${rootPagefloder}', {
        url: '/${rootPagefloder}',
        template: '<div ui-view class="fade-in-right-big smooth"></div>'
    })
    .state('${ngrout}.${rootPagefloder}.${clasNameWithoutPrefix}', {
        url: '/${clasNameWithoutPrefix}',
        template: '<div ui-view class="fade-in-right-big smooth"></div>'
    })
    .state('${ngrout}.${rootPagefloder}.${clasNameWithoutPrefix}.list', {
        url: '/list',
        templateUrl: 'src/app/${rootPagefloder}/${clasNameWithoutPrefix}/list.html',
        controller: '${rootPagefloder?cap_first}${clasNameWithoutPrefix?cap_first}ListController',
        resolve: {
            deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                return uiLoad.load(['src/app/${rootPagefloder}/${clasNameWithoutPrefix}/listController.js']);
            }]
        }
    })
    .state('${ngrout}.${rootPagefloder}.${clasNameWithoutPrefix}.update', {
        url: '/update',
        params:{id:0},
        templateUrl: 'src/app/${rootPagefloder}/${clasNameWithoutPrefix}/update.html',
        controller: '${rootPagefloder?cap_first}${clasNameWithoutPrefix?cap_first}UpdateController',
        resolve: {
            deps: ['uiLoad', '$ocLazyLoad', function(uiLoad, $ocLazyLoad) {
                return uiLoad.load(['src/app/${rootPagefloder}/${clasNameWithoutPrefix}/updateController.js']);
            }]
        }
    });