 $(document).ready(function() {
	 	
	 	// AUTOCOMPLETE DO CPF
	    $("#relatorio_cpf_id").autocomplete({
	        minLength: 3,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/relatorioPaciente/listarPacientesPorCpf", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.nomePaciente + " - " + item.cpfPaciente,
	                        // following property gets entered in the textbox
	                        value: item.nomePaciente + " - " + item.cpfPaciente + " - Id: " + item.idPaciente ,
	                        // following property is added for our own use
	                        tag_url: "http://" + window.location.host + "/paciente/buscaPaciente" + item.nomePaciente
	                    }
	                }));
	            });
	        }
	    });	 	
	 
	 
	 	// AUTOCOMPLETE DO NOME
	    $("#relatorio_nome_completo_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/relatorioPaciente/listarPacientes", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.nomePaciente,
	                        // following property gets entered in the textbox
	                        value: item.nomePaciente + " - Id: " + item.idPaciente ,
	                        // following property is added for our own use
	                        tag_url: "http://" + window.location.host + "/paciente/buscaPaciente" + item.nomePaciente
	                    }
	                }));
	            });
	        }
	    });
	    
	    
	    // Desabilitar campo CPF ao clicar no campo Nome
	    $("#relatorio_nome_completo_id").click(function(event) {
	    	$("#relatorio_nome_completo_id").prop("required", true);
	    	$("#relatorio_cpf_id").prop("disabled", true);
	        $("#relatorio_cpf_id").prop("required", false);
		});
	    
	    // Se o campo perder o foco, e não for inserida nenhuma informação,
	    // o campo cpf é habilitado novamente 
	    $("#relatorio_nome_completo_id").blur(function(event) {
	        if ($("#relatorio_nome_completo_id").val() == ""){
	        	$("#relatorio_cpf_id").prop("disabled", false);
	        }
		});
	    
	    // Desabilitar campo Nome ao clicar no campo CPF
	    $("#relatorio_cpf_id").click(function(event) {
	    	$("#relatorio_cpf_id").prop("required", true);
	    	$("#relatorio_nome_completo_id").prop("disabled", true);
	        $("#relatorio_nome_completo_id").prop("required", false);
		});
	    
	    // Se o campo perder o foco, e não for inserida nenhuma informação,
	    // o campo nome é habilitado novamente 
	    $("#relatorio_cpf_id").blur(function(event) {
	        if ($("#relatorio_cpf_id").val() == ""){
	        	$("#relatorio_nome_completo_id").prop("disabled", false);
	        }
		});
	    
	    
	    // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBuscaRelatorioPaciente").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var cpf =  $("#relatorio_cpf_id").val();
	    	var nome =  $("#relatorio_nome_completo_id").val();
	    	
	    	if (cpf.indexOf(':') < 0 && nome.indexOf(':') < 0) {
	    		alert('Os valores devem ser selecionados a partir da lista');
	    	} else {
	    		$(this).unbind('submit').submit()
	    	}
	    	
	    });
	});
