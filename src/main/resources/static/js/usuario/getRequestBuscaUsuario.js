 $(document).ready(function() {
	 	
	 	// AUTOCOMPLETE DO NOME
	    $("#recuperar_senha_nome_usuario_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/cadastroUsuario/listarUsuariosPorNome", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.nomeUsuario + " - " + item.idUsuario,
	                        // following property gets entered in the textbox
	                        value: item.nomeUsuario + " - login: " + item.login + " - Id: " + item.idUsuario,
	                        
	                        tag_url: "http://" + window.location.host + "/sessao/recuperarSenha" + item.nomeUsuario
	                    }
	                }));
	            });
	        }
	    });	 	
	 
	 
	    
	    // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formRecuperacaoSenha").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var login =  $("#recuperar_senha_nome_usuario_id").val();
	    	
	    	if (login.indexOf(':') < 0) {
	    		alert('Os valores devem ser selecionados a partir da lista');
	    	} else {
	    		$(this).unbind('submit').submit()
	    	}
	    	
	    });
	});
