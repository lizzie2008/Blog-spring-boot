app.directive('myPagination', [ '$http', function($http) {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : 'app/_common/angular-pagination.html',
		scope : {
			conf : "="
		},
		link : function(scope, element, attrs) {

			// 分页配置
			var conf = scope.conf;
			
			// 分页变量
			scope.p_current = 1;
			scope.p_pageNum = 0;

			// 初始加载一次数据
			reloadPage();

			// 绑定点击事件
			scope.pageClick = function(page) {
				// 点击相同的页数 不执行点击事件
				if (page == scope.p_current)
					return;
				scope.p_current = page;
				reloadPage();
			};

			// 上一页
			scope.p_pre = function() {
				if (scope.p_current == 1) {
					scope.p_current = scope.p_pageNum;
				} else {
					scope.p_current--;
				}
				reloadPage();
			}
			// 下一页
			scope.p_next = function() {
				if (scope.p_current == scope.p_pageNum) {
					scope.p_current = 1;
				} else {
					scope.p_current++;
				}
				reloadPage();
			}

			// 加载页面
			function reloadPage() {
				$http({
					method : 'GET',
					url : conf.url.replace('{#page}', scope.p_current).replace('{#size}', conf.size)
				}).then(function successCallback(response) {
					var total = response.data[conf.totalName];
					scope.p_pageNum = Math.ceil(total / conf.size);
					scope.p_pages = getPages(scope.p_current, scope.p_pageNum, conf.showNum);
					conf.onLoaded(response.data[conf.rowsName]);					
				}, function errorCallback(response) {
					console.log(response.data);
				})

			}

			// 返回页数范围（用来遍历）
			function getPages(current, pageNum, showNum) {
				var pages = [];
				if (pageNum <= showNum) {
					for (i = 1; i <= pageNum; i++) {
						pages.push(i);
					}
				} else {
					var separatorMin = Math.ceil(showNum / 2);
					var separatorMax = pageNum - separatorMin + 1;
					if (current <= separatorMin) {
						for (i = 1; i <= separatorMin + 1; i++) {
							pages.push(i);
						}
						pages.push('...');
						pages.push(pageNum);
					} else if (current >= separatorMax) {
						pages.push(1);
						pages.push('...');
						for (i = separatorMax - 1; i <= pageNum; i++) {
							pages.push(i);
						}
					} else {
						var leftNum = Math.floor((showNum - 4) / 2);
						var rightNum = showNum - 4 - leftNum - 1;
						pages.push(1);
						pages.push('...');
						for (i = current - leftNum; i <= current + rightNum; i++) {
							pages.push(i);
						}
						pages.push('...');
						pages.push(pageNum);
					}
				}
				return pages;
			}
		}
	}
} ]);