<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:form action="" method="post" modelAttribute="pedidoCompraForm">
	<div id="dadosRegistro">
		<c:forEach items="${pedidoCompraForm.itemPedidoCompra}" var="itemPedido" varStatus="status">
			<div class="card">
				<div class="card-content">
					<f:hidden path="itemPedidoCompra[${status.index}].id" />
					<f:hidden path="itemPedidoCompra[${status.index}].pedido" />
					<div class="row">
						<div class="input-field col s3">
							<f:select path="itemPedidoCompra[${status.index}].produto" cssClass="validate">
								<f:option value="" >Selecione um produto</f:option>
								<f:options items="${ listaProdutos }" item="id" itemLabel="nomeProduto" />
							</f:select> 
							<label for="itemPedidoCompra[${status.index}].produto">Produto</label>
							<f:errors path="itemPedidoCompra[${status.index}].produto" cssClass="helper-text" />
						</div>
						<div class="input-field col s3">
							<label class="active" for="itemPedidoCompra[${status.index}].quantidade">Quantidade</label>
							<f:input path="itemPedidoCompra[${status.index}].quantidade" cssClass="validate" />
							<f:errors path="itemPedidoCompra[${status.index}].quantidade" cssClass="helper-text" />
						</div>
						<div class="input-field col s3">
							<label class="active" for="itemPedidoCompra[${status.index}].precoUnitario">Preço Unitário</label>
							<f:input readonly="true" path="itemPedidoCompra[${status.index}].precoUnitario" cssClass="validate" />
							<f:errors path="itemPedidoCompra[${status.index}].precoUnitario" cssClass="helper-text" />
						</div>
						<div class="input-field col s3">
							<button class="btn-small red deleteItem" title="excluir" type="button" value="${status.index}" >
								<i class="material-icons">delete</i>
							</button> 
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</f:form>