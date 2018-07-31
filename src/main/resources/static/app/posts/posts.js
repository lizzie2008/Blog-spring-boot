app.controller('postsController', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    // 获取博客内容
    $http({
        method: 'GET',
        url: '/blog/' + $stateParams.id
    }).then(function successCallback(response) {

        // 博客
        $scope.blog = response.data;
        // 评论处理
        var comments = $scope.blog.comments;
        var rootComments = comments.filter(s => s.reply == false);
        $.each(rootComments, function (i, item) {
            item.subComments = comments.filter(s => s.reply == true && s.parentCommentId == item.id);
        });
        $scope.comments = rootComments;

        // 渲染markdown
        $(function () {
            testEditor = editormd.markdownToHTML("doc-content", {// 注意：这里是上面DIV的id
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tex: true, // 默认不解析
                flowChart: true, // 默认不解析
                sequenceDiagram: true, // 默认不解析
                codeFold: true
            });

            // 生成目录
            initDoc('#myScrollspy');

            // 滚动定位
            $('.doc-nav a').on('click', function () {
                var target = $(this).attr("href");
// $("html,body").animate({
// "scrollTop": $(target).offset().top
// }, 500)
                 window.scrollTo(0, $(target).offset().top);
                return false;
            });
        });
    }, function errorCallback(response) {
        console.log(response.data);
    });

    // 添加评论
    $scope.onSubmit = function () {
        // 构建评论
        var comment = {};
        comment.blog={};
        comment.blog.id=$scope.blog.id;
        comment.content = $scope.postComment.content;
        comment.nickName = $scope.postComment.nickName;
        comment.webSite = $scope.postComment.webSite;
        comment.createTime = new Date();
        comment.browser = getBrowserInfo().browser;

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
            url: '/blog/addComment',
            data: comment
        }).then(function successCallback(response) {
            return response.data;
        }, function errorCallback(response) {
            console.log(response.data);
        });
    }
}]);


// 获取浏览器信息
function getBrowserInfo() {
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var re = /(msie|firefox|chrome|opera|version).*?([\d.]+)/;
    var m = ua.match(re);
    Sys.browser = m[1].replace(/version/, "safari");
    Sys.ver = m[2];
    return Sys;
}

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
    var weight=prefix.length;
    
    if (list != null && list.length > 0) {
        content_ul += '<nav class="nav nav-pills flex-column">';
        for (var i = 0; i < list.length; i++) {
            var newPrefix = prefix + (i + 1) + '.';
            var text = newPrefix + $(list[i]).text();
            var href = $(list[i]).attr('id');
            content_ul += '<a class="nav-link ml-3 my-1" style="padding-left:'+(10+weight*6)+'px;" href="#' + href + '">' + text + '</a>'
            var subList = $(list[i]).nextUntil('h' + level, 'h' + (level + 1));
            content_ul += generateContent(subList, (level + 1), newPrefix);
            content_ul += '';
        }
        content_ul += '</nav >';
    }
    return content_ul;
}
