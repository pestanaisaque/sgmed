$( document ).ready(function() {
	
	// FORMULARIO DE CADASTRO
    $("#formCadastroMedicamento").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostCad();
	});
    
    // FORMULARIO DE ALTERACAO
    $("#formAlteracaoMedicamento").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostAlt();
	});
    
    // FORMULARIO DE EXCLUSAO
    $("#formExclusaoMedicamento").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostExcl();
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
    		idMedicamento : $("#medicamento_id").val(),
    		nomeComercial : $("#nome_comercial").val(),
    		principioAtivo : $("#principio_ativo_id").val(),
    		indicacoes : 	$("#indicacoes_id").val(),
    		contraIndicacoes : $("#contra_indicacoes_id").val(),
    		generico : (isCheked) ? 1 : 2
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/cadastroMedicamento/cadastrarMedicamento",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Medicamento cadastrado com sucesso. ID: " + result.data.idMedicamento);
					
					// Reset FormData after Posting
			    	resetDataCad();
			    	window.location.href = "buscaMedicamento"
				}else{
					alert("Já existe um medicamento cadastrado com esse ID.");
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
    		idMedicamento : $("#idMedicamento").val(),
    		nomeComercial : $("#nomeComercial").val(),
    		principioAtivo : $("#principioAtivo").val(),
    		indicacoes : 	$("#indicacoes").val(),
    		contraIndicacoes : $("#contraIndicacoes").val(),
    		generico : (isCheked) ? 1 : 2
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/alteracaoMedicamento/alterarMedicamento",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Medicamento alterado com sucesso. ID: " + result.data.idMedicamento);
					
					// Reset FormData after Posting
			    	resetDataAlt();
			    	window.location.href = "buscaAlteracaoMedicamento"
				}else{
					alert("Erro ao alterar medicamento.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
    
    function ajaxPostExcl(){
    	var isCheked = jQuery("input[name=generico]:checked").val();
    	
    	// PREPARE FORM DATA
    	var formData = {
    		idMedicamento : $("#idMedicamento").val(),
    		nomeComercial : $("#nomeComercial").val(),
    		principioAtivo : $("#principioAtivo").val(),
    		indicacoes : 	$("#indicacoes").val(),
    		contraIndicacoes : $("#contraIndicacoes").val(),
    		generico : (isCheked) ? 1 : 2
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/exclusaoMedicamento/excluirMedicamento",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Medicamento excluído com sucesso. ID: " + result.data.idMedicamento);
					
					// Reset FormData after Posting
			    	resetDataExcl();
			    	window.location.href = "buscaExclusaoMedicamento"
				}else{
					alert("Erro ao excluir medicamento.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
    
    function resetDataCad(){
    	
    }
    
    function resetDataAlt(){
    	$("#idMedicamento").val("");
		$("#nomeComercial").val("");
		$("#principioAtivo").val("");
		$("#indicacoes").val("");
		$("#contraIndicacoes").val("");
		$('#generico').attr('checked', false)
		$('#naoGenerico').attr('checked', false)
    }
    
    
    function resetDataExcl(){
    	
    }
    
    function getModal(){
    	$("#modal_sucesso").modal();
    }
    
})