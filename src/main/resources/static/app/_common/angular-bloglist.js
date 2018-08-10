app.directive('myBloglist', [ '$http', function($http) {
	return {
		restrict : 'E',
		replace : true,
		templateUrl : 'app/_common/angular-bloglist.html',
		scope : {
			articles : "="
		}
	}
} ]);