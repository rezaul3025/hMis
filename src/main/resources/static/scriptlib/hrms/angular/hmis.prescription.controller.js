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
			drugName:typeof drug.drugNameM !== 'undefined'?drug.drugNameM:'',
			drugRemark:typeof drug.drugRemarkM !== 'undefined'?drug.drugRemarkM:'',
			morningDose : typeof drug.morningDoseM !== 'undefined'?drug.morningDoseM:'',		
			afternoonDose :typeof drug.afternoonDoseM !== 'undefined'?drug.afternoonDoseM:'',	
			eveningDose : typeof drug.eveningDoseM !== 'undefined'?drug.eveningDoseM:'',	
			othersDose	: typeof drug.othersDoseM !== 'undefined'?drug.othersDoseM:''	
		});
	};
	
	$scope.removeDrugFromList = function(index){
		$scope.drugsArr.splice(index, 1);
	};
	
	
	$scope.savePrescription = function(prescription){
		var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
		alert(token);

		var data = {
				patientId : $rootScope.patientId,
				visitId : $rootScope.visitId,
				prescription :prescription,
				medications : $scope.drugsArr
					
		}
		
		$http({
            method: 'POST',
            url:  "/hmis/prescription/store",
            data: data,
            headers: {
            	   'X-CSRF-TOKEN': token,
            	   '_csrf_header':header
            	 }
            //params: {'chromosome':variant.chromosome,'gdna':variant.gdna,'cdna':variant.cdna,'dbsnp':variant.dbSnp,'hgmdaccession':variant.hgmdAccession,'type':variant.type,'pathogenicityeffect':variant.pathogenicityEffect,'pathogenicityscore':variant.pathogenicityScore,'coddingeffect':variant.codingEffect,'location':variant.location,'protein':variant.protein,'reference':variant.reference}
        }).
        success(function(data, status, headers, config) {
        	
        })
        .error(function(data, status, headers, config) {                
		})
		
	}
	
}]);