 $(document).ready(function() {
	 	
	 	var CPF = "";
	 	var NOME = "";
	 	
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
	                        value: item.cpfPaciente,
	                        nome: item.nomePaciente,
	                        cpf: item.cpfPaciente
	                    }
	                }));
	            });
	        },
	        select: function( event, ui ) {
	    		CPF = ui.item.cpf;
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
	    		NOME = ui.item.nome;
	    		CPF = ui.item.cpf;
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
	    $("#formBuscaPaciente").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var cpf =  $("#cpf_id").val();
	    	var nome =  $("#nome_id").val();
	    	
	    			if (CPF == cpf && nome == ""){
	    				$("#cpf_par_id").val(CPF);
	    				$(this).unbind('submit').submit()
	    			} else if (NOME == nome && cpf == ""){
	    				$("#cpf_par_id").val(CPF);
	    				$(this).unbind('submit').submit()
	    			} else {
	    				alert('Os valores devem ser selecionados a partir da lista');
	    			}
	    });
	});
