app.controller('homeController', [ '$scope', '$stateParams', '$http', function($scope, $stateParams, $http) {
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
	//开启公告和轮播
	scroll_f();
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
			$scope.articles = rows;
			

			$(() => {
				$('.article p').dotdotdot({
					height:90
				});
			})
		}
	};
	
	
	
} ]);
