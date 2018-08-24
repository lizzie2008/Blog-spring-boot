app.directive('myComment', [ '$http', function($http) {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : 'app/_common/angular-comment.html',
		scope : {
			blog : "="
		},
		link : function($scope, element, attrs) {
			
			// 初始化
			$scope.$watch('blog', function(newValue) {
			    if (newValue !== undefined) {
			    	 transComments();
			    }
			});
			
			// 添加评论
			$scope.postComment={};
		    $scope.onSubmit = function () {
		        // 构建评论
		        var comment = {};
		        comment.content = $scope.postComment.content;
		        comment.nickName = $scope.postComment.nickName;
		        comment.webSite = $scope.postComment.webSite;
		        comment.createTime = new Date();
		        comment.browser = $.getBrowserInfo().browser;

		        var parentCommentId = $scope.postComment.parentCommentId;
		        if (parentCommentId) {
		            // 是回复评论
		        	comment.reply=true;
		            comment.parentCommentId = parentCommentId;
		        } else {
		            // 非回复评论
		        	comment.reply=false;
		        	comment.parentCommentId = null;
		        }
		        comment.id=$scope.addComment(comment);

		        // 重置form
		        $scope.postComment = {};
		    }
		    // 点击回复跳转评论
		    $scope.jumpToComment = function (id, nickName) {

		        // 设置当前
		        if (nickName) {
		            var textarea = $('form textarea');
		            $scope.postComment.parentCommentId = id;
		            textarea.val('@' + nickName + '：\n');
		            textarea.focus();
		        }
		    }
		    // 调用提交评论接口
		    $scope.addComment = function (comment) {

		        $http({
		            method: 'POST',
		            url: '/api/blogs/'+$scope.blog.id+'/comments',
		            data: comment
		        }).then(function successCallback(response) {
		        	comment.id=response.data;
		        	$scope.blog.comments.push(comment);
		        	transComments();
		        }, function errorCallback(response) {
		            console.log(response.data);
		        });
		    }
		    
		    // 将博客评论转换为层级显示
		    function transComments(){
		    	// 评论处理
		        var comments = $scope.blog.comments.sort(function(a, b){
		    		return a.id>b.id;
		    	});
		        
		        $.each(comments, function (i, item) {
		            if(!item.reply){
		            	item.subComments = comments.filter(s => s.reply == true && s.parentCommentId == item.id);
		            }
		            item.pattern = GeoPattern.generate(item.nickName).toDataUrl();
		        });
		        
//		        var rootComments = comments.filter(s => s.reply == false);
//		        $.each(rootComments, function (i, item) {
//		            item.subComments = comments.filter(s => s.reply == true && s.parentCommentId == item.id);
//		        });
		        $scope.comments = comments.filter(s => !s.reply);
		    }
		}
	}
} ]);