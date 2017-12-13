 $(document).ready(function() {
	 	
	 	var LOTE = "";
	 	var NOME_COMERCIAL = "";
	 	
	    // AUTOCOMPLETE DO NOME COMERCIAL
	    $("#relatorio_nome_comercial_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/relatorioPaciente/listarDistinctItensPorNomeComercial", request, function(result) {
	                response($.map(result, function(item) {
	                	return {
	                        // following property gets displayed in drop down
	                        label: item.medicamentoPO.nomeComercial + " - Dt validade: " + item.dtValidadeItemEstoque,
	                        // following property gets entered in the textbox
	                        value: item.medicamentoPO.nomeComercial,
	                        
	                        nomeComercial: item.medicamentoPO.nomeComercial, 
	                        
	                        lote: item.idItemEstoque
	                    }
	                }));
	            });
	        },
	        select: function( event, ui ) {
		    	LOTE = ui.item.lote;
		    	NOME_COMERCIAL = ui.item.nomeComercial;
	        }
	    });
	    
	    
	    // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaRelatorioMedicamento").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nomeComercial =  $("#relatorio_nome_comercial_id").val();
	    	
	    	if (NOME_COMERCIAL == nomeComercial || "" == nomeComercial){
				$("#lote_par_id").val(LOTE);
				$(this).unbind('submit').submit()
			} else {
				alert('Os valores devem ser selecionados a partir da lista');
			}
	    	
	    });
	    
	});
