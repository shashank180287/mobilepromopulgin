$(document).ready(function() {

	$.getJSON("/service/", function(result) {
    	var optionsValues = '<select>';
        $.each(result, function(value) {
    		optionsValues += '<option value="' + value.code + '">' + value.Name + '</option>';
        });
    	optionsValues += '</select>';
    	var options = $('#ser_type_dd');
    	options.replaceWith(optionsValues);
    });

	
	$('#ser_type_dd').change(function(){
		var ser_type_dd_value = $(this).options[$('#ser_type_dd').selectedIndex];
		$.ajax({
		  type: 'GET',
		  url : '/',
		  data : 'value='+ser_type_dd_value,
		  dataType : 'json',
		  success: function(data){
		    $('#sub_cat_dd').html('');
		    $(data).each(function(value){
		      $('#sub_cat_dd').append('<option value="'+value.code+'">'+value.name+'</option>');
		    }); // you can change this loop as per your response data type
		  }
		});
	});

});