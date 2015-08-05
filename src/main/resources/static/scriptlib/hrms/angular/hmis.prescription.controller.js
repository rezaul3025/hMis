//var cgNgapp = angular.module('hmisPrescription',[]);
cgNgapp.controller('PrescriptionController', ['$http', '$scope', '$rootScope', function PrescriptionController($http, $scope, $rootScope ){
	
	$scope.drugsArr = [];
	
	this.init = function(patientId, visitId){
		 $rootScope.patientId = patientId;
		 $rootScope.visitId = visitId;
		 
		 //alert($rootScope.patientId);
	 };
	 
	$scope.addDrugs = function(drug){
		//alert('test'+$scope.drugsArr.length);
		//var drug='drugNameD:'+drug.drugName+',drugRemarkD:'+drug.drugRemark+',morningDoseD:'+drug.morningDose+',afternoonDoseD:'+drug.afternoonDose+',eveningDoseD:'+drug.eveningDose+',othersDoseD:'+drug.othersDose;
		
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
	
	
	$scope.savePrescription = function(prescription){
		alert($rootScope.patientId);
		var data = {
				patientId : $rootScope.patientId,
				visitId : $rootScope.visitId,
				prescription :prescription,
				medications : $scope.drugsArr
					
		}
		
		$http({
            method: 'POST',
            url:  "/hmis/prescription/store",
            data: data
            //params: {'chromosome':variant.chromosome,'gdna':variant.gdna,'cdna':variant.cdna,'dbsnp':variant.dbSnp,'hgmdaccession':variant.hgmdAccession,'type':variant.type,'pathogenicityeffect':variant.pathogenicityEffect,'pathogenicityscore':variant.pathogenicityScore,'coddingeffect':variant.codingEffect,'location':variant.location,'protein':variant.protein,'reference':variant.reference}
        }).
        success(function(data, status, headers, config) {
        	
        })
        .error(function(data, status, headers, config) {                
		})
		
	}
	
}]);