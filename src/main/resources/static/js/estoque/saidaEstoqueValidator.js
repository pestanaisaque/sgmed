$( document ).ready(function() {
	var QTD_OK = false;
	
    // SAIDA
    $("#formSaidaMedicamento").submit(function(event) {
    	// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostSaida();
	});
    
    // RESULTADO SAIDA
    $("#formResultadoSaidaMedicamento").submit(function(event) {
    	// Prevent the form from submitting via the browser.
		event.preventDefault();
		if (QTD_OK){
			ajaxPostResultadoSaida();
			$("#saida_estoque_quantidade_saida_id").val("");
			$("#saida_estoque_quantidade_atual_id").val("");
		}
	});
    
    
    function ajaxPostSaida(){
    	var nomePaciente =  $("#saida_nome_paciente_id").val();
    	var nomeComercial =  $("#saida_nome_comercial_id").val();
    	
    	if (nomePaciente.indexOf(':') < 0 || nomeComercial.indexOf(':') < 0) {
    		alert('O valor deve ser selecionado a partir da lista');
    	} else {
    		
    		
	    		// PREPARE FORM DATA
	        	var formData = {
	        		
	    			itemEstoquePO : {
	    				medicamentoPO : {
	            			nomeComercial : $("#saida_nome_comercial_id").val()
	            		}
	    			},
	        			
	        		pacientePO : {
	    				nomePaciente : $("#saida_nome_paciente_id").val()
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
    }
    
    function ajaxPostResultadoSaida(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		
			itemEstoquePO : {
				
				idItemEstoque : $("#saida_estoque_lote_id").val()
				
			},
    			
    		pacientePO : {
				cpfPaciente : $("#saida_estoque_cpf_id").val()
			},
			
			qtdSaida : $("#saida_estoque_quantidade_saida_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/saidaMedicamentoEstoque/retirarMedicamentoEstoque",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Item retirado do estoque com sucesso.");
					window.location.href = "buscaParaSaidaEstoque";
				} else {
					alert("Erro ao recuperar valores do Lote ou Paciente.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    }
    
    // VALIDAR QTD ESTOQUE
    $('#saida_estoque_quantidade_saida_id').blur(function(){
		var qtdSaida = parseInt($("#saida_estoque_quantidade_saida_id").val());
		var qtdAtual = parseInt($("#saida_estoque_quantidade_atual_id").val());
		
		
		if (qtdSaida > qtdAtual){
			$("#saida_estoque_quantidade_saida_id").addClass("error");
			$("#div_error_saida").html("Quantidade de saída maior do que a disponível em estoque.");
			QTD_OK = false;
		} else if (qtdSaida == 0){
			$("#saida_estoque_quantidade_saida_id").addClass("error");
			$("#div_error_saida").html("Quantidade de saída deve ser maior que 0.");
			QTD_OK = false;
		} else {
			$("#saida_estoque_quantidade_saida_id").removeClass("error");
    		$("#div_error_saida").html("");
    		QTD_OK = true;
		}
		
	});
    
    
})