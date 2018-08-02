app.directive('myComment', [ '$http', function($http) {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : 'app/_common/angular-comment.html',
		scope : {
			relatedid : "=",
			comments : "="
		},
		link : function($scope, element, attrs) {
			
			$scope.postComment={};
			// 添加评论
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
		            var parent = $scope.comments.filter(s => s.id == parentCommentId)[0];
		            comment.id=$scope.addComment(comment);
		            parent.subComments.push(comment);
		        } else {
		            // 非回复评论
		        	comment.id=$scope.addComment(comment);
		            $scope.comments.push(comment);
		        }

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
		            url: '/blogs/'+$scope.relatedid+'/comments',
		            data: comment
		        }).then(function successCallback(response) {
		            return response.data;
		        }, function errorCallback(response) {
		            console.log(response.data);
		        });
		    }
		}
	}
} ]);