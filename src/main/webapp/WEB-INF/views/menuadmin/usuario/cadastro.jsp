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
					action="${ s:mvcUrl('salvarUsuarioUrl').build() }" method="post"
					modelAttribute="usuarioForm">
					<div class="row">
						<div class="col s12 center">
							<label class="erroGeral"></label><br>
						</div>
						<h5 class="center">Cadastro de Usuário</h5>
						<div class="row">
							<div class="input-field col l12 s12">
								<label for="usuario.nome">Nome completo</label>
								<f:input path="usuario.nome" cssClass="validate" maxlength="255" />
								<f:errors path="usuario.nome" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="usuario.cpf">CPF</label>
								<f:input path="usuario.cpf" cssClass="validate cpf"
									maxlength="11" />
								<f:errors path="usuario.cpf" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<label for="usuario.rg">RG</label>
								<f:input path="usuario.rg" cssClass="validate" maxlength="10" />
								<f:errors path="usuario.rg" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s6">
								<fieldset class="sexo">
									<legend>Sexo</legend>
									<label> <f:radiobutton cssClass="with-gap"
											path="usuario.sexo" value="F" /> <span>Feminino</span></label> <label>
										<f:radiobutton cssClass="with-gap" path="usuario.sexo"
											value="M" /> <span>Masculino</span>
									</label>
								</fieldset>
								<f:errors path="usuario.sexo" cssClass="helper-text sexoErrors" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l6 s12">
								<label for="usuario.data_nascimento">Data de Nascimento</label>
								<f:input path="usuario.data_nascimento" id="datanascimento"
									cssClass="validate" />
								<f:errors path="usuario.data_nascimento"
									cssClass="helper-text dataNascimento" />
							</div>
							<div class="input-field col l6 s12">
								<label for="usuario.telefone">Telefone com (DDD)</label>
								<f:input path="usuario.telefone" cssClass="validate telefone"
									maxlength="11" />
								<f:errors path="usuario.telefone" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="usuario.cep">CEP</label>
								<f:input path="usuario.cep" cssClass="validate cep"
									maxlength="8" />
								<f:errors path="usuario.cep" cssClass="helper-text" />
							</div>
							<div class="input-field col l8 s12">
								<label for="usuario.rua">Rua</label>
								<f:input path="usuario.rua" cssClass="validate" maxlength="255" />
								<f:errors path="usuario.rua" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="usuario.numero">Numero</label>
								<f:input path="usuario.numero" cssClass="validate"
									maxlength="10" />
								<f:errors path="usuario.numero" cssClass="helper-text" />
							</div>
							<div class="input-field col l8 s12">
								<label for="usuario.complemento">Complemento</label>
								<f:input path="usuario.complemento" cssClass="validate"
									maxlength="20" />
								<f:errors path="usuario.complemento" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="usuario.bairro">Bairro</label>
								<f:input path="usuario.bairro" cssClass="validate"
									maxlength="100" />
								<f:errors path="usuario.bairro" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<label for="usuario.cidade">Cidade</label>
								<f:input path="usuario.cidade" cssClass="validate"
									maxlength="100" />
								<f:errors path="usuario.cidade" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<label for="usuario.estado" class="estado">Estado</label>
								<f:select path="usuario.estado" cssClass="validate">
									<f:option value="">Selecione um estado</f:option>
									<f:option value="AC">Acre</f:option>
									<f:option value="AL">Alagoas</f:option>
									<f:option value="AP">Amapá</f:option>
									<f:option value="AM">Amazonas</f:option>
									<f:option value="BA">Bahia</f:option>
									<f:option value="CE">Ceará</f:option>
									<f:option value="DF">Distrito Federal</f:option>
									<f:option value="ES">Espirito Santo</f:option>
									<f:option value="GO">Goiás</f:option>
									<f:option value="MA">Maranhão</f:option>
									<f:option value="MS">Mato Grosso do Sul</f:option>
									<f:option value="MT">Mato Grosso</f:option>
									<f:option value="MG">Minas Gerais</f:option>
									<f:option value="PA">Pará</f:option>
									<f:option value="PB">Paraíba</f:option>
									<f:option value="PR">Paraná</f:option>
									<f:option value="PE">Pernambuco</f:option>
									<f:option value="PI">Piauí</f:option>
									<f:option value="RJ">Rio de Janeiro</f:option>
									<f:option value="RN">Rio Grande do Norte</f:option>
									<f:option value="RS">Rio Grande do Sul</f:option>
									<f:option value="RO">Rondônia</f:option>
									<f:option value="RR">Roraima</f:option>
									<f:option value="SC">Santa Catarina</f:option>
									<f:option value="SP">São Paulo</f:option>
									<f:option value="SE">Sergipe</f:option>
									<f:option value="TO">Tocantins</f:option>
								</f:select>
								<f:errors path="usuario.estado" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l12 s12">
								<label for="usuario.username">E-mail</label>
								<f:input path="usuario.username" cssClass="validate"
									maxlength="255" />
								<f:errors path="usuario.username" cssClass="helper-text" />
							</div>
							<c:if test="${usuarioForm.inclusao}">
								<div class="input-field col l12 s12">
									<label for="usuario.password">Senha</label>
									<f:password path="usuario.password" cssClass="validate"
										maxlength="255" />
									<f:errors path="usuario.password" cssClass="helper-text" />
								</div>
							</c:if>
						</div>
						<f:hidden path="usuario.visivel" value="S" />
						<f:hidden path="usuario.id" />
						<f:hidden path="usuario.foto" />
						<f:hidden path="usuario.token" />
						<f:hidden path="inclusao" />

						<h5 class="center">Perfis de Acesso</h5>
						<div class="row divPerfil">
							<div class="input-field col s8">
								<select name="" id="perfilSel">
									<option value="">Selecione um perfil de acesso</option>
									<c:forEach items="${ listaDePerfil }" var="perfil">
										<option
											value='"authority": "${ perfil.authority }", "id": "${ perfil.id }", "visivel": "${ perfil.visivel }", "descricao": "${ perfil.descricao }"'>${ perfil.descricao }</option>
									</c:forEach>
								</select> <label for="perfil">Perfil</label>
							</div>
							<div class="inpuit-field col s4">
								<button type="button" class="btn-small" id="btnAddPerfil">
									Adicionar <i class="material-icons right">add_circle</i>
								</button>
							</div>
							<div class="input-field col l12 s12">
								<label class="erroSelectPerfil"></label>
							</div>
						</div>
						<div class="row divTabelaPerfil">
							<div class="input-field col s12">
								<table class="responsive-table" id="tablePerfis">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Descrição</th>
											<th class="center-align">Ações</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${ usuarioForm.listaPerfil }" var="perfil"
											varStatus="status">
											<tr>
												<f:hidden path="listaPerfil[${ status.index }].authority" />
												<f:hidden path="listaPerfil[${ status.index }].descricao" />
												<f:hidden path="listaPerfil[${ status.index }].id" />
												<f:hidden path="listaPerfil[${ status.index }].visivel" />
												<td>${perfil.authority}</td>
												<td>${perfil.descricao}</td>
												<td class='center-align'>
													<button class='btn-small red btnDeletePerfil'
														title='Excluir' type='button'>
														<i class='material-icons'>delete</i>
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<br>
								<f:errors path="listaPerfil" cssClass="helper-text" />
							</div>
						</div>

						<div class="col s12 center">
							<button class="btn-small waves-effect waves-light green"
								type="submit" name="action">
								Gravar<i class="material-icons right">send</i>
							</button>
							<a class="btn-small orange" title="Voltar"
								href="${s:mvcUrl('listarUsuarioUrl').build()}">voltar<i
								class="material-icons right">undo</i></a>
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