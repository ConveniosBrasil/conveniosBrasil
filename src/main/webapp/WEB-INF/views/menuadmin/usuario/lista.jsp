<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html lang="pt-br">
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
					<div class="input-field col s3">
						<h5>Lista de Usuários</h5>
					</div>

					<f:form method="GET">
						<div class="input-field col s5">
							<input id="busca" name="busca" id="search" type="text"
								placeholder="Pesquisar por Nome" value="${busca}">
						</div>
						<div class="input-field col s1">
							<button class="btn-small btnPesquisarSubmit" type="submit">
								<i class="material-icons icons-white">search</i>
							</button>
						</div>
					</f:form>

						<div class="input-field col s3">
							<a
								class="btn-floating btn-large waves-effect waves-light red right"
								title="novo" href="${ s:mvcUrl('novoUsuarioUrl').build() }">
								<i class="material-icons icons-white icons-size">add</i>
							</a>
						</div>
				</div>

				<div class="row">
					<div class="responsive-table col l12 s12">
						<table>
							<thead>
								<tr>
									<th>Id</th>
									<th>Nome</th>
									<th>E-mail</th>
									<th>Telefone</th>
									<th>Ativo / Inativo</th>
									<th class="center-align tabelaColunaAcoes">Ações</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaPaginada.content}" var="registro">
									<tr>
										<td>${ registro.id }</td>
										<td>${ registro.nome }</td>
										<td>${ registro.username }</td>
										<td class="telefone">${ registro.telefone }</td>
										<td>
											<c:if test="${registro.visivel == 'S'}">
												<i class="material-icons iconsRegistroAtivo" title="Usuário ativo">check_circle</i>
											</c:if>
											<c:if test="${registro.visivel == 'N'}">
												<i class="material-icons iconsRegistroInativo" title="Usuário inativo">cancel</i>
											</c:if>
										</td>
										<td class="center-align tabelaColunaAcoes">
											<a class="btn-small green" title="alterar"
												href="${s:mvcUrl('alterarUsuarioUrl').arg(0, registro.id).build()}">
												<i class="material-icons icons-white">edit</i>
											</a> 
											<a class="btn-small" title="detalhes"
												href="${s:mvcUrl('visualizarUsuarioUrl').arg(0, registro.id).build()}">
												<i class="material-icons icons-white">menu_book</i>
											</a>
											<c:if test="${registro.visivel == 'N'}">
												<button class="btn-small green btnAtivar modal-excluir" title="excluir"
													type="button" data-descr="${ registro.nome }" data-visivel="${ registro.visivel }">Ativar
													<i class="material-icons right">offline_pin</i>
													<f:form
														action="${s:mvcUrl('ativarUsuarioUrl').arg(0, registro.id).build()}"
														method="POST"></f:form>
												</button>
											</c:if>
											<c:if test="${registro.visivel == 'S'}">
												<button class="btn-small red modal-excluir" title="excluir"
													type="button" data-descr="${ registro.nome }" data-visivel="${ registro.visivel }">Inativar
													<i class="material-icons right">delete</i>
													<f:form
														action="${s:mvcUrl('inativarUsuarioUrl').arg(0, registro.id).build()}"
														method="POST"></f:form>
												</button>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<%@ include file="../../base/paginacao.jsp"%>
				</div>
			</div>
		</div>

	</div>
	<%@ include file="../../base/modalExcluir.jsp"%>
	<%@ include file="../../base/footerAdmin.jsp"%>
	<%@ include file="../../base/scripts.jsp"%>
</body>
</html>