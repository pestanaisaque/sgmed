 $(document).ready(function() {
	 	
	 	var LOGIN = "";
	 	var NOME = "";
	 	
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
	                        label: item.nomeUsuario + " - " + item.login,
	                        // following property gets entered in the textbox
	                        value: item.nomeUsuario,
	                        
	                        login: item.login,
	                        
	                        nome: item.nomeUsuario
	                    }
	                }));
	            });
	        },
	        select: function( event, ui ) {
		    	LOGIN = ui.item.login;
		    	NOME = ui.item.nome;
	        }
	    });	 	
	 
	 
	    
	    // Se o campo do autocomplete nao for selecionado, retornar erro
	    $("#formRecuperacaoSenha").submit(function(event) {
	    	event.preventDefault();
	    	
	    	var nome =  $("#recuperar_senha_nome_usuario_id").val();
	    	
	    	if (NOME == nome){
				$("#login_par_id").val(LOGIN);
				$(this).unbind('submit').submit()
			} else {
				alert('Os valores devem ser selecionados a partir da lista');
			}
	    	
	    });
	});
