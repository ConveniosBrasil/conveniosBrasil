$(".modal-excluir").click(function() {
	
    var button    = $(this);
    var descricao = button.data('descr');
    var visivel = button.data('visivel');
    var modal     = $("#modalExcluir");
    
    var ativoInativo = visivel == 'S' ? 'inativar' : 'ativar';

    modal.find('.modal-content p').html('Deseja realmente ' + ativoInativo + ' o registro: <strong>'  + descricao + '</strong> ?');
    
    $('#btnModalSim').click(function(){
    	$(button).find("form").submit();
    });
    
    modal.modal('open');
});