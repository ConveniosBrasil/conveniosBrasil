<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ include file="../../base/header.jsp"%>
<html lang="pt-br">
<%@ include file="../../base/header.jsp"%>
<body>
	<%@ include file="../../base/navbaradmin.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col s1"></div>
			<div class="col s11">
				<f:form class="col s12"
					action="${ s:mvcUrl('salvarPedidoCompraUrl').build() }" method="post"
					modelAttribute="pedidoCompraForm">
					<div class="row">
						<div class="col s12 center">
							<label class="erroGeral"></label><br>
						</div>
						<h5 class="center">Cadastro de Pedido de Compra</h5>
						<div class="input-field col l12 s12">
							<f:select path="pedidoCompra.fornecedor" cssClass="validate">
								<f:option value="" >Selecione um fornecedor</f:option>
								<f:options items="${ listaFornecedores }" itemValue="id" itemLabel="razaoSocial" />
							</f:select> 
							<label for="pedidoCompra.fornecedor">Fornecedor</label>
							<f:errors path="pedidoCompra.fornecedor" cssClass="helper-text" />
						</div>
						<div class="input-field col l3 s12">
							<label for="pedidoCompra.dataEntrega">Data Entrega</label>
							<f:input path="pedidoCompra.dataEntrega" cssClass="validate datepicker datepickerDataEntrega" />
							<f:errors path="pedidoCompra.dataEntrega" cssClass="helper-text" />
						</div>
						<div class="input-field col l9 s12">
							<f:select path="pedidoCompra.condicaoPagamento" cssClass="validate">
								<f:option value="">Selecione uma condição de pagamento</f:option>
								<f:options items="${ listaCondicaoPagto }" itemLabel="displayValue" />
							</f:select> 
							<label for="pedidoCompra.condicaoPagamento" >Condição de Pagamento</label>
							<f:errors path="pedidoCompra.condicaoPagamento" cssClass="helper-text" />
						</div>
						<div class="input-field col l12 s12">
							<label for="pedidoCompra.observacao">Observação</label>
							<f:input path="pedidoCompra.observacao" cssClass="validate" maxlength="255" />
							<f:errors path="pedidoCompra.observacao" cssClass="helper-text" />
						</div>
						<div class="col s12 center">
							<span class="card-title center-align card-itens">Itens
								<button id="btnNovoItem" type="button" class="btn-floating btn-large waves-effect waves-light green right">
									<i class="material-icons icons-size">add</i>
								</button>
							</span>
						</div>
						<div class="row">
							<div class="col s12 center"><br>
								<f:errors path="itemPedidoCompra" cssClass="helper-text" />
								<div id="dadosItens">
									<c:forEach items="${pedidoCompraForm.itemPedidoCompra}" var="itemPedido" varStatus="status">
										<div class="card">
											<div class="card-content">
												<f:hidden path="itemPedidoCompra[${status.index}].id" />
												<f:hidden path="itemPedidoCompra[${status.index}].pedido" />
												<div class="row">
													<div class="input-field col s3">
														<f:select path="itemPedidoCompra[${status.index}].produto" cssClass="validate">
															<f:option value="" >Selecione um produto</f:option>
															<f:options items="${ listaProdutos }" item="id" itemLabel="nomeProduto" />
														</f:select> 
														<label for="itemPedidoCompra[${status.index}].produto">Produto</label>
														<f:errors path="itemPedidoCompra[${status.index}].produto" cssClass="helper-text" />
													</div>
													<div class="input-field col s3">
														<label class="active" for="itemPedidoCompra[${status.index}].quantidade">Quantidade</label>
														<f:input path="itemPedidoCompra[${status.index}].quantidade" cssClass="validate" />
														<f:errors path="itemPedidoCompra[${status.index}].quantidade" cssClass="helper-text" />
													</div>
													<div class="input-field col s3">
														<label class="active" for="itemPedidoCompra[${status.index}].precoUnitario">Preço Unitário</label>
														<f:input readonly="true" path="itemPedidoCompra[${status.index}].precoUnitario" cssClass="validate" />
														<f:errors path="itemPedidoCompra[${status.index}].precoUnitario" cssClass="helper-text" />
													</div>
													<div class="input-field col s3">
														<button class="btn-small red deleteItem" title="excluir" type="button" value="${status.index}">
															<i class="material-icons">delete</i>
														</button> 
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>						
						<f:hidden path="pedidoCompra.id"/>
						<f:hidden path="pedidoCompra.visivel" value="S" />
						<div class="col s12 center">
							<button class="btn-small waves-effect waves-light green"
								type="submit" name="action">Gravar<i class="material-icons right">send</i></button>
							<a class="btn-small orange" title="Voltar"
								href="${ s:mvcUrl('listarPedidoCompraUrl').build() }">voltar<i class="material-icons right">undo</i></a>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
	<script type="text/javascript">
		function montaGradeItens(retorno){
			var cHtml = $(retorno).find("#dadosRegistro").html();
			$("#dadosItens").html(cHtml);
			$("select").formSelect();
		}
		
		function erroAjax(dadosErro){
			console.log("Erro: " + retornoErro);
		}
	
		$("body").on("click", "#btnNovoItem", function(event){
			event.preventDefault();
			
			$.ajax({
				type : "POST",
				url : "/pedido/novoitem",
				data : $("form").serialize(),
				success : montaGradeItens,
				error : erroAjax
			});
		});
		
		$("body").on("click", ".deleteItem", function(event){
			event.preventDefault();
			
			$.ajax({
				type : "POST",
				url : "/pedido/deleteitem/" + $(this).val(),
				data : $("form").serialize(),
				success : montaGradeItens,
				error : erroAjax
			});
		});
		
		$("body").on("change", "select[name$='produto']", function(event) {
			var elementoProduto = $(this);
			var elementoPrecoUnitario = $(this).closest(".row").find("input[name$='precoUnitario']");
			
			$.ajax({
				type : "GET",
				url : "/api/produtos/" + elementoProduto.val(),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				success : function(dados) {
					elementoPrecoUnitario.val(dados.preco);
				},
				error : erroAjax
			});						
		});	
	</script>
</body>
</html>