app.controller('postsController', [ '$scope', '$stateParams', '$http', function($scope, $stateParams, $http) {

	// 获取博客内容
	$http({
		method : 'GET',
		url : '/blog/' + $stateParams.id
	}).then(function successCallback(response) {
		$scope.blog = response.data;

		// 渲染markdown
		$(function() {
			testEditor = editormd.markdownToHTML("doc-content", {// 注意：这里是上面DIV的id
				htmlDecode : "style,script,iframe",
				emoji : true,
				taskList : true,
				tex : true, // 默认不解析
				flowChart : true, // 默认不解析
				sequenceDiagram : true, // 默认不解析
				codeFold : true
			});

			initDoc('#myScrollspy');

			// 滚动定位
			$('.doc-nav a').on('click', function() {
				var target = $(this).attr("href");
				$("html,body").animate({
					"scrollTop" : $(target).offset().top
				}, 500)
				// window.scrollTo(0, $(target).offset().top);
				return false;
			});

			var arr = [ {
				id : 1,
				img : "./images/img.jpg",
				replyName : "帅大叔",
				beReplyName : "匿名",
				content : "同学聚会，看到当年追我的屌丝开着宝马车带着他老婆来了，他老婆是我隔壁宿舍的同班同学，心里后悔极了。",
				time : "2017-10-17 11:42:53",
				address : "深圳",
				osname : "",
				browse : "谷歌",
				replyBody : []
			}, {
				id : 2,
				img : "./images/img.jpg",
				replyName : "匿名",
				beReplyName : "",
				content : "到菜市场买菜，看到一个孩子在看摊，我问：“一只鸡多少钱？” 那孩子回答：“23。” 我又问：“两只鸡多少钱？” 孩子愣了一下，一时间没算过来，急中生智大吼一声：“一次只能买一只！”",
				time : "2017-10-17 11:42:53",
				address : "深圳",
				osname : "",
				browse : "谷歌",
				replyBody : [ {
					id : 3,
					img : "",
					replyName : "帅大叔",
					beReplyName : "匿名",
					content : "来啊，我们一起吃鸡",
					time : "2017-10-17 11:42:53",
					address : "",
					osname : "",
					browse : "谷歌"
				} ]
			}, {
				id : 3,
				img : "./images/img.jpg",
				replyName : "帅大叔",
				beReplyName : "匿名",
				content : "同学聚会，看到当年追我的屌丝开着宝马车带着他老婆来了，他老婆是我隔壁宿舍的同班同学，心里后悔极了。",
				time : "2017-10-17 11:42:53",
				address : "深圳",
				osname : "win10",
				browse : "谷歌",
				replyBody : []
			} ];

//			$(".comment-list").addCommentList({
//				data : arr,
//				add : ""
//			});
//			$("#comment").click(function() {
//				var obj = new Object();
//				obj.img = "./images/img.jpg";
//				obj.replyName = "懒人";
//				obj.content = $("#content").val();
//				obj.browse = "深圳";
//				obj.osname = "win10";
//				obj.replyBody = "";
//				$(".comment-list").addCommentList({
//					data : [],
//					add : obj
//				});
//			});

		});
	}, function errorCallback(response) {
		console.log(response.data);
	})
} ]);

// 构建文档目录
function initDoc(selector) {
	// 找到最高一级的目录
	var hLevel = 1;
	for (; hLevel <= 6; hLevel++) {
		var list = $('#doc-content h' + hLevel);
		if (list.length > 0)
			break;
	}
	if (hLevel > 6)
		return;

	var firstList = $('#doc-content h' + hLevel);
	$(selector).html(generateContent(firstList, hLevel, ''));
}

// 生成目录内容
function generateContent(list, level, prefix) {
	var content_ul = '';
	if (list != null && list.length > 0) {
		content_ul += '<ul>';
		for (var i = 0; i < list.length; i++) {
			var newPrefix = prefix + (i + 1) + '.';
			var text = newPrefix + $(list[i]).text();
			var href = $(list[i]).attr('id');
			content_ul += '<li><a href="#' + href + '">' + text + '</a>'
			var subList = $(list[i]).nextUntil('h' + level, 'h' + (level + 1));
			content_ul += generateContent(subList, (level + 1), newPrefix);
			content_ul += '</li>';
		}
		content_ul += '</ul>';
	}
	return content_ul;
}
