 $(document).ready(function() {
	 	
	 	var LOTE = "";
	 	var NOME_COMERCIAL = "";
	 	
	 	var NOME_PACIENTE = "";
	 	var CPF_PACIENTE = "";
	 	
	 	function log( message ) {
	      $( "<div>" ).text( message ).prependTo( "#log" );
	      $( "#log" ).scrollTop( 0 );
	    }
	 
	 	// CONSULTA ESTOQUE
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
	    
	 // AUTOCOMPLETE DO NOME_PACIENTE
	    $("#saida_estoque_nome_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	        	$.getJSON("/api/buscaPaciente/listarPacientes", request, function(result) {
	        		response($.map(result, function(item) {
	        			return {
	                        // following property gets displayed in drop down
	                        label: item.nomePaciente + " - " + item.cpfPaciente,
	                        // following property gets entered in the textbox
	                        value: item.nomePaciente,
	                        nome: item.nomePaciente,
	                        cpf: item.cpfPaciente
	                    }
	                }));
	            });
	        },
	    	select: function( event, ui ) {
	    		NOME_PACIENTE = ui.item.nome;
	    		CPF_PACIENTE = ui.item.cpf;
	        }
	    });
	    
	    
	 // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaMedicamentoEstoque").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nomeComercial =  $("#estoque_nome_comercial_id").val();
	    	
	    	if (NOME_COMERCIAL == nomeComercial){
				$("#lote_par_id").val(LOTE);
				$(this).unbind('submit').submit()
			} else {
				alert('Os valores devem ser selecionados a partir da lista');
			}
	    	
	    });
	    
	    
	 // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formSaidaMedicamento").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nomePaciente =  $("#saida_estoque_nome_id").val();
	    	var nomeComercial =  $("#estoque_nome_comercial_id").val();
	    	
	    	if (NOME_PACIENTE == nomePaciente && NOME_COMERCIAL == nomeComercial){
				$("#cpf_par_id").val(CPF_PACIENTE);
				$("#lote_par_id").val(LOTE);
//				$(this).unbind('submit').submit()
				ajaxPostSaida();
			} else {
				alert('Os valores devem ser selecionados a partir da lista');
			}
	    });
	    
	   // SAIDA
	    function ajaxPostSaida(){
    		
			// PREPARE FORM DATA
	    	var formData = {
	    		
				itemEstoquePO : {
					idItemEstoque : $("#lote_par_id").val()
				},
	    			
	    		pacientePO : {
					cpfPaciente : $("#cpf_par_id").val()
				}	
	    	}
	    	
	    	// DO POST
	    	$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "/api/saidaMedicamentoEstoque/buscarParaSaidaEstoque",
				data : JSON.stringify(formData),
				dataType : 'json',
				success : function(result) {
					if(result.status == "OK"){
						window.location.href = "resultadoBuscaParaSaidaEstoque";
					} else if(result.status == "NOK_QTD"){
						alert("Medicamento indisponível em Lote.");
						window.location.href = "buscaParaSaidaEstoque";
					} else {
						alert("Erro ao recuperar valores do Lote ou Paciente.");
						window.location.href = "buscaParaSaidaEstoque";
					}
					console.log(result);
				},
				error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
			});
	    		
	    } 
	});
