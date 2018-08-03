app.controller('postsController', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    // 获取博客内容
    $http({
        method: 'GET',
        url: '/blogs/' + $stateParams.id
    }).then(function successCallback(response) {

        // 博客
        $scope.blog = response.data;

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
        });
    }, function errorCallback(response) {
        console.log(response.data);
    });
}]);
