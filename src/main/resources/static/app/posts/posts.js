app.controller('postsController', [ '$scope', '$stateParams', '$http', '$location', function($scope, $stateParams, $http, $location) {

	$scope.absUrl = $location.absUrl();
	// 初始化弹出框
	$('.share-weixin').popover({
		trigger : 'manual',
		html : true,
		content : '<div id="qrcode"></div>'
	}).on("mouseenter", function() {
		$(this).popover("show");
		// 生成二维码
		$("#qrcode").qrcode({
			width : 150,
			height : 150,
			render : 'canvas',
			text : $scope.absUrl
		});
	}).on("mouseleave", function() {
		$(this).popover("hide");
	});

	// 获取博客内容
	$http({
		method : 'GET',
		url : '/api/blogs/' + $stateParams.id
	}).then(function successCallback(response) {

		// 博客
		$scope.blog = response.data;
		// 是否点赞过
		$scope.hasLike = $.cookie($scope.blog.id);

		// 渲染markdown
		$(function() {
			testEditor = editormd.markdownToHTML("doc-content", {
				htmlDecode : "style,script,iframe",
				emoji : true,
				taskList : true,
				tex : true, // 默认不解析
				flowChart : true, // 默认不解析
				sequenceDiagram : true, // 默认不解析
				codeFold : true
			});

			// 生成目录
			$('#myScrollspy').initDoc('#doc-content');
			$scope.isLoaded = true;
			$scope.$apply();
		});
	});

	// 复制文档时提示
	document.body.oncopy = function() {
		layer.msg('复制成功,若要转载请务必保留原文链接', function() {
		});
	};

	$scope.like = function(event) {
		if ($scope.hasLike) {
			layer.msg('您已点过赞了！');
			return;
		}

		// 调用接口
		$http({
			method : 'POST',
			url : '/api/blogs/' + $scope.blog.id + '/like',
		});
		$scope.hasLike = true;
		$scope.blog.likeSize++;
		$(event.target).tipsBox({
			obj : $(event.target),
			str : "+1"
		});
		$.cookie($scope.blog.id, "true", {
			expires : 365
		});
	};

	// 打赏
	$scope.showReward=function(){
		$.dialog({
			title: '',
		    content: ''+
			'<div class="reward">'+
				'<h6 class="reward-title">觉得文章有用就打赏一下文章作者</h6>'+
				'<div class="pull-left">'+
					'<h6 class="reward-subtitle">支付宝扫一扫打赏</h6>'+
					'<img src="https://mysite.bj.bcebos.com/images/profile/alipay.jpg" alt="Photo">'+
				'</div>'+
				'<div class="pull-right">'+
					'<h6 class="text-center reward-subtitle">微信扫一扫打赏</h6>'+
					'<img src="https://mysite.bj.bcebos.com/images/profile/wechatpay.jpg" alt="Photo">'+
				'</div>'+
			'</div>'
		});
	};
} ]);
