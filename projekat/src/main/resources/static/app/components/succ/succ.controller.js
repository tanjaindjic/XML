(function () {
    'use strict';

    angular
		.module('app')
		.controller('succController', succController);

    succController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window', '$timeout'];
    function succController($location, $scope, $rootScope,$http, $cookies, $window,  $timeout) {
    	var succ = this;
  
    	$scope.seconds = 5000;
    	$scope.message = "";
        //timer callback
        var timer = function() {
            if( $scope.seconds > 0 ) {
                $scope.seconds -= 1000;
                $timeout(timer, 1000);
            }else $location.path("/login")
        }
       
      
        
        
    	var init = function(){
    		var absUrl = $location.absUrl();
    		var lastPart = absUrl.split("/").pop();
    		if(lastPart==1)
    			$scope.message = "Almost there! Please finish your registration via link we sent on your email.";
    		if(lastPart==2){
    			$scope.message = "Your account is now active!"
    			document.getElementById("timer").style.display = "block";
    			$timeout(timer, 1000);  
    		}
    		if(lastPart==3)
    			$scope.message = "Your request is being processed by our administrators. Certificate will be sent to your email address after approval.";

    		if(lastPart==4){
                $scope.message = "Your account is now active!"
                document.getElementById("timer2").style.display = "block";
                $timeout(timer, 1000);
                		}
    		if(lastPart==5){
                $scope.message = "Administrator accounts are enabled only in Admin Section."
                document.getElementById("timer3").style.display = "block";
                document.getElementById("success").style.display = "none";
    		}
    		if(lastPart==6){
                $scope.message = "We sent you an email with password reset details."
    		}
        };
        init();

        
    }

})();