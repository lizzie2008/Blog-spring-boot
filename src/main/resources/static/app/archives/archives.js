app.controller('archivesController', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    $scope.archiveid = $stateParams.id;
    $scope.archivename = $stateParams.name;

    // 全部归档
    $http({
        method: 'GET',
        url: '/archives'
    }).then(function successCallback(response) {
        $scope.archives = response.data;
    });

    // 归档博客分页
    var archivePara = $scope.archiveid == null ? "" : $scope.archiveid;
    $scope.paginationConf = {
        size: 5,
        showNum: 7,
        // 定义url格式,默认使用GET方式
        url: '/blogs?sort=createTime&order=desc&page={#page}&size={#size}&archive=' + archivePara,
        // 返回对象需要指明元素集合rows和元素总条数total，需指定访问属性名
        totalName: 'total',
        rowsName: 'rows',
        onLoaded: function (rows) {
            $scope.blogs = rows;
            $(() => {
                $('.article .article-summary').dotdotdot();
            })
        }
    };

}]);
