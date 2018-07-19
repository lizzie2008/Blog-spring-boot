app.controller('homeController', ['$scope', '$stateParams', function ($scope, $stateParams) {
	//text scroll
	var scrollIndex = 0;
	var Timer = null;
	function scroll_f() {
	    clearInterval(Timer);
	    var ul = $(".scroll ul");
	    var li = ul.children("li");
	    var h = li.height();
	    var index = 0;
	    ul.css("height", h * li.length * 2);
	    ul.html(ul.html() + ul.html());
	    function run() {
	        if (scrollIndex >= li.length) {
	            ul.css({
	                top: 0
	            });
	            scrollIndex = 1;
	            ul.animate({
	                top: -scrollIndex * h
	            }, 500);
	        } else {
	            scrollIndex++;
	            ul.animate({
	                top: -scrollIndex * h
	            }, 500);
	        }
	    }
	    Timer = setInterval(run, 3500);
	}
	// initialize
	$(() => {
	    new WOW().init();
	    scroll_f();
	})
}]);
