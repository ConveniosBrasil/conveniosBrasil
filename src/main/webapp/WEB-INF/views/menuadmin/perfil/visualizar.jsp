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
				<b>Detalhes do Perfil</b>
			</legend>
			<ul>
				<li><b>Código Perfil:</b> ${ perfil.authority }</li>
				<li><b>Descrição:</b> ${ perfil.descricao }</li>
			</ul>
		</fieldset>
		<div class="col s12 center">
			<a class="btn-small orange" title="Voltar"
				href="${s:mvcUrl('listarPerfilUrl').build()}">Voltar<i class="material-icons right">undo</i></a>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>