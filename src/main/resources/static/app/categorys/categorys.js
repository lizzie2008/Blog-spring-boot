app.controller('categorysController', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    $scope.categoryid = $stateParams.id;
    $scope.categoryname = $stateParams.name;

    // 全部分类
    $http({
        method: 'GET',
        url: '/api/categorys'
    }).then(function successCallback(response) {
        $scope.categorys = response.data;
    });

    // 分类博客分页
    var categoryPara = $scope.categoryid == null ? "" : $scope.categoryid;
    $scope.paginationConf = {
        size: 10,
        showNum: 7,
        // 定义url格式,默认使用GET方式
        url: '/api/blogs?sort=createTime&order=desc&page={#page}&size={#size}&category=' + categoryPara,
        // 返回对象需要指明元素集合rows和元素总条数total，需指定访问属性名
        totalName: 'total',
        rowsName: 'rows',
        onLoaded: function (rows) {
            $scope.blogs = rows;
            $(() => {
                $('.blog-summary').dotdotdot({
                	 truncate: "letter"
                });
            })
        }
    };

}]);
