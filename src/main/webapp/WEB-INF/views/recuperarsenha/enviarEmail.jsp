<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@ include file="../base/header.jsp"%>

<body class="paginalogin">
	<%@ include file="../base/navbar.jsp"%>
	<div class="container containerlogin">
		<div class="row">
			<div class="col s6 offset-s3">
				<div class="card opacity">
					<div class="card-content">
						<div class="col s12 mensagemStatusRecuperarEmail">
							<c:if test="${mensagemStatus != null}">
								<div class="mensagemStatus animated bounce">
									<p>${mensagemStatus}</p>
								</div>
							</c:if>
						</div>
						<h4 class="center">Recuperar Senha</h4>
						<h6 class="center color-white">Para começar o processo de alteração de sua senha,<br>digite seu e-mail</h6>
						<f:form action="${ s:mvcUrl('verificarEmailUrl').build() }" method="post" modelAttribute="usuario">
							<div class="row">
								<div class="input-field col l12 s12">
									<label for="username">Login</label>
									<f:input path="username" cssClass="validate" maxlength="255" />
									<f:errors path="username" cssClass="helper-text" />
								</div>
								<div class="col s12 center">
									<button class="btn waves-effect waves-light green"
										type="submit">Enviar e-mail <i class="material-icons right">send</i>
									</button>
								</div>
							</div>
						</f:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../base/footer.jsp"%>
	<%@ include file="../base/scripts.jsp"%>
</body>
</html>