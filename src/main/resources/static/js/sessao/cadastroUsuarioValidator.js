$( document ).ready(function() {
	
	// FORMULARIO DE CADASTRO
//    $("#formCadastroUsuario").submit(function(event) {
//		// Prevent the form from submitting via the browser.
//		event.preventDefault();
//		ajaxPostCad();
//	});
    
    function ajaxPostCad(){
    	// PREPARE FORM DATA
    	var formData = {
    		nomeUsuario : $("#usuario_nome_id").val(),
    		login : $("#usuario_login_id").val(),
    		senha : $("#usuario_senha_id").val(),
    		email : $("#usuario_email_id").val(),
    		perfis : {
    			perfilPO : {
    				idPerfil : $("#usuario_perfil_id").val()
    			}
    		}
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
//			    	window.location.href = "login"
				}else{
					alert("Já existe um usuário cadastrado com esse login.");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
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
})