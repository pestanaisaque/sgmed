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
	    });
	    
	 // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaMedicamento").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nomeComercial =  $("#nome_comercial_id").val();
	    	
	    	if (nomeComercial.indexOf(':') < 0) {
	    		alert('O valor deve ser selecionado a partir da lista');
	    	} else {
	    		$(this).unbind('submit').submit()
	    	}
	    	
	    });
	});
