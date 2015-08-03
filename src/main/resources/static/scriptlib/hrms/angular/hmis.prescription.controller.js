//var cgNgapp = angular.module('hmisPrescription',[]);
cgNgapp.controller('PrescriptionController', ['$http', '$scope', '$rootScope', function PrescriptionController($http, $scope, $rootScope ){
	
	$scope.drugsArr = [];
	
	$scope.addDrugs = function(drug){
		//alert('test'+$scope.drugsArr.length);
		//var drug='drugNameD:'+drug.drugName+',drugRemarkD:'+drug.drugRemark+',morningDoseD:'+drug.morningDose+',afternoonDoseD:'+drug.afternoonDose+',eveningDoseD:'+drug.eveningDose+',othersDoseD:'+drug.othersDose;
		//alert('test'+drug);
		$scope.drugsArr.push({
			drugNameD:typeof drug.drugName !== 'undefined'?drug.drugName:'',
			drugRemarkD:typeof drug.drugRemark !== 'undefined'?drug.drugRemark:'',
			morningDoseD : typeof drug.morningDose !== 'undefined'?drug.morningDose:'',		
			afternoonDoseD :typeof drug.afternoonDose !== 'undefined'?drug.afternoonDose:'',	
			eveningDoseD : typeof drug.eveningDose !== 'undefined'?drug.eveningDose:'',	
			othersDoseD	: typeof drug.othersDose !== 'undefined'?drug.othersDose:''	
		});
	};
	
	$scope.removeDrugFromList = function(index){
		$scope.drugsArr.splice(index, 1);
	};
	
	
	
}]);