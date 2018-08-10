app.controller('homeController', [ '$scope', '$stateParams', '$http', function($scope, $stateParams, $http) {

	// 开启公告
	scroll_f();
	// 开启轮播
    $('#carousel').carousel();

	// 热门文章分页
	$scope.paginationConf = {
		size : 5,
		showNum : 7,
		// 定义url格式,默认使用GET方式
		url : '/blogs?sort=createTime&order=desc&page={#page}&size={#size}',
		// 返回对象需要指明元素集合rows和元素总条数total，需指定访问属性名
		totalName : 'total',
		rowsName : 'rows',
		onLoaded : function(rows) {
			$scope.blogs = rows;			
			$(() => {
				$('.article .article-summary').dotdotdot();
			})
		}
	};
	
	// 评论排行
	 $http({
	        method: 'GET',
	        url: '/blogs?sort=commentSize&order=desc&page=1&size=9'
	    }).then(function successCallback(response) {
	        $scope.commentRankBolgs = response.data.rows;  
	 });
	 
	// 分类
	 $http({
	        method: 'GET',
	        url: '/categorys'
	    }).then(function successCallback(response) {
	       $scope.categorys=response.data;
	 });
	
	// 归档
	 $http({
	        method: 'GET',
	        url: '/archives'
	    }).then(function successCallback(response) {
	       $scope.archives=response.data;
	 });
	 
} ]);

// 内容折叠
function toggleContent(obj) {
    if ($(obj).html() == '﹀') {
        $(obj).html('︿');
    }
    else {
        $(obj).html('﹀');
    }
}

// 文字滚动
var scrollIndex = 0;
var Timer = null;
function scroll_f() {
	clearInterval(Timer);
	var ul = $(".scroll ul");
	var li = ul.children("li");
	var h = li.height();
	var index = 0;
	ul.css("height", h * li.length * 2);
	ul.html(ul.html() + ul.html());
	function run() {
		if (scrollIndex >= li.length) {
			ul.css({
				top : 0
			});
			scrollIndex = 1;
			ul.animate({
				top : -scrollIndex * h
			}, 1000);
		} else {
			scrollIndex++;
			ul.animate({
				top : -scrollIndex * h
			}, 1000);
		}
	}
	Timer = setInterval(run, 3500);
}
