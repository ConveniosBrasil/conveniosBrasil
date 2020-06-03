<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="pt-br">
<%@ include file="../../base/header.jsp"%>
<body>
	<%@ include file="../../base/navbaradmin.jsp"%>
	<div class="container visualizacao">
		<fieldset class="visualizar">
			<legend>
				<b>Detalhes do Produto</b>
			</legend>
			<ul>
				<c:if test="${produto.id != ''}">
					<li><b>Código:</b> ${ produto.id }</li>
				</c:if>
				<c:if test="${produto.nomeProduto != ''}">
					<li><b>Produto:</b> ${ produto.nomeProduto }</li>
				</c:if>
				<c:if test="${produto.descricao != ''}">
					<li><b>Descrição:</b> ${ produto.descricao }</li>
				</c:if>
				<c:if test="${produto.saldo != ''}">
					<li><b>Saldo:</b> ${ produto.saldo }</li>
				</c:if>
				<c:if test="${produto.preco != ''}">
					<li><b>Preço:</b> <fmt:formatNumber value="${produto.preco }" type="currency" /></li>
				</c:if>
			</ul>
		</fieldset>
		<div class="col s12 center">
			<a class="btn-small orange" title="Voltar"
				href="${s:mvcUrl('listarProdutoUrl').build()}">Voltar<i class="material-icons right">undo</i></a>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>