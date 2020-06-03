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
					action="${ s:mvcUrl('salvarCorretorUrl').build() }" method="post"
					modelAttribute="corretor">
					<div class="row">
						<div class="col s12 center">
							<label class="erroGeral"></label><br>
						</div>
						<h5 class="center">Cadastro de Corretor</h5>
						<div class="row">
							<div class="input-field col l12 s12">
								<label for="nome">Nome completo</label>
								<f:input path="nome" cssClass="validate" maxlength="255" />
								<f:errors path="nome" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="cpf">CPF</label>
								<f:input path="cpf" cssClass="validate cpf" maxlength="11" />
								<f:errors path="cpf" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<label for="rg">RG</label>
								<f:input path="rg" cssClass="validate" maxlength="10" />
								<f:errors path="rg" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<fieldset class="sexo">
									<legend>Sexo</legend>
									<label> <f:radiobutton cssClass="with-gap" path="sexo"
											value="F" /> <span>Feminino</span></label> <label><f:radiobutton
											cssClass="with-gap" path="sexo" value="M" /> <span>Masculino</span></label>
								</fieldset>
								<f:errors path="sexo" cssClass="helper-text sexoErrors" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l6 s12">
								<label for="dataNascimento">Data de Nascimento</label>
								<f:input path="dataNascimento" cssClass="validate" id="datanascimento" />
								<f:errors path="dataNascimento"
									cssClass="helper-text dataNascimento" />
							</div>
							<div class="input-field col l6 s12">
								<label for="telefone">Telefone com (DDD)</label>
								<f:input path="telefone" cssClass="validate telefone" maxlength="11" />
								<f:errors path="telefone" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l12 s12">
								<label for="email">E-mail</label>
								<f:input path="email" cssClass="validate" maxlength="255" />
								<f:errors path="email" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="cep">CEP</label>
								<f:input path="cep" cssClass="validate cep" maxlength="8" />
								<f:errors path="cep" cssClass="helper-text" />
							</div>
							<div class="input-field col l8 s12">
								<label for="rua">Rua</label>
								<f:input path="rua" cssClass="validate" maxlength="255" />
								<f:errors path="rua" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="numero">Numero</label>
								<f:input path="numero" cssClass="validate" maxlength="10" />
								<f:errors path="numero" cssClass="helper-text" />
							</div>
							<div class="input-field col l8 s12">
								<label for="complemento">Complemento</label>
								<f:input path="complemento" cssClass="validate" maxlength="20" />
								<f:errors path="complemento" cssClass="helper-text" />
							</div>
						</div>
						<div class="row">
							<div class="input-field col l4 s12">
								<label for="bairro">Bairro</label>
								<f:input path="bairro" cssClass="validate" maxlength="100" />
								<f:errors path="bairro" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<label for="cidade">Cidade</label>
								<f:input path="cidade" cssClass="validate" maxlength="100" />
								<f:errors path="cidade" cssClass="helper-text" />
							</div>
							<div class="input-field col l4 s12">
								<label for="estado" class="estado">Estado</label>
								<f:select path="estado" cssClass="validate">
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
								<f:errors path="estado" cssClass="helper-text" />
							</div>
						</div>
						<f:hidden path="visivel" value="S" />
						<f:hidden path="id" />

						<div class="col s12 center">
							<button class="btn-small waves-effect waves-light green"
								type="submit" name="action">Gravar<i class="material-icons right">send</i></button>
							<a class="btn-small orange" title="Voltar"
								href="${s:mvcUrl('listarCorretorUrl').build()}">voltar<i class="material-icons right">undo</i></a>
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