app.controller('tagsController', ['$scope', '$stateParams', '$http', '$state',function ($scope, $stateParams, $http,$state) {

    $scope.tagid = $stateParams.id;
    $scope.tagname = $stateParams.name;

    // 全部分类
    $http({
        method: 'GET',
        url: '/api/tags'
    }).then(function successCallback(response) {
        $scope.tags = response.data;
        
        // 根据标签数量动态设置高度
        var weight=$scope.tags.length;
        $("#tagCloud").css('height',weight*30);
        
        // 构造标签云
        var word_array = [];
        $.each($scope.tags,function(index,tag){
        	word_array.push({text:tag.name,weight:tag.blogSize,html:{"data-id":tag.id,"data-name":tag.name}});
        });
        
        $("#tagCloud").jQCloud(word_array,{afterCloudRender:function(){
        	// 标签云渲染后，初始化标签跳转
        	$("#tagCloud span").on('click',function(){
        		 var id=$(this).data("id");
        		 var name=$(this).data("name");
        		$state.go("tags",{id:id,name:name});
        	})
        }});
    });

    // 分类博客分页
    var tagPara = $scope.tagid == null ? "" : $scope.tagid;
    $scope.paginationConf = {
        size: 10,
        showNum: 7,
        // 定义url格式,默认使用GET方式
        url: '/api/blogs?sort=createTime&order=desc&page={#page}&size={#size}&tag=' + tagPara,
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
