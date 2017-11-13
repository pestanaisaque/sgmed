 $(document).ready(function() {
	 	
	 	// AUTOCOMPLETE DO CPF
	    $("#cpf_id").autocomplete({
	        minLength: 3,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/buscaPaciente/listarPacientesPorCpf", request, function(result) {
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
	    $("#nome_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/buscaPaciente/listarPacientes", request, function(result) {
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
	    $("#nome_id").click(function(event) {
	    	$("#nome_id").prop("required", true);
	    	$("#cpf_id").prop("disabled", true);
	        $("#cpf_id").prop("required", false);
		});
	    
	    // Se o campo perder o foco, e não for inserida nenhuma informação,
	    // o campo cpf é habilitado novamente 
	    $("#nome_id").blur(function(event) {
	        if ($("#nome_id").val() == ""){
	        	$("#cpf_id").prop("disabled", false);
	        }
		});
	    
	    // Desabilitar campo Nome ao clicar no campo CPF
	    $("#cpf_id").click(function(event) {
	    	$("#cpf_id").prop("required", true);
	    	$("#nome_id").prop("disabled", true);
	        $("#nome_id").prop("required", false);
		});
	    
	    // Se o campo perder o foco, e não for inserida nenhuma informação,
	    // o campo nome é habilitado novamente 
	    $("#cpf_id").blur(function(event) {
	        if ($("#cpf_id").val() == ""){
	        	$("#nome_id").prop("disabled", false);
	        }
		});
	    
	    
	    // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formBusca").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var cpf =  $("#cpf_id").val();
	    	var nome =  $("#nome_id").val();
	    	
	    	if (cpf.indexOf(':') < 0 && nome.indexOf(':') < 0) {
	    		alert('Os valores devem ser selecionados a partir da lista');
	    	} else {
	    		$(this).unbind('submit').submit()
	    	}
	    	
	    });
	});
