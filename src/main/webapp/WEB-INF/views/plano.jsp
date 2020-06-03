<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt-br">
<%@ include file="base/header.jsp"%>
<body>
	<%@ include file="base/navbar.jsp"%>

	<div class="container">
		<div class="col s12 m7">
			<h2 class="header center">Planos</h2>
			<div class="card horizontal">
				<div class="card-image">
					<img src="/img/plano-bronze.png">
				</div>
				<div class="card-stacked">
					<div class="card-content" id="bronze">
						<p>O plano Bronze abrange especialidades para pessoas que não
							podem contribuir muito, mas que não podem ficar sem o que a saúde
							necessita. Especialidades e exames básicos são ofertados pelo
							plano, como:
						</p>
							<ol>
								<li>Clínico geral</li>
								<li>Ortopedia</li>
								<li>Pediatria</li>
								<li>Urologista</li>
								<li>Ginecologia</li>
								<li>Hepatologista</li>
								<li>Exame de sangue</li>
								<li>Exame de urina</li>
							</ol>
					</div>
				</div>
			</div>
			<br>
			<div class="card horizontal">
				<div class="card-image">
					<img src="/img/plano-prata.png">
				</div>
				<div class="card-stacked">
					<div class="card-content" id="prata">
						<p>O plano Prata além de cobrir todas as especialidades e exames que o 
						plano Bronze garante, ele também oferece mais especialidades como:
						</p>
						<ol>
							<li>Neurologistas</li>
							<li>Cardiologia</li>
							<li>Gastroenterologia</li>
							<li>Oftalmologia</li>
							<li>Otorrinolaringologia</li>
							<li>Psicologia</li>
						</ol>
					</div>
				</div>
			</div>
			<br>
			<div class="card horizontal">
				<div class="card-image">
					<img src="/img/plano-ouro.png">
				</div>
				<div class="card-stacked">
					<div class="card-content" id="ouro">
						<p>
							O plano Ouro, cobre todos os benefícios 
							dos planos anteriores, e também tem coberturas a mais, como:
						</p>
						<ol>
							<li>Quartos particulares </li>
							<li>Cobertura para cirurgias</li>
						</ol>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>
	<%@ include file="base/footer.jsp"%>
	<%@ include file="base/scripts.jsp"%>
</body>
</html>