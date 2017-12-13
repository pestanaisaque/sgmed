$( document ).ready(function() {
	
	// Máscaras
	$("#cep_id").mask("99999-999");
	$('#cpf_id').mask('000.000.000-00');
	$('#telefone_id').mask('(00) 0000-0000');
	$('#celular_id').mask('(00) 00000-0000');
	
	
	// FORMULARIO DE CADASTRO
    $("#formCadastroPaciente").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostCad();
	});
    
    // FORMULARIO DE ALTERACAO
    $("#formAlteracaoPaciente").submit(function(event) {
    	
    	if ($("#formAlteracaoPaciente").data("changed")) {
        	// Prevent the form from submitting via the browser.
    		event.preventDefault();
    		ajaxPostAlt();
    	} else {
    		alert('Sem alteração');
    		event.preventDefault();
    	}
    	

	});
    
    // FORMULARIO DE EXCLUSAO
    $("#formExclusaoPaciente").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostExcl();
	});
    
    // Verifica se houve alteração em qualquer input
    $("#formAlteracaoPaciente :input").change(function() {
    	   $("#formAlteracaoPaciente").data("changed",true);
    });
    
    function ajaxPostCad(){
    	// PREPARE FORM DATA
    	var formData = {
    		nomePaciente : $("#nome_completo_id").val(),
    		dtNascimentoPaciente : $("#dt_nasc_id").val(),
    		cpfPaciente : 	$("#cpf_id").val().replace(/\D/g, ''),
    		telefonePaciente : $("#telefone_id").val().replace(/\D/g, ''),
    		celularPaciente : $("#celular_id").val().replace(/\D/g, ''),
    		emailPaciente : $("#email_id").val(),
    		cepPaciente : $("#cep_id").val().replace(/\D/g, ''),
    		enderecoPaciente : $("#endereco_id").val(),
    		numeroEnderecoPaciente : $("#numero_id").val(),
    		complementoEnderecoPaciente : $("#complemento_id").val(),
    		cidadePaciente : $("#cidade_id").val(),
    		estadoPaciente : $("#uf_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/cadastroPaciente/cadastrarPaciente",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Paciente cadastrado com sucesso.");
			    	window.location.href = "buscaPaciente"
				}else{
					alert("Já existe um paciente cadastrado com esse CPF.");
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
    	// PREPARE FORM DATA
    	var formData = {
    			idPaciente : $("#paciente_id").val(),
        		cpfPaciente : $("#cpf_id").val().replace(/\D/g, ''),
    			nomePaciente : $("#nome_completo_id").val(),
        		dtNascimentoPaciente : $("#dt_nasc_id").val(),
        		telefonePaciente : $("#telefone_id").val().replace(/\D/g, ''),
        		celularPaciente : $("#celular_id").val().replace(/\D/g, ''),
        		emailPaciente : $("#email_id").val(),
        		cepPaciente : $("#cep_id").val().replace(/\D/g, ''),
        		enderecoPaciente : $("#endereco_id").val(),
        		numeroEnderecoPaciente : $("#numero_id").val(),
        		complementoEnderecoPaciente : $("#complemento_id").val(),
        		cidadePaciente : $("#cidade_id").val(),
        		estadoPaciente : $("#uf_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/alteracaoPaciente/alterarPaciente",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Paciente alterado com sucesso.");
			    	window.location.href = "buscaAlteracaoPaciente"
				}else{
					alert("É necessário alterar ao menos um valor do paciente.");
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
    	// PREPARE FORM DATA
    	var formData = {
    			idPaciente : $("#idPaciente").val(),
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/exclusaoPaciente/excluirPaciente",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Paciente excluído com sucesso.");
			    	window.location.href = "buscaExclusaoPaciente"
				}else{
					alert("Falha ao excluir paciente");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
    
    //Quando o campo cep perde o foco.
    $("#cep_id").blur(function() {
        //Nova variável "cep" somente com dígitos.
        var cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "" && !(cep < 8) ) {

                //Preenche os campos com "..." enquanto consulta webservice.
                $("#endereco_id").val("...");
                $("#cidade_id").val("...");
                $("#uf_id").val("...");

                //Consulta o webservice viacep.com.br/
                $.getJSON("//viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
                        $("#endereco_id").val(dados.logradouro);
                        $("#cidade_id").val(dados.localidade);
                        $("#uf_id").val(dados.uf);
                        
                        // Remover a marcação de erro caso seja a segunda tentativa
                        $("#cep_id").removeClass("error");
                		$("#div_error_cep").html("");
                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        limpa_formulário_cep();
                        $("#cep_id").addClass("error");
                		$("#div_error_cep").html("CEP inválido.");
                    }
                });
        } //end if.
        else {
            //cep sem valor ou menor que 8 digitos, limpa formulário.
            limpa_formulário_cep();
            $("#cep_id").addClass("error");
    		$("#div_error_cep").html("CEP inválido.");
        }
    });
    
    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
    	$("#cep_id").val("");
    	$("#endereco_id").val("");
        $("#cidade_id").val("");
        $("#uf_id").val("");
    }
  
    // VALIDAR EMAIL
    $('#email_id').blur(function(){
		//atribuindo o valor do campo
		var sEmail	= $("#email_id").val();
		// filtros
		var emailFilter=/^.+@.+\..{2,}$/;
		var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
		// condição
		if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
			$("#email_id").addClass("error");
			$("#div_error_email").html("Formato de e-mail inválido");
			$("#email_id").val("");
			
		}else{
			$("#email_id").removeClass("error");
    		$("#div_error_email").html("");
		}
	});
    
    
    // VALIDAR CPF
    $("#cpf_id").blur(function() { 
    	var cpf = $("#cpf_id").val();
    	
    	if (!validaCPF(cpf)){
    		$("#cpf_id").addClass("error");
    		$("#div_error_cpf").html("CPF inválido.");
    		$("#cpf_id").val("");
    	} else {
    		$("#cpf_id").removeClass("error");
    		$("#div_error_cpf").html("");
    	}
    	
    });
    
    // VALIDAR TELEFONE
    $("#telefone_id").blur(function() { 
    	var cel = $("#telefone_id").val().replace(/\D/g, '');
    	
    	if (cel.length < 10 && cel.length != 0){
    		$("#telefone_id").addClass("error");
    		$("#div_error_telefone").html("Telefone inválido.");
    		$("#telefone_id").val("");
    	} else {
    		$("#telefone_id").removeClass("error");
    		$("#div_error_telefone").html("");
    	}
    	
    });
    
    // VALIDAR CELULAR
    $("#celular_id").blur(function() { 
    	var cel = $("#celular_id").val().replace(/\D/g, '');
    	
    	if (cel.length < 11){
    		$("#celular_id").addClass("error");
    		$("#div_error_celular").html("Celular inválido.");
    		$("#celular_id").val("");
    	} else {
    		$("#celular_id").removeClass("error");
    		$("#div_error_celular").html("");
    	}
    	
    });
    
    function validaCPF(cpf)
    {
      var cpf = $("#cpf_id").val().replace(/[^\d]+/g,'');
      var numeros, digitos, soma, i, resultado, digitos_iguais;
      digitos_iguais = 1;
      if (cpf.length < 11)
            return false;
      for (i = 0; i < cpf.length - 1; i++)
            if (cpf.charAt(i) != cpf.charAt(i + 1))
                  {
                  digitos_iguais = 0;
                  break;
                  }
      if (!digitos_iguais)
            {
            numeros = cpf.substring(0,9);
            digitos = cpf.substring(9);
            soma = 0;
            for (i = 10; i > 1; i--)
                  soma += numeros.charAt(10 - i) * i;
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(0))
                  return false;
            numeros = cpf.substring(0,10);
            soma = 0;
            for (i = 11; i > 1; i--)
                  soma += numeros.charAt(11 - i) * i;
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(1))
                  return false;
            return true;
            }
      else
          return false;
    }
})