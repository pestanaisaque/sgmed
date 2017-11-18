 $(document).ready(function() {
	 	function log( message ) {
	      $( "<div>" ).text( message ).prependTo( "#log" );
	      $( "#log" ).scrollTop( 0 );
	    }
	 
	 	//attach autocomplete
	    $("#estoque_nome_comercial_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/buscaMedicamentoEstoque/listarItensPorNomeComercial", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.medicamentoPO.nomeComercial + " - Dt validade: " + item.dtValidadeItemEstoque,
	                        // following property gets entered in the textbox
	                        value: item.medicamentoPO.nomeComercial + " - Dt validade: " + item.dtValidadeItemEstoque + " - Id: " + item.idItemEstoque ,
	                        // following property is added for our own use
	                        tag_url: "http://" + window.location.host + "/estoque/buscaItemEstoque" + item.medicamentoPO.nomeComercial
	                    }
	                }));
	            });
	        }
	    });
	    
	  //attach autocomplete
	    $("#nome_comercial_entrada_estoque_id").autocomplete({
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
	    
	    
	    $("#lote_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/buscaMedicamentoEstoque/listarItensPorLote", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.idItemEstoque + " - Medicamento: " + item.medicamentoPO.nomeComercial,
	                        // following property gets entered in the textbox
	                        value: item.medicamentoPO.nomeComercial + " - Dt validade: " + item.dtValidadeItemEstoque + " - Id: " + item.idItemEstoque ,
	                        // following property is added for our own use
	                        tag_url: "http://" + window.location.host + "/estoque/buscaItemEstoque" + item.medicamentoPO.nomeComercial
	                    }
	                }));
	            });
	        }
	    });
	    
	 // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaAlteracaoEstoque").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var lote =  $("#lote_id").val();
	    	
	    	if (lote.indexOf(':') < 0) {
	    		alert('O valor deve ser selecionado a partir da lista');
	    	} else {
	    		$(this).unbind('submit').submit()
	    	}
	    	
	    });
	    
	 // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaMedicamentoEstoque").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nomeComercial =  $("#nome_comercial_entrada_estoque_id").val();
	    	
	    	if (nomeComercial.indexOf(':') < 0) {
	    		alert('O valor deve ser selecionado a partir da lista');
	    	} else {
	    		$(this).unbind('submit').submit()
	    	}
	    	
	    });
	    
	});
