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
						<h5 class="center color-white">E-mail enviado com sucesso!</h5>
						<div class="color-white center">A informação da recuperação de senha foi enviada para o seu e-mail, favor conferir seu e-mail</div><br>
						<div class="col s12 center">
							<a href="${ s:mvcUrl('loginUrl').build() }" class="btn waves-effect waves-light blue color-white">Voltar para o login</a>
						</div><br><br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../base/footer.jsp"%>
	<%@ include file="../base/scripts.jsp"%>
</body>
</html>