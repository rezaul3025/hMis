
var cgNgapp = angular.module('hrmsApp',[]);
      cgNgapp.directive('cgInput', function(){
    	  
    	  return{
    		  restrict: 'AE',
    		  replace:true,
    		  template:'<input type="text" class="form-control" />'
    	  }
    	  
      });
      
      cgNgapp.directive('cgPassword', function(){
    	  
    	  return{
    		  restrict: 'AE',
    		  replace:true,
    		  template:'<input type="password" class="form-control" />'
    	  }
    	  
      });
      
      cgNgapp.directive('cgTextArea', function(){
    	  
    	  return{
    		  restrict: 'AE',
    		  replace:true,
    		  template:'<textarea class="form-control" rows="3"></textarea>'
    	  }
    	  
      });
