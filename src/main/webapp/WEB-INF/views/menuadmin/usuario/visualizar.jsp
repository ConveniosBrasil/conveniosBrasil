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
				<b>Detalhes do Usuário</b>
			</legend>
			<fmt:parseDate value="${ usuario.data_nascimento }"
				pattern="yyyy-MM-dd" var="parsedDate" type="date" />
			<ul>
				<c:if test="${usuario.id != ''}">
					<li><b>Código:</b> ${ usuario.id }</li>
				</c:if>
				<c:if test="${usuario.nome != ''}">
					<li><b>Nome Completo:</b> ${ usuario.nome }</li>
				</c:if>
				<c:if test="${usuario.cpf != ''}">
					<li><b>CPF:</b> <span class="cpf">${ usuario.cpf }</span></li>
				</c:if>
				<c:if test="${usuario.rg != ''}">
					<li><b>RG:</b> ${ usuario.rg }</li>
				</c:if>
				<c:if test="${usuario.sexo != ''}">
					<li><b>Sexo:</b> ${ usuario.sexo == 'M'? 'Masculino' : 'Feminino'}</li>
				</c:if>
				<c:if test="${usuario.data_nascimento != ''}">
					<li><b>Data de Nascimento:</b> <fmt:formatDate
							value="${parsedDate}" pattern="dd/MM/yyyy" /></li>
				</c:if>
				<c:if test="${usuario.telefone != ''}">
					<li><b>Telefone:</b> <span class="telefone">${ usuario.telefone }</span></li>
				</c:if>
				<c:if test="${usuario.username != ''}">
					<li><b>E-mail:</b> ${ usuario.username }</li>
				</c:if>
				<c:if test="${usuario.cep != ''}">
					<li><b>CEP:</b> <span class="cep">${ usuario.cep }</span></li>
				</c:if>
				<c:if test="${usuario.rua != ''}">
					<li><b>Rua:</b> ${ usuario.rua }</li>
				</c:if>
				<c:if test="${usuario.numero != ''}">
					<li><b>Número:</b> ${ usuario.numero }</li>
				</c:if>
				<c:if test="${usuario.complemento != ''}">
					<li><b>Complementmo:</b> ${ usuario.complemento }</li>
				</c:if>
				<c:if test="${usuario.bairro != ''}">
					<li><b>Bairro:</b> ${ usuario.bairro }</li>
				</c:if>
				<c:if test="${usuario.cidade != ''}">
					<li><b>Cidade:</b> ${ usuario.cidade }</li>
				</c:if>
				<c:if test="${usuario.estado != ''}">
					<li><b>Estado:</b> ${ usuario.estado }</li>
				</c:if>
			</ul>
		</fieldset>
		<div class="col s12 center">
			<a class="btn-small orange" title="Voltar"
				href="${s:mvcUrl('listarUsuarioUrl').build()}">Voltar<i class="material-icons right">undo</i></a>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>