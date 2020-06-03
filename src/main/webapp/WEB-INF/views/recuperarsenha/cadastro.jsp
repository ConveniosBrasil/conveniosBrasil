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
								<div class="mensagemStatus mensagemStatusToken animated bounce">
									<p>${mensagemStatus}</p>
								</div>
							</c:if>
						</div>
						<h4 class="center">Cadastrar Nova Senha</h4>
						<f:form action="${ s:mvcUrl('novaSenha').build() }" method="post" modelAttribute="senhaForm" cssClass="formNovaSenha" >
							<div class="row">
								<div class="input-field col l12 s12">
									<label for="usuario.password">Senha</label>
									<f:password path="usuario.password" cssClass="validate color-white" maxlength="255" required="required" />
									<f:errors path="usuario.password" cssClass="helper-text" />
								</div>
								<div class="input-field col l12 s12">
									<label for="repetirSenha">Repetir Senha</label>
									<f:password path="repetirSenha" cssClass="validate color-white" maxlength="255" />
									<f:errors path="repetirSenha" cssClass="helper-text" />
								</div>
								<f:hidden path="usuario.token" cssClass="tokenUsuario"/>
								<div class="col s12 center">
									<button class="btn waves-effect waves-light green btnGravarNovaSenha"
										type="button">Gravar Senha <i class="material-icons right">send</i>
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