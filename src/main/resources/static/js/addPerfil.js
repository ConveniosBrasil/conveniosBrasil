$(document).ready(function() {
	$("#btnAddPerfil").click(function (){
		var dados = JSON.parse('{' + $("#perfilSel option:selected").val() + '}');
		if ($("#perfilSel").val() != ""){
			var temPerfilTabela = $(".nomePerfil").text().indexOf(dados.authority) > -1 ? true : false;
			var count = $("#tablePerfis tbody tr").length;
			var table = $("#tablePerfis tbody");
			var cHTML = "";
			$(".erroSelectPerfil").css("display", "none");
			
			if (temPerfilTabela)
				$(".erroSelectPerfil").css("display", "block").html("Este perfil já foi adicionado na tabela");
			else {
				$(".tabelaPerfilVazia").css("display","none");
				cHTML += "<tr>";
				cHTML += "<input type='hidden' id='listaPerfil[" + count + "].authority' name='listaPerfil[" + count + "].authority' value='"
									+ dados.authority + "' />";
				cHTML += "<input type='hidden' id='listaPerfil[" + count + "].descricao' name='listaPerfil[" + count + "].descricao' value='"
									+ dados.descricao + "' />";
				cHTML += "<input type='hidden' id='listaPerfil[" + count + "].id' name='listaPerfil[" + count + "].id' value='"
									+ dados.id + "' />";
				cHTML += "<input type='hidden' id='listaPerfil[" + count + "].visivel' name='listaPerfil[" + count + "].visivel' value='"
									+ dados.visivel + "' />";
				cHTML += "<td class='nomePerfil'>" + dados.authority + "</td>";
				cHTML += "<td>" + dados.descricao + "</td>";
				cHTML += "<td class='center-align'><button class='btn-small red btnDeletePerfil' title='Excluir' type='button'>" +
									"<i class='material-icons'>delete</i></button></td>";
				cHTML += "</tr>";
				table.append(cHTML);
			}
		} else
			$(".erroSelectPerfil").css("display", "block").html("É preciso selecionar um perfil primeiro");
	});
	
	$("#tablePerfis tbody").on("click", "button.btnDeletePerfil", function(botao){
		$(this).closest("tr").remove();
	});
});