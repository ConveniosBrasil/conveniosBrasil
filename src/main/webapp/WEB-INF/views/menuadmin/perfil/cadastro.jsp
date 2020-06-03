<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html lang="pt-br">
<%@ include file="../../base/header.jsp"%>
<body>
	<%@ include file="../../base/navbaradmin.jsp"%>
	<div class="container visualizacao">
		<div class="row">
			<div class="col s1"></div>
			<div class="col s11">
				<f:form class="col s12"
					action="${ s:mvcUrl('salvarPerfilUrl').build() }" method="post"
					modelAttribute="perfil">
					<div class="row">
						<div class="col s12 center">
							<label class="erroGeral"></label><br>
						</div>
						<h5 class="center">Cadastro de Perfil</h5>
						<div class="input-field col l12 s12">
							<label for="authority">Código Perfil</label>
							<f:input path="authority" cssClass="validate id_authority" maxlength="45" />
							<f:errors path="authority" cssClass="helper-text" />
						</div>
						<div class="input-field col l12 s12">
							<label for="descricao">Descricao</label>
							<f:input path="descricao" cssClass="validate" maxlength="255" />
							<f:errors path="descricao" cssClass="helper-text" />
						</div>
						<f:hidden path="id"/>
						<f:hidden path="visivel" value="S" />
						<div class="col s12 center">
							<button class="btn-small waves-effect waves-light green"
								type="submit" name="action">Gravar<i class="material-icons right">send</i></button>
							<a class="btn-small orange" title="Voltar"
								href="${s:mvcUrl('listarPerfilUrl').build()}">voltar<i class="material-icons right">undo</i></a>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>