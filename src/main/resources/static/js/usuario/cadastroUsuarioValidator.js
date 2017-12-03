$( document ).ready(function() {
	
	// FORMULARIO DE CADASTRO
    $("#formCadastroUsuario").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostCadastro();
	});
    
    // FORMULARIO DE ALTERACAO
    $("#formAlteracaoUsuario").submit(function(event) {
    	if ($("#formAlteracaoUsuario").data("changed")) {
    		// Prevent the form from submitting via the browser.
    		event.preventDefault();
    		ajaxPostAlteracao();
    	} else {
    		alert('É necessário alteração ao menos um valor.');
    		event.preventDefault();
    	}
		
	});
    
    // Verifica se houve alteração em qualquer input
    $("#formAlteracaoUsuario :input").change(function() {
    	   $("#formAlteracaoUsuario").data("changed",true);
    });
    
    // FORMULARIO DE EXCLUSAO
    $("#formExclusaoUsuario").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostExclusao();
	});
    
    function ajaxPostCadastro(){
    	// PREPARE FORM DATA
    	var formData = {
    		nomeUsuario : $("#usuario_nome_id").val(),
    		login : $("#usuario_login_id").val(),
    		senha : $("#usuario_senha_id").val(),
    		email : $("#usuario_email_id").val(),
    		perfis :
    		[
    			{
					idPerfil : $("#usuario_perfil_id").val()
    			}
    		]
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/cadastroUsuario/cadastrarUsuario",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Usuario cadastrado com sucesso.");
			    	window.location.href = "buscaUsuario"
				}else if (result.status == "NOK"){
					alert("Já existe um usuário cadastrado com esse login.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!");
				console.log("ERROR: ", e);
			}
		});
    }
    
    function ajaxPostAlteracao(){
    	// PREPARE FORM DATA
    	var formData = {
    		login : $("#alteracao_usuario_login_id").val(),
    		nomeUsuario : $("#alteracao_usuario_nome_id").val(),
    		email : $("#alteracao_usuario_email_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/cadastroUsuario/alterarUsuario",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Usuario alterado com sucesso.");
			    	window.location.href = "buscaAlteracaoUsuario"
				}else if (result.status == "NOK"){
					alert("Erro ao alterar usuário");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!");
				console.log("ERROR: ", e);
			}
		});
    }
    
    function ajaxPostExclusao(){
    	// PREPARE FORM DATA
    	var formData = {
    		login : $("#exclusao_usuario_login_id").val(),
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/cadastroUsuario/deletarUsuario",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Usuario excluído com sucesso.");
			    	window.location.href = "buscaUsuario"
				}else if (result.status == "NOK"){
					alert("Erro ao excluir usuário.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!");
				console.log("ERROR: ", e);
			}
		});
    }
    
    // VALIDAR EMAIL
    $('#usuario_email_id').blur(function(){
		//atribuindo o valor do campo
		var sEmail	= $("#usuario_email_id").val();
		// filtros
		var emailFilter=/^.+@.+\..{2,}$/;
		var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
		// condição
		if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
			$("#usuario_email_id").addClass("error");
			$("#div_error_usuario_email").html("Formato de e-mail inválido");
			$("#usuario_email_id").val("");
			
		}else{
			$("#usuario_email_id").removeClass("error");
    		$("#div_error_usuario_email").html("");
		}
	});
    

    // VALIDAR EMAIL ALTERAÇÃO
    $('#alteracao_usuario_email_id').blur(function(){
		//atribuindo o valor do campo
		var sEmail	= $("#alteracao_usuario_email_id").val();
		// filtros
		var emailFilter=/^.+@.+\..{2,}$/;
		var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
		// condição
		if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
			$("#alteracao_usuario_email_id").addClass("error");
			$("#div_error_alteracao_usuario_email").html("Formato de e-mail inválido");
			$("#alteracao_usuario_email_id").val("");
			
		}else{
			$("#alteracao_usuario_email_id").removeClass("error");
    		$("#div_error_alteracao_usuario_email").html("");
		}
	});

})