app.controller('searchController', [ '$scope', '$stateParams', '$http', '$state', function($scope, $stateParams, $http, $state) {

//	$stateParams.searchText = "个人";
	// 构造查询并高亮
	if ($stateParams.searchText) {
		var query = {
			"_source" : [ "id", "title", "summary", "createTime" ],
			"query" : {
				"bool" : {
					"should" : [ {
						"match" : {
							"title" : $stateParams.searchText
						}
					}, {
						"match" : {
							"summary" : $stateParams.searchText
						}
					}, {
						"match" : {
							"content" : $stateParams.searchText
						}
					} ]
				}
			},
			"highlight" : {
				"pre_tags" : [ "<span class='text-danger'>" ],
				"post_tags" : [ "</span>" ],
				"fields" : {
					"title" : {},
					"content" : {}
				}
			}
		};

		// 调用ElasticSearch，进行全文检索
		$http({
			method : 'POST',
			url : '/blogs/search',
			data : JSON.stringify(query)
		}).then(function successCallback(response) {
			if (response.data) {
				console.log(response.data);
				var hits = response.data.hits.hits;
				// 搜索结果
				$scope.resultList = [];
				$.each(hits, function(index, hit) {
					var highlight = hit.highlight;
					var source = hit._source;

					var result = {};
					result.id = source.id;
					result.createTime = source.createTime;

					// 标题处理
					if (highlight && highlight.title) {
						result.title = highlight.title[0];
					} else {
						result.title = source.title;
					}
					// 快照处理
					if (highlight && highlight.summary) {
						result.snapshot = highlight.summary[0];
					} else if (highlight && highlight.content) {
						result.snapshot = highlight.content[0];
					} else {
						result.snapshot = source.summary;
					}

					$scope.resultList.push(result);
				});

				 $(() => {
				 $('.search-result .search-snapshot').dotdotdot();
				 })
			}
		}, function errorCallback(response) {
			console.log(response.data);
		});
	}

} ]);
