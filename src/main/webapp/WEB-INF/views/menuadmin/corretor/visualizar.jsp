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
				<b>Detalhes do Corretor</b>
			</legend>
			<fmt:parseDate value="${ corretor.dataNascimento }"
				pattern="yyyy-MM-dd" var="parsedDate" type="date" />
			<ul>
				<c:if test="${corretor.id != ''}">
					<li><b>Código:</b> ${ corretor.id }</li>
				</c:if>
				<c:if test="${corretor.nome != ''}">
					<li><b>Nome Completo:</b> ${ corretor.nome }</li>
				</c:if>
				<c:if test="${corretor.cpf != ''}">
					<li><b>CPF:</b> <span class="cpf">${ corretor.cpf }</span></li>
				</c:if>
				<c:if test="${corretor.rg != ''}">
					<li><b>RG:</b> ${ corretor.rg }</li>
				</c:if>
				<c:if test="${corretor.sexo != ''}">
					<li><b>Sexo:</b> ${ corretor.sexo == 'M'? 'Masculino' : 'Feminino'}</li>
				</c:if>
				<c:if test="${corretor.dataNascimento != ''}">
					<li><b>Data de Nascimento:</b> <fmt:formatDate
							value="${parsedDate}" pattern="dd/MM/yyyy" /></li>
				</c:if>
				<c:if test="${corretor.telefone != ''}">
					<li><b>Telefone:</b> <span class="telefone">${ corretor.telefone }</span></li>
				</c:if>
				<c:if test="${corretor.email != ''}">
					<li><b>E-mail:</b> ${ corretor.email }</li>
				</c:if>
				<c:if test="${corretor.cep != ''}">
					<li><b>CEP:</b> <span class="cep">${ corretor.cep }</span></li>
				</c:if>
				<c:if test="${corretor.rua != ''}">
					<li><b>Rua:</b> ${ corretor.rua }</li>
				</c:if>
				<c:if test="${corretor.numero != ''}">
					<li><b>Número:</b> ${ corretor.numero }</li>
				</c:if>
				<c:if test="${corretor.complemento != ''}">
					<li><b>Complementmo:</b> ${ corretor.complemento }</li>
				</c:if>
				<c:if test="${corretor.bairro != ''}">
					<li><b>Bairro:</b> ${ corretor.bairro }</li>
				</c:if>
				<c:if test="${corretor.cidade != ''}">
					<li><b>Cidade:</b> ${ corretor.cidade }</li>
				</c:if>
				<c:if test="${corretor.estado != ''}">
					<li><b>Estado:</b> ${ corretor.estado }</li>
				</c:if>
			</ul>
		</fieldset>
		<div class="col s12 center">
			<a class="btn-small orange" title="Voltar"
				href="${s:mvcUrl('listarCorretorUrl').build()}">Voltar<i class="material-icons right">undo</i></a>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>