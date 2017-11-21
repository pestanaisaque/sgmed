$(document).ready(function() {

	$('#tabelaRelatorio').DataTable( {

		dom: 'Bfrtip',
		"oLanguage": {
			  "sSearch": "Buscar" //search
			},
		buttons: [
        	{
        		extend: 'excel',
                text: 'Exportar Excel'
        	}
        ]
	
	} );

})