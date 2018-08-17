/** **********************Angular*************************** */
// Angular相关配置
var app = angular.module('app', ['ui.router','ngSanitize']);
app.run(['$rootScope', '$transitions', '$state', function ($rootScope, $transitions, $state) {
    $transitions.onStart({}, function (trans) {
        var toStateName = trans.to().name;
        $rootScope.module = toStateName;
        switch(toStateName){
        case 'home':
        	$rootScope.moduleName="首页";
        	break;
        case 'posts':
        	$rootScope.moduleName="详情";
        	break;
        case 'categorys':
        	$rootScope.moduleName="分类";
        	break;
        case 'archives':
        	$rootScope.moduleName="归档";
        	break;
        case 'search':
        	$rootScope.moduleName="搜索";
        	break;
        case 'about':
        	$rootScope.moduleName="关于";
        	break;
        	default:
        	$rootScope.moduleName=null;
        }
        $rootScope.moduleName='Lancel0tの博客-'+$rootScope.moduleName;
        // 离开搜索页面，清空
        if(toStateName!='search'){
        	$('#search').val("");
        }
    })
}]);
// 定义一个 Service ，把它作为 Interceptors 的处理函数
app.factory('HttpInterceptor', ['$q', HttpInterceptor]);
function HttpInterceptor($q) {
  return {
    request: function(config){
      return config;
    },
    requestError: function(err){
      return $q.reject(err);
    },
    response: function(res){
      return res;
    },
    responseError: function(err){
      if(-1 === err.status) {
        // 远程服务器无响应
      } else if(500 === err.status) {
        // 处理各类自定义错误
      } else if(501 === err.status) {
        // ...
      }
      console.log(err);
      return $q.reject(err);
    }
  };
}
// 路由配置
app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function ($stateProvider, $urlRouterProvider, $httpProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
        // 首页
        url: '/',
        templateUrl: 'app/home/home.html'
    }).state('posts', {
        // 博客详情
        url: '/posts/:id',
        templateUrl: 'app/posts/posts.html'
    }).state('categorys', {
        // 博客分类
        url: '/categorys/',
        params:{id:null,name:null},
        templateUrl: 'app/categorys/categorys.html'
    }).state('archives', {
        // 博客归档
        url: '/archives/',
        params:{id:null,name:null},
        templateUrl: 'app/archives/archives.html'
    }).state('search', {
        // 全文搜索
        url: '/search/',
        params:{searchText:null},
        templateUrl: 'app/search/search.html'
    }).state('about', {
        // 关于
        url: '/about',
        templateUrl: 'app/about/about.html'
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.post[header] = token;
    
    $httpProvider.interceptors.push(HttpInterceptor);

}]);
// app控制器
app.controller('appController', ['$scope', '$state', function ($scope, $state) {

	// 全文搜索
	$scope.search=function(){
	// 跳转到搜索页面
		$state.go('search',{searchText:$scope.searchText});
	}
}]);
// 公共过滤器
app.filter('unsafe', ['$sce',function($sce) {
    return function(val) {
        return $sce.trustAsHtml(val);
    }; }]);
/** **********************jquery全局函数*************************** */
// 静态方法
$.extend({
    // 获取浏览器信息
    getBrowserInfo: function () {
        var Sys = {};
        var ua = navigator.userAgent.toLowerCase();
        var re = /(msie|firefox|chrome|opera|version).*?([\d.]+)/;
        var m = ua.match(re);
        Sys.browser = m[1].replace(/version/, "safari");
        Sys.ver = m[2];
        return Sys;
    }
});
// 成员函数
$.fn.extend({
    initDoc: function (selector) {
        // 找到最高一级的目录
        var hLevel = 1;
        for (; hLevel <= 6; hLevel++) {
            var list = $(selector + ' h' + hLevel);
            if (list.length > 0)
                break;
        }
        if (hLevel > 6)
            return;

        var firstList = $(selector + ' h' + hLevel);
        $(this).html(generateContent(firstList, hLevel, ''));
        
        // 滚动定位
        $('.doc-nav a').on('click', function () {
            var target = $(this).attr("href");
            window.scrollTo(0, $(target).offset().top);
            return false;
        });
    }
});
// 递归生成目录
function generateContent(list, level, prefix) {
    var content_ul = '';
    var weight = prefix.length;

    if (list != null && list.length > 0) {
        content_ul += '<nav class="nav nav-pills flex-column">';
        for (var i = 0; i < list.length; i++) {
            var newPrefix = prefix + (i + 1) + '.';
            var text = newPrefix + $(list[i]).text();
            var href = $(list[i]).attr('id');
            content_ul += '<a class="nav-link my-1" style="padding-left:' + (5 + weight * 6) + 'px;" href="#' + href + '">' + text + '</a>'
            var subList = $(list[i]).nextUntil('h' + level, 'h' + (level + 1));
            content_ul += generateContent(subList, (level + 1), newPrefix);
            content_ul += '';
        }
        content_ul += '</nav >';
    }
    return content_ul;
}
/** **********************启动初始化*************************** */
$(() => {
	// 初始化WOW动画
    new WOW().init();
    // 返回顶部开始
	$('.to-top').toTop();
})
