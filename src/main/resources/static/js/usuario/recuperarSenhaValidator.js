$( document ).ready(function() {
	
	// FORMULARIO DE CADASTRO
    $("#formResultadoRecuperarSenha").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostAlterarSenha();
	});
    
    function ajaxPostAlterarSenha(){
    	// PREPARE FORM DATA
    	var varNovaSenha = $("#resultado_recuperar_senha_id").val();
    	var varConfirmacaoNovaSenha = $("#resultado_recuperar_senha_confirmacao_id").val();
    	
    	//SEPARAR O TOKEN DA URL
    	var url = location.search.slice(1);
    	var varToken = url.split("=")[1];
    	
    	// Valida se as senhas coincidem
    	if (varNovaSenha != varConfirmacaoNovaSenha) {
    		$("#resultado_recuperar_senha_confirmacao_id").addClass("error");
    		$("#resultado_recuperar_senha_id").addClass("error");
			$("#div_error_resultado_recuperar_senha_confirmacao_id").html("As senhas digitadas não coincidem");
    	} else {
    		var formData = {
    	    		novaSenha : varNovaSenha,
    	    		confirmacaoNovaSenha : varConfirmacaoNovaSenha,
    	    		token : varToken
    	    	}
    	    	
    	    	// DO POST
    	    	$.ajax({
    				type : "POST",
    				contentType : "application/json",
    				url : "/api/cadastroUsuario/alterarSenhaUsuario",
    				data : JSON.stringify(formData),
    				dataType : 'json',
    				success : function(result) {
    					if(result.status == "OK"){
    						alert("Senha alterada com sucesso.");
    				    	window.location.href = "login"
    					}else if (result.status == "NOK_PASS"){
    						alert("Erro ao alterar senha, TOKEN inválido.");
    					}else if (result.status == "NOK_PASS"){
    						alert("Erro ao alterar senha.");
    					}
    					console.log(result);
    				},
    				error : function(e) {
    					alert("Error!");
    					console.log("ERROR: ", e);
    				}
    			});
    	}
    	
    	
    }
    
    // VALIDAR EMAIL
    $('#resultado_recuperar_senha_confirmacao_id').blur(function(){
		//atribuindo o valor do campo
    	var varNovaSenha = $("#resultado_recuperar_senha_id").val();
    	var varConfirmacaoNovaSenha = $("#resultado_recuperar_senha_confirmacao_id").val();
    	
		// condição
    	if (varNovaSenha != varConfirmacaoNovaSenha) {
    		$("#resultado_recuperar_senha_confirmacao_id").addClass("error");
    		$("#resultado_recuperar_senha_id").addClass("error");
			$("#div_error_resultado_recuperar_senha_confirmacao_id").html("As senhas digitadas não coincidem");
    	}else{
			$("#resultado_recuperar_senha_id").removeClass("error");
			$("#resultado_recuperar_senha_confirmacao_id").removeClass("error");
    		$("#div_error_resultado_recuperar_senha_confirmacao_id").html("");
		}
	});
    

})