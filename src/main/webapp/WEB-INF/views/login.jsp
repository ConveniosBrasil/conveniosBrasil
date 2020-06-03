<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<%@ include file="base/header.jsp"%>

<body class="paginalogin">
	<%@ include file="base/navbar.jsp"%>
	<div class="container containerlogin">
		<div class="row">
			<div class="col s6 offset-s3">
				<div class="card opacity">
					<div class="card-content">
						<h4 class="center">Entrar no Sistema</h4>
						<f:form class="form-signin"
							action="${s:mvcUrl('loginUrl').build()}" method="POST">
							<div class="row">
								<div class="input-field col l12 s12">
									<label for="username">Login</label>
									<input type="email" id="username" name="username" class="validate" required autofocus>
								</div>
								<div class="input-field col l12 s12">
									<label for="password">Password</label>
									<input type="password" id="password" name="password" class="validate" required>
								</div>
								<div class="col s12 center">
									<button class="btn waves-effect waves-light green"
										type="submit">Entrar <i class="material-icons right">send</i>
									</button><br><br>
									<a href="${ s:mvcUrl('esqueciSenhaUrl').build() }" class="btn waves-effect waves-light blue color-white">Recuperar Senha</a>
								</div>
							</div>
						</f:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="base/footer.jsp"%>
	<%@ include file="base/scripts.jsp"%>
</body>
</html>