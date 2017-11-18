 $(document).ready(function() {
	 	function log( message ) {
	      $( "<div>" ).text( message ).prependTo( "#log" );
	      $( "#log" ).scrollTop( 0 );
	    }
	 
	 	//attach autocomplete
	    $("#estoque_nome_comercial_id").autocomplete({
	        minLength: 1,
	        delay: 500,
	        //define callback to format results
	        source: function (request, response) {
	            $.getJSON("/api/buscaMedicamentoEstoque/listarItensPorNomeComercial", request, function(result) {
	                response($.map(result, function(item) {
	                    return {
	                        // following property gets displayed in drop down
	                        label: item.medicamentoPO.nomeComercial + " - Dt validade: " + item.dtValidadeItemEstoque,
	                        // following property gets entered in the textbox
	                        value: item.medicamentoPO.nomeComercial + " - Dt validade: " + item.dtValidadeItemEstoque + " - Id: " + item.idItemEstoque ,
	                        // following property is added for our own use
	                        tag_url: "http://" + window.location.host + "/estoque/buscaItemEstoque" + item.medicamentoPO.nomeComercial
	                    }
	                }));
	            });
	        }
	    });
	});
