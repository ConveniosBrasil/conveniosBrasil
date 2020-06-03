<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="base/header.jsp"%>
<body>
	<%@ include file="base/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<c:if test="${mensagemStatus != null}">
				<div class="mensagemStatus animated bounce">
					<p>${mensagemStatus}</p>
				</div>
			</c:if>
			<div class="col l6 s12">
				<f:form action="${ s:mvcUrl('enviarEmailContatoUrl').build() }" method="post" modelAttribute="contatoForm">
					<div class="row">
						<h5 class="center">Contato</h5>
						<div class="input-field col s12">
							<label for="nome">Nome</label>
							<f:input path="nome" cssClass="validate" maxlength="255" />
						</div>
						<div class="input-field col s12">
							<label for="email">E-mail</label>
							<f:input path="email" cssClass="validate" maxlength="255" type="email" />
						</div>
						<div class="input-field col s12">
							<label for="telefone">Telefone</label>
							<f:input path="telefone" cssClass="validate telefone" maxlength="255" />
						</div>
						<div class="input-field col s12">
							<f:textarea path="mensagem" cssClass="validate materialize-textarea" />
							<label for="mensagem">Mensagem</label>
						</div>
						<div class="col s12 center">
							<button class="btn waves-effect waves-light green btnContatoEmail"
								type="submit">Enviar <i class="material-icons right">send</i></button>
						</div>
					</div>
				</f:form>
			</div>
			<div class="col l6 s12">
				<h5 class="center">Endereço</h5>
				<iframe class="mapacontato" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3654.4020670070963!2d-46.53171538486907!3d-23.661575371186274!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce42890ae627ef%3A0x934856e70030c41a!2sFAPEN%20-%20Faculdade%20Pent%C3%A1gono!5e0!3m2!1spt-BR!2sbr!4v1569285927352!5m2!1spt-BR!2sbr"></iframe>
			</div>
		</div>
	</div>
	<%@ include file="base/footer.jsp"%>
	<%@ include file="base/scripts.jsp"%>
</body>
</html>
