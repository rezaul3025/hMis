
//Select box for roles
$('#roles').select2({
	  placeholder: "Select a role(s)",
	  allowClear: true
});

//Select box for roles
$('#title').select2({
	  placeholder: "Select a title",
	  allowClear: true
});

$("#hpoterms1").select2({
    placeholder: 'Select hpo',
    multiple: true,
    allowClear: true,
    dropdownAutoWidth: true,
    quietMillis: 100,
    ajax: {
        url: '/hmis/rest/hpo-terms',
        data: function (term, page) {
        	//alert(page);
            var result = {
                selected: $("#hpoterms").val(),
                query: 'ab',
                page: 1,
                pageSize: 10
            };
            $.extend(result, {});
            alert(result);
            return result;
        },
        results: function (data, page) {
            var more = (page * pageSize) < data.count;
            return { results: data.results, more: more };
        }
    },
    formatSelection: function (item) {
        if (typeof item === "string") {
            return item;
        }
       
        return item;
    },
    formatResult: function (item) {
    	
        if (typeof item === "string") {
            return item;
        }
        return item;
    },
    id: function (item) {
        if (typeof item === "string") {
            return item;
        }
        return item;
    },
    initSelection: function (elem, callback) {
        // callback(ngModel.$viewValue);
        var value = elem.value;
      
        if (!value) {
            callback("");
        }
      //Some hpo term contain ","  thats way ";" is used as a separator
        else if (typeof value === "string" && value.indexOf(";") > -1) {
            callback(value.split(";"));
        }
        else if (typeof value === "string") {
            callback(value.split(","));
        }
        else {
            callback(elem.value);
        }
    }
}).on("select2-blur", function (elem) {
    //validation(ngModel, scope);
}).on("select2-close", function (elem) {
    var select2Data = $(this).data("select2");
    // Manually blur search input on close to let placeholder reappear
    // See https://github.com/ivaynberg/select2/issues/1545
    if (select2Data) {
        select2Data.search.blur();
    }
    //validation(ngModel, scope);
});


$("#hpoterms").select2({
	ajax: {
	      url: "/hmis/rest/hpo-terms",
	      dataType: 'json',
	      delay: 250,
	      data: function (params) {
	    	  params.page = params.page || 1;
	        return {
	          query: params.term, // search term
	          page: params.page,
	          pageSize:10
	        };
	      },
	      processResults: function (data, params) {
	        // parse the results into the format expected by Select2
	        // since we are using custom formatting functions we do not need to
	        // alter the remote JSON data, except to indicate that infinite
	        // scrolling can be used
	        params.page = params.page || 1;

	        // !IMPORTANT! your every item in data.items has to have an .id property - this is the actual value that Select2 uses
	        // Luckily the source data.items already have one
	        return {
	          results: data.results,
	          pagination: {
	            more: (params.page * 30) < data.count
	          }
	        };
	      },
	      cache: true
	    },
	    escapeMarkup: function (markup) {
	      return markup; // let our custom formatter work
	    },
	    minimumInputLength: 1,
	    templateResult: function(repo) {
	      if (repo.loading) return repo.text;
	      return repo.full_name;
	    },
	    templateSelection: function(repo) {
	      return repo.full_name || repo.text;
	    }
       
      });
   
      
 