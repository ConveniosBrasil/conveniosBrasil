<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<%@ include file="../../base/header.jsp"%>
<body>
	<%@ include file="../../base/navbaradmin.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col s11 colunaLista">	
				<c:if test="${mensagemStatus != null}">
					<div class="mensagemStatus animated bounce">
						<p>${mensagemStatus}</p>
					</div>
				</c:if>
				<div class="row">
						<div class="input-field col s6">
							<h5>Pedidos de Compra</h5>
						</div>
						<div class="input-field col s3 offset-s3">
							<a class="btn-floating btn-large waves-effect waves-light red right" title="novo" href="${s:mvcUrl('novoPedidoCompraUrl').build()}" >
								<i class="material-icons">add</i>
							</a>
						</div>
					</div>	
					
					<div class="row">
						<div class="col s12">
							<ul class="collapsible">
								<li>
									<div class="collapsible-header">
										<i class="material-icons">filter_list</i>Filtros
									</div>
									<div class="collapsible-body">
										<f:form method="GET" modelAttribute="pedidoFiltroForm" id="formFiltro">
											<f:hidden path="pagina"/>
											<f:hidden path="novoFiltro"/>
											
											<div class="row">
												<div class="input-field col s4">
													<label>
														<f:radiobutton path="tipoFiltro" value="RS" />
														<span>Razão Social</span>
													</label>
												</div>	
												<div class="input-field col s8">
													<label class="active" for="razaoSocial">Razão Social</label>
													<f:input path="razaoSocial" cssClass="validate"/>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s4">
													<label>
														<f:radiobutton path="tipoFiltro" value="ST" />
														<span>Status</span>
													</label>
												</div>	
												<div class="input-field col s8">
													<label class="active" for="status">Status</label>
													<f:select path="status">
														<f:options items="${listaDeStatus}" itemLabel="displayValue" />
													</f:select>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s4">
													<label>
														<f:radiobutton path="tipoFiltro" value="DT" />
														<span>Data de Entrega</span>
													</label>
												</div>	
												<div class="input-field col s4">
													<label class="active" for="entregaInicial">Entrega (Inicial)</label>
													<f:input path="entregaInicial" cssClass="validate datepicker"/>
												</div>	
												<div class="input-field col s4">
													<label class="active" for="entregaFinal">Entrega (Final)</label>
													<f:input path="entregaFinal" cssClass="validate datepicker"/>
												</div>	
											</div>
											<div class="row">
												<div class="col s12">
													<button class="btn-small right" type="button" id="btnFiltro">Pesquisar</button>
												</div>
											</div>
										</f:form>
									</div>
								</li>
							</ul>				
						</div>			
					</div>		
					
					<c:if test="${!listaPagina.isEmpty()}">
						<div class="row">
							<div class="responsive-table col s12">
								<table>
									<thead>
										<tr>
											<th>Nº</th>
											<th>Fornecedor</th>
											<th>Entrega</th>
											<th>Condição Pagto.</th>
											<th>Valor</th>
											<th>Status</th>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<th>Ativo / Inativo</th>
											</sec:authorize>
											<th class="center-align">Ações</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listaPaginada.content}" var="registro" >
											<tr>
												<fmt:parseDate value="${registro.dataEntrega}" pattern="yyyy-MM-dd" var="dataModificada" type="date" />
												<td>${registro.id}</td>
												<td>${registro.fornecedor.razaoSocial}</td>
												<td><fmt:formatDate value="${dataModificada}" pattern="dd/MM/yyyy" /></td>
												<td>${registro.condicaoPagamento.displayValue}</td>
												<td><fmt:formatNumber value="${registro.valorTotal}" type="currency"/></td>
												<td>${registro.status.displayValue}</td>
												<sec:authorize access="hasRole('ROLE_ADMIN')">
													<td>
														<c:if test="${registro.visivel == 'S'}">
															<i class="material-icons iconsRegistroAtivo" title="Pedido de compra ativo">check_circle</i>
														</c:if>
														<c:if test="${registro.visivel == 'N'}">
															<i class="material-icons iconsRegistroInativo" title="Pedido de compra inativo">cancel</i>
														</c:if>
													</td>
												</sec:authorize>
												<td class="center-align">
													<a class="btn-small green" title="alterar" href="${s:mvcUrl('alterarPedidoCompraUrl').arg(0, registro.id).build()}"><i class="material-icons">edit</i></a>
													<a class="btn-small" title="detalhes" href="${s:mvcUrl('visualizarPedidoCompraUrl').arg(0, registro.id).build()}"><i class="material-icons">more_horiz</i></a>
													
													<sec:authorize access="hasRole('ROLE_ADMIN')">	
														<c:if test="${registro.visivel == 'N'}">
															<button class="btn-small green btnAtivar modal-excluir" title="excluir"
																type="button" data-descr="${ registro.fornecedor.razaoSocial }" data-visivel="${ registro.visivel }">Ativar
																<i class="material-icons right">offline_pin</i>
																<f:form
																	action="${s:mvcUrl('ativarPedidoCompraUrl').arg(0, registro.id).build()}"
																	method="POST"></f:form>
															</button>
														</c:if>
														<c:if test="${registro.visivel == 'S'}">
															<button class="btn-small red modal-excluir" title="excluir"
																type="button" data-descr="${ registro.fornecedor.razaoSocial }" data-visivel="${ registro.visivel }">Inativar
																<i class="material-icons right">delete</i>
																<f:form
																	action="${s:mvcUrl('inativarPedidoCompraUrl').arg(0, registro.id).build()}"
																	method="POST"></f:form>
															</button>
														</c:if>	
													</sec:authorize>
													
													<sec:authorize access="!hasRole('ROLE_ADMIN')">
														<button class="btn-small red modal-excluir" title="excluir"
															type="button" data-target="modalExcluir"
															data-descr="${ registro.fornecedor.razaoSocial }">
															<i class="material-icons">delete</i>
															<f:form
																action="${s:mvcUrl('inativarPedidoCompraUrl').arg(0, registro.id).build()}"
																method="POST"></f:form>
														</button>
													</sec:authorize>
													
													<a href="${s:mvcUrl('geraPdfPedidoUrl').arg(0, registro.id).build()}" target="__blank"  class="btn-small deep-purple" title="imprimir" type="button">
														<i class="material-icons">print</i>
													</a>
												</td>
											</tr>			
										</c:forEach>		
									</tbody>	
								</table>
							</div>
						</div>
						
						<c:if test="${listaPaginada.hasContent()}">   
							<div class="row">
								<div class="col s12">
							        <ul class="pagination">
							            <li class="${listaPaginada.hasPrevious() ? '' : 'disabled'}">
							            	<c:if test="${listaPaginada.hasPrevious()}">
						            			<a href="#" class="paginacao" data-pagina="${listaPaginada.getNumber()}"><i class="material-icons">chevron_left</i></a>
							            	</c:if>
							            	<c:if test="${!listaPaginada.hasPrevious()}">
							            		<a href="#" class="paginacao"><i class="material-icons">chevron_left</i></a>
							            	</c:if>
							            </li>
							            
							            <c:forEach var="cont" begin="0" end="${listaPaginada.getTotalPages()-1}"    >
								            <li class="${cont==listaPaginada.getNumber() ? 'active' : ''}">
								            	<a href="#" class="paginacao" data-pagina="${cont+1}">${cont+1}</a>
								            </li>
							            </c:forEach>
							            
							            <li class="${listaPaginada.hasNext() ? '' : 'disabled'}" >
							            	<c:if test="${listaPaginada.hasNext()}">
							            		<a href="#" class="paginacao" data-pagina="${listaPaginada.getNumber()+2}" rel="next"><i class="material-icons">chevron_right</i></a>
							            	</c:if>				            	
							            	<c:if test="${!listaPaginada.hasNext()}">
							            		<a href="#" class="paginacao" rel="next"><i class="material-icons">chevron_right</i></a>
							            	</c:if>
							            </li>
							        </ul>				
								</div>
							</div>
						</c:if>				
					</c:if>
			</div>
		</div>
	</div>
</body>
<%@ include file="../../base/modalExcluir.jsp"%>
<%@ include file="../../base/footerAdmin.jsp"%>
<%@ include file="../../base/scripts.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".paginacao").on("click", function(e) {
			e.preventDefault();
			var pagina = $(this).data("pagina");
			
			//Muda o valor da pagina no formulario
			$("#pagina").val(pagina);
			$("#novoFiltro").val("false");
			
			if (pagina != null)
				$("#formFiltro").submit();
		});
		
		$("#btnFiltro").click(function(e) {
			e.preventDefault();
			$("#novoFiltro").val("true");
			$("#formFiltro").submit();
		});			
	});	
</script>	
</html>