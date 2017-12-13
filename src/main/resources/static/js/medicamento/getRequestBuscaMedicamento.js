 $(document).ready(function() {
	 	
	 	var ID = "";
	 	var NOME = "";
	 	
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
	                        value: item.nomeComercial,
	                        
	                        nomeComercial: item.nomeComercial,
	                        idMedicamento: item.idMedicamento
	                    }
	                }));
	            });
	        },
		    select: function( event, ui ) {
		    	ID = ui.item.idMedicamento;
		    	NOME = ui.item.nomeComercial;
	        }
	    });
	    
	 // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaMedicamento").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nomeComercial =  $("#nome_comercial_id").val();
	    	
	    	if (NOME == nomeComercial ){
				$("#id_medicamento_par_id").val(ID);
				$(this).unbind('submit').submit()
			} else {
				alert('Os valores devem ser selecionados a partir da lista');
			}
	    	
	    });
	});
