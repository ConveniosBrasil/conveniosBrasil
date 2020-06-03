<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html lang="pt-br">
<%@ include file="../../base/header.jsp"%>
<body>
	<%@ include file="../../base/navbaradmin.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col s1"></div>
			<div class="col s11">
				<f:form class="col s12"
					action="${ s:mvcUrl('salvarProdutoUrl').build() }" method="post"
					modelAttribute="produto">
					<div class="row">
						<div class="col s12 center">
							<label class="erroGeral"></label><br>
						</div>
						<h5 class="center">Cadastro de Produto</h5>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="nomeProduto">Produto</label>
								<f:input path="nomeProduto" cssClass="validate" maxlength="30" />
								<f:errors path="nomeProduto" cssClass="helper-text" />
							</div>
							<div class="input-field col l8 s12">
								<label for="descricao">Descricao</label>
								<f:input path="descricao" cssClass="validate" maxlength="100" />
								<f:errors path="descricao" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l6 s12">
								<label for="saldo">Saldo</label>
								<f:input path="saldo" cssClass="validate" type="number" step="1" />
								<f:errors path="saldo" cssClass="helper-text" />
							</div>
							<div class="input-field col l6 s12">
								<label for="preco">Preço</label>
								<f:input path="preco" cssClass="validate" type="number" step="0.01" />
								<f:errors path="preco" cssClass="helper-text" />
							</div>
						</div>
						
						<f:hidden path="visivel" value="S" />
						<f:hidden path="id" />

						<div class="col s12 center">
							<button class="btn-small waves-effect waves-light green"
								type="submit" name="action">Gravar<i class="material-icons right">send</i></button>
							<a class="btn-small orange" title="Voltar"
								href="${s:mvcUrl('listarProdutoUrl').build()}">voltar<i class="material-icons right">undo</i></a>
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