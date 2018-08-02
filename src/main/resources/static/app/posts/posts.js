app.controller('postsController', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    // 获取博客内容
    $http({
        method: 'GET',
        url: '/blogs/' + $stateParams.id
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
            testEditor = editormd.markdownToHTML("doc-content", {
                htmlDecode: "style,script,iframe",
                emoji: true,
                taskList: true,
                tex: true, // 默认不解析
                flowChart: true, // 默认不解析
                sequenceDiagram: true, // 默认不解析
                codeFold: true
            });

            // 生成目录
            $('#myScrollspy').initDoc('#doc-content');

            // 滚动定位
            $('.doc-nav a').on('click', function () {
                var target = $(this).attr("href");
                window.scrollTo(0, $(target).offset().top);
                return false;
            });
        });
    }, function errorCallback(response) {
        console.log(response.data);
    });
}]);
