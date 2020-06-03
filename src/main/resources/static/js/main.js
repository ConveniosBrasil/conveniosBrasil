$(document).ready(function() {
	$('.sidenav').sidenav();
	$(".dropdown-trigger").dropdown();
	$('.collapsible').collapsible();
	$('ul.tabs').tabs();
	$('select').formSelect();
	$('.modal').modal();
	$('textarea#mensagem').characterCounter();
	$('.parallax').parallax();

	// mascaras para dados e inputs
	$(".cnpj").mask("00.000.000/0000-00");
	$(".cpf").mask("000.000.000-00");
	$(".cep").mask("00000-000");
	$('.telefone').mask('(00) 0000-00009');
	$(".telefone").blur(function(event) {
		if ($('.telefone').val().length == 15)
			$('.telefone').mask('(00) 00000-0000');
		else
			$('.telefone').mask('(00) 0000-0000');
	});
	
	//Trocar o tipo do campo data ao ter foco
	$("#datanascimento").mask("00/00/0000");
	
	//Submit ao sair do campo de pesquisa
	$("#busca").blur(function(){
		$(".btnPesquisarSubmit").click();
	});
	
	//Clicar na div home é redirecionado para a pagina dos planos
	$(".bronze").click(function(){
		$(location).attr('href', '/plano#bronze');
	});
	
	$(".prata").click(function(){
		$(location).attr('href', '/plano#prata');
	});
	
	$(".ouro").click(function(){
		$(location).attr('href', '/plano#ouro');
	});
	
	// Transformando o texto id perfil para mauisculo
	$(".id_authority").keyup(function(){
	    $(this).val($(this).val().toUpperCase());
	});
	
	$(".id_authority").focus(function(){
		$(".id_authority").val() == "" ? $(".id_authority").val("ROLE_") : null;
	});
	
	var dataAtual = new Date();
	$(".anoAtualRodape").html(dataAtual.getFullYear());
	
	if($(".helper-text").text() != "")
		$(".erroGeral").css("display", "block").html("Cadastro com erro, favor corrigi-lo");
	
	$(".btnAlterarFoto").click(function(){
		if($(".file-path").val() == "")
			$(".erroImagemSelecionada").css("display", "block").html("Selecione uma imagem primeiro");
		else {
			$(".erroImagemSelecionada").css("display", "none");
			$(".formFotoPerfil").submit();
		}
	});
	
	$.urlParam = function(name){
		var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		if (results != null)
			return results[1] || 0;
	}
	
	$(".btnGravarNovaSenha").click(function(){
		var token = $.urlParam('token');
		if (token != null)	
			$(".tokenUsuario").val(token);
		$(".formNovaSenha").submit();		
	});	
});


