//Angular相关配置
var app = angular.module('app', ['ui.router']);
// 路由配置
app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider.state('home', {
        // 首页
        url: '/',
        templateUrl: 'app/home/home.html'
    }).state('about', {
        // 首页
        url: '/about',
        templateUrl: 'app/about/about.html'
    });
}]);

