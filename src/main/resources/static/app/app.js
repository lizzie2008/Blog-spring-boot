//Angular相关配置
var app = angular.module('app', ['ui.router']);
app.run(['$rootScope', '$transitions', '$state', function ($rootScope, $transitions, $state) {
    $transitions.onStart({}, function (trans) {
        var toStateName = trans.to().name;
        $rootScope.module=toStateName;
    })
}]);
// 路由配置
app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',function ($stateProvider, $urlRouterProvider,$httpProvider) {
	$urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
        // 首页
        url: '/',
        templateUrl: 'app/home/home.html'
    }).state('posts', {
        // 博客详情
        url: '/posts/:id',
        templateUrl: 'app/posts/posts.html'
    }).state('about', {
        // 关于
        url: '/about',
        templateUrl: 'app/about/about.html'
    });
    
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.post[header]=token ;
   
}]);

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$.ajaxSetup({
    beforeSend: function (xhr) {
        if(header && token ){
            xhr.setRequestHeader(header, token);
        }
    }}
);


// 启动初始化
$(() => {
	new WOW().init();
})
