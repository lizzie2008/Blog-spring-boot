app.controller('homeController', ['$scope', '$stateParams', '$http', function ($scope, $stateParams, $http) {

    // 开启公告
    scroll_f();
    // 开启轮播
    $('#carousel').carousel();
    // 初始化弹出框
    $('.weixin').popover({
        trigger: 'hover',
        html: true,
        content: '<img src="//mysite.bj.bcebos.com/images/profile/weixin.jpg" alt="Photo">'
    });
    $('.gongzhonghao').popover({
        trigger: 'hover',
        html: true,
        content: '<img src="//mysite.bj.bcebos.com/images/profile/gongzhonghao.jpg" alt="Photo">'
    });
    $('.qq').popover({
        trigger: 'hover',
        html: true,
        content: '<img src="//mysite.bj.bcebos.com/images/profile/qq_me.png" alt="Photo">'
    });

    // 热门文章分页
    $scope.paginationConf = {
        size: 10,
        showNum: 7,
        // 定义url格式,默认使用GET方式
        url: '/api/blogs?sort=isSticky,createTime&order=desc,desc&page={#page}&size={#size}',
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

    // 阅读排行
    $http({
        method: 'GET',
        url: '/api/blogs?sort=readSize&order=desc&page=1&size=10'
    }).then(function successCallback(response) {
        $scope.readRankBolgs = response.data.rows;
    });

    // 评论排行
    $http({
        method: 'GET',
        url: '/api/blogs?sort=commentSize&order=desc&page=1&size=10'
    }).then(function successCallback(response) {
        $scope.commentRankBolgs = response.data.rows;
    });

    // 分类
    $http({
        method: 'GET',
        url: '/api/categorys'
    }).then(function successCallback(response) {
        $scope.categorys = response.data;
    });

    // 归档
    $http({
        method: 'GET',
        url: '/api/archives'
    }).then(function successCallback(response) {
        $scope.archives = response.data;
    });

    // 标签
    $http({
        method: 'GET',
        url: '/api/tags'
    }).then(function successCallback(response) {
        $scope.tags = response.data;
    });

}]);

// 内容折叠
function toggleContent(obj) {
    if ($(obj).html() == '﹀') {
        $(obj).html('︿');
    }
    else {
        $(obj).html('﹀');
    }
}

// 文字滚动
var scrollIndex = 0;
var Timer = null;
function scroll_f() {
    // 重置
    clearInterval(Timer);
    scrollIndex = 0;
    var ul = $(".scroll ul");
    var li = ul.children("li");
    var h = li.height();
    var index = 0;
    ul.css("height", h * li.length * 2);
    ul.html(ul.html() + ul.html());
    // 滚动运行
    function run() {
        // console.log("scrollIndex:"+scrollIndex+",top:"+ul.css("top"));
        if (scrollIndex >= li.length) {
            ul.css({
                top: 0
            });
            scrollIndex = 0;
        }
        scrollIndex++;
        ul.animate({
            top: -scrollIndex * h
        }, 1000);
    }
    Timer = setInterval(run, 3500);
}


