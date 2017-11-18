$( document ).ready(function() {
	
    $("#formEntradaMedicamentoEstoque").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostCad();
	});
    
    $("#formAlteracaoMedicamentoEstoque").submit(function(event) {
		
    	if ($("#formAlteracaoMedicamentoEstoque").data("changed")) {
        	// Prevent the form from submitting via the browser.
    		event.preventDefault();
    		ajaxPostAlt();
    	} else {
    		alert('Por favor altere ao menos um valor.');
    		event.preventDefault();
    	}
	});
    
    // Verifica se houve alteração em qualquer input
    $("#formAlteracaoMedicamentoEstoque :input").change(function() {
    	   $("#formAlteracaoMedicamentoEstoque").data("changed",true);
    });
    
    $('input[type=radio][name=generico]').on('change', function() {    	
    	$('#naoGenerico').attr('checked', false)
    });
    
    $('input[type=radio][name=naoGenerico]').on('change', function() {
    	$('#generico').attr('checked', false)
    });
    
    function ajaxPostCad(){
    	var isCheked = jQuery("input[name=generico]:checked").val();
    	
    	// PREPARE FORM DATA
    	var formData = {
    		medicamentoPO : {
    			idMedicamento : $("#medicamento_id").val()
    		},
    		
    		idItemEstoque : $("#item_estoque_id").val(),
    		qtdItemEmEstoque : $("#quantidade_a_inserir_id").val(),
    		dtValidadeItemEstoque : $("#data_validade_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/entradaMedicamentoEstoque/inserirMedicamentoEmEstoque",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Medicamento inserido em estoque com sucesso. Lote: " + result.data.idItemEstoque);
			    	window.location.href = "/estoque/consulta/consultaMedicamentoEstoque"
				}else if (result.status == 'NOK_ID') {
					alert("Já existe um item cadastrado com esse Lote.");
				} else {
					alert("Erro ao cadastrar item.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
    
    function ajaxPostAlt(){
    	var isCheked = jQuery("input[name=generico]:checked").val();
    	
    	// PREPARE FORM DATA
    	var formData = {
    		medicamentoPO : {
    			idMedicamento : $("#medicamento_id").val()
    		},
    		
    		idItemEstoque : $("#item_estoque_id").val(),
    		qtdItemEmEstoque : $("#quantidade_a_inserir_id").val(),
    		dtValidadeItemEstoque : $("#data_validade_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/alteracaoMedicamentoEstoque/alterarMedicamentoEmEstoque",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Lote alterado com sucesso. Lote: " + result.data.idItemEstoque);
			    	window.location.href = "/estoque/consulta/consultaMedicamentoEstoque"
				} else {
					alert("Erro ao alterar lote.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
    
})