$( document ).ready(function() {
	
    $("#formEntradaMedicamentoEstoque").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPostCad();
	});
    
    $('input[type=radio][name=generico]').on('change', function() {    	
    	$('#naoGenerico').attr('checked', false)
    });
    
    $('input[type=radio][name=naoGenerico]').on('change', function() {
    	$('#generico').attr('checked', false)
    });
    
    function ajaxPostCad(){
    	var isCheked = jQuery("input[name=generico]:checked").val();
    	
    	// PREPARE FORM DATA
    	var formData = {
    		medicamentoPO : {
    			idMedicamento : $("#medicamento_id").val()
    		},
    		
    		qtdItemEmEstoque : $("#quantidade_a_inserir_id").val(),
    		dtValidadeItemEstoque : $("#data_validade_id").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/api/entradaMedicamentoEstoque/inserirMedicamentoEmEstoque",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "OK"){
					alert("Medicamento inserido em estoque com sucesso. ID: " + result.data.idItemEstoque);
			    	window.location.href = "/home"
				}else{
					alert("Erro ao cadastrar item");
				}
				console.log(result);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }
    
})