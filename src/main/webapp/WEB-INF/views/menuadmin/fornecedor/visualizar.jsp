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
				<b>Detalhes do Fornecedor</b>
			</legend>
			<ul>
				<c:if test="${fornecedor.id != ''}">
					<li><b>Código:</b> ${ fornecedor.id }</li>
				</c:if>
				<c:if test="${fornecedor.razaoSocial != ''}">
					<li><b>Razão Social:</b> ${ fornecedor.razaoSocial }</li>
				</c:if>
				<c:if test="${fornecedor.nomeFantasia != ''}">
					<li><b>Nome Fantasia:</b> <span class="cpf">${ fornecedor.nomeFantasia }</span></li>
				</c:if>
				<c:if test="${fornecedor.nomeResponsavel != ''}">
					<li><b>Nome Responsável:</b> ${ fornecedor.nomeResponsavel }</li>
				</c:if>
				<c:if test="${fornecedor.cnpj != ''}">
					<li><b>CNPJ:</b> ${ fornecedor.cnpj}</li>
				</c:if>
				<c:if test="${fornecedor.telefone != ''}">
					<li><b>Telefone:</b> <span class="telefone">${ fornecedor.telefone }</span></li>
				</c:if>
				<c:if test="${fornecedor.email != ''}">
					<li><b>E-mail:</b> ${ fornecedor.email }</li>
				</c:if>
				<c:if test="${fornecedor.cep != ''}">
					<li><b>CEP:</b> <span class="cep">${ fornecedor.cep }</span></li>
				</c:if>
				<c:if test="${fornecedor.rua != ''}">
					<li><b>Rua:</b> ${ fornecedor.rua }</li>
				</c:if>
				<c:if test="${fornecedor.numero != ''}">
					<li><b>Número:</b> ${ fornecedor.numero }</li>
				</c:if>
				<c:if test="${fornecedor.complemento != ''}">
					<li><b>Complementmo:</b> ${ fornecedor.complemento }</li>
				</c:if>
				<c:if test="${fornecedor.bairro != ''}">
					<li><b>Bairro:</b> ${ fornecedor.bairro }</li>
				</c:if>
				<c:if test="${fornecedor.cidade != ''}">
					<li><b>Cidade:</b> ${ fornecedor.cidade }</li>
				</c:if>
				<c:if test="${fornecedor.estado != ''}">
					<li><b>Estado:</b> ${ fornecedor.estado }</li>
				</c:if>
			</ul>
		</fieldset>
		<div class="col s12 center">
			<a class="btn-small orange" title="Voltar"
				href="${s:mvcUrl('listarFornecedorUrl').build()}">Voltar<i class="material-icons right">undo</i></a>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>