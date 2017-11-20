$( document ).ready(function() {
	
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
		ajaxPostResultadoSaida();
	});
    
    
    function ajaxPostSaida(){
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
					window.location.href = "resultadoBuscaParaSaidaEstoque";
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
		var qtdSaida = $("#saida_estoque_quantidade_saida_id").val();
		var qtdAtual = $("#saida_estoque_quantidade_atual_id").val();
		
		
		if (qtdSaida > qtdAtual){
			$("#saida_estoque_quantidade_saida_id").addClass("error");
			$("#div_error_saida").html("Quantidade de saída maior do que a disponível em estoque.");
			$("#saida_estoque_quantidade_saida_id").val("");
		} else if (qtdSaida == 0){
			$("#saida_estoque_quantidade_saida_id").addClass("error");
			$("#div_error_saida").html("Quantidade de saída deve ser maior que 0.");
			$("#saida_estoque_quantidade_saida_id").val("");
		} else {
			$("#saida_estoque_quantidade_saida_id").removeClass("error");
    		$("#div_error_saida").html("");
		}
		
	});
    
    
})