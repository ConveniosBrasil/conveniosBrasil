<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt-br">
<%@ include file="base/header.jsp"%>
<body>
	<%@ include file="base/navbar.jsp"%>

	<div class="container">
		<div class="carousel carousel-slider" data-indicators="true">
			<div class="carousel-item" href="#one!">
				<a href="#"><img src="/img/slideshow1.jpg"></a>
			</div>
			<div class="carousel-item" href="#two!">
				<a href="#"><img src="/img/slideshow2.jpg"></a>
			</div>
			<div class="carousel-item" href="#three!">
				<a href="#"><img src="/img/slideshow3.jpg"></a>
			</div>
			<div class="carousel-item" href="#four!">
				<a href="#"><img src="/img/slideshow4.jpg"></a>
			</div>
		</div>

		<div class="planos-home">
			<div class="planos-home-center">
				<div class="flip-card">
					<div class="flip-card-inner">
						<div class="flip-card-front">
							<img src="img/plano-bronze.png" alt="Plano Bronze">
						</div>
						<div class="flip-card-back bronze">
							<br>
							<h3>Plano Bronze</h3>
							<p class="saibamais">
								Clique Aqui e <br> Saiba Mais
							</p>
						</div>
					</div>
				</div>
				<div class="flip-card">
					<div class="flip-card-inner">
						<div class="flip-card-front">
							<img src="img/plano-prata.png" alt="Plano prata">
						</div>
						<div class="flip-card-back prata">
							<br>
							<h3>Plano Prata</h3>
							<p class="saibamais">
								Clique Aqui e <br> Saiba Mais
							</p>
						</div>
					</div>
				</div>
				<div class="flip-card">
					<div class="flip-card-inner">
						<div class="flip-card-front">
							<img src="img/plano-ouro.png" alt="Plano ouro">
						</div>
						<div class="flip-card-back ouro">
							<br>
							<h3>Plano Ouro</h3>
							<p class="saibamais">
								Clique Aqui e <br> Saiba Mais
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="base/footer.jsp"%>
	<%@ include file="base/scripts.jsp"%>
</body>
</html>