app.controller('homeController',
		[
				'$scope',
				'$stateParams',
				'$http',
				function($scope, $stateParams, $http) {
					// 文字滚动
					scroll_f();

					// // 取最新发布文章
					// $http(
					// {
					// method : 'GET',
					// url : '/blog/list' + "?sort=" + "createTime"
					// + "&order=" + "desc" + "&page=" + "0"
					// + "&size=" + "10"
					// }).then(function successCallback(response) {
					// // 文章列表
					// $scope.articles = response.data.rows;
					// }, function errorCallback(response) {
					// console.log(response.data);
					// });

					// 配置
					$scope.count = 0;
					$scope.p_pernum = 1;
					// 变量
					$scope.p_current = 1;
					$scope.p_all_page = 0;
					$scope.pages = [];
					// 最大显示页数
					$scope.p_show_page = 7;

					// 获取数据
					var _get = function(page, size) {
						$http.get(
								"/blog/list?sort=createTime&order=desc"
										+ "&page=" + (page - 1) + "&size="
										+ size).then(
								function successCallback(response) {
									if (response.data != null) {
										$scope.count = response.data.total;
										$scope.list = response.data.rows;
										$scope.p_current = page;
										$scope.p_all_page = Math
												.ceil($scope.count
														/ $scope.p_pernum);
										reloadPno();
									}
								});
					}
					// 初始化页码
					var reloadPno = function() {
						$scope.pages = calculateIndexes($scope.p_current,
								$scope.p_all_page, $scope.p_show_page);
					};
					// 分页算法
					var calculateIndexes = function(current, all_page,
							show_page) {
						var indexes = [];
						if (all_page <= show_page) {
							for (i = 1; i <= all_page; i++) {
								indexes.push(i);
							}
						} else {
							var separatorMin = Math.ceil(show_page / 2);
							var separatorMax = all_page - separatorMin + 1;
							if (current <= separatorMin) {
								for (i = 1; i <= separatorMin + 1; i++) {
									indexes.push(i);
								}
								indexes.push('...');
								indexes.push(all_page);
							} else if (current >= separatorMax) {
								indexes.push(1);
								indexes.push('...');
								for (i = separatorMax - 1; i <= all_page; i++) {
									indexes.push(i);
								}
							} else {
								var leftNum = Math.floor((show_page - 4) / 2);
								var rightNum = show_page - 4 - leftNum - 1;
								indexes.push(1);
								indexes.push('...');
								for (i = current - leftNum; i <= current
										+ rightNum; i++) {
									indexes.push(i);
								}
								indexes.push('...');
								indexes.push(all_page);
							}
						}
						return indexes;
					};

					// 初始化第一页
					_get($scope.p_current, $scope.p_pernum);

					// 上一页
					$scope.p_pre = function() {
						if ($scope.p_current == 1) {
							$scope.p_current = $scope.p_all_page;
						} else {
							$scope.p_current--;
						}
						_get($scope.p_current, $scope.p_pernum);
					}
					// 下一页
					$scope.p_next = function() {
						if ($scope.p_current == $scope.p_all_page) {
							$scope.p_current = 1;
						} else {
							$scope.p_current++;
						}
						_get($scope.p_current, $scope.p_pernum);
					}
					// 加载某一页
					$scope.load_page = function(page) {
						_get(page, $scope.p_pernum);
					};
				} ]);

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
			}, 500);
		} else {
			scrollIndex++;
			ul.animate({
				top : -scrollIndex * h
			}, 500);
		}
	}
	Timer = setInterval(run, 3500);
}
