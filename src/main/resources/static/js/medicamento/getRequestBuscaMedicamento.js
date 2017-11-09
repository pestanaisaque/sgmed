 $(document).ready(function() {
	 	function log( message ) {
	      $( "<div>" ).text( message ).prependTo( "#log" );
	      $( "#log" ).scrollTop( 0 );
	    }
	 
	 	//attach autocomplete
	    $("#nome_comercial_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/buscaMedicamento/listarNomeComercial", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.nomeComercial,
	                        // following property gets entered in the textbox
	                        value: item.nomeComercial + " - Id: " + item.idMedicamento ,
	                        // following property is added for our own use
	                        tag_url: "http://" + window.location.host + "/medicamento/buscaMedicamento" + item.nomeComercial
	                    }
	                }));
	            });
	        }

	        
	        //define select handler
//	        select : function(event, ui) {
//	            if (ui.item) {
//	                event.preventDefault();
//	                $("#selected_tags span").append('<a href=" + ui.item.nomeComercial</a>');
//	                //$("#tagQuery").value = $("#tagQuery").defaultValue
//	                var defValue = $("#nome_comercial_id").prop('defaultValue');
//	                $("#nome_comercial_id").val(defValue);
//	                $("#nome_comercial_id").blur();
//	                return false;
//	            }
//	        }
	    });
	});
