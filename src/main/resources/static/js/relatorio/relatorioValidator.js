$(document).ready(function() {

	// PACIENTE
	$('#tabelaRelatorioPaciente').DataTable({

		dom : 'Bfrtip',
		"oLanguage" : {
			"sSearch" : "Buscar" // search
		},
		
		"language" : {
			"lengthMenu" : "Mostrar _MENU_ registros por página",
			"zeroRecords" : "Nenhum registro encontrado",
			"info" : "Mostrando página _PAGE_ de _PAGES_",
			"infoEmpty" : "Sem registros disponíveis",
			"infoFiltered" : "(filtered from _MAX_ total records)"
		},
		
		buttons : [ {
			extend : 'excel',
			footer: true,
			text : 'Exportar Excel'
		} ]

	});
	
	// MEDICAMENTO
	$('#tabelaRelatorioMedicamento').DataTable({

		dom : 'Bfrtip',
		"oLanguage" : {
			"sSearch" : "Buscar" // search
		},
		
		"language" : {
			"lengthMenu" : "Mostrar _MENU_ registros por página",
			"zeroRecords" : "Nenhum registro encontrado",
			"info" : "Mostrando página _PAGE_ de _PAGES_",
			"infoEmpty" : "Sem registros disponíveis",
			"infoFiltered" : "(filtered from _MAX_ total records)"
		},
		
		buttons : [ {
			extend : 'excel',
			text : 'Exportar Excel'
		} ]

	});

})