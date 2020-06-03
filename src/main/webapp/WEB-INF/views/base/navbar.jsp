<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<nav>
	<div class="nav-wrapper blue lighten-5">
		<a href="/home" class="brand-logo">
			<img src="/img/conveniosbrasil.png"	class="logo">
		</a> 
		<a href="#" data-target="mobile-demo"
			class="sidenav-trigger"> <i class="material-icons">menu</i>
		</a>
		<ul class="right hide-on-med-and-down">
			<li><a href="${ s:mvcUrl('homeUrl').build() }">Inicio</a></li>
			<li><a href="${ s:mvcUrl('planoUrl').build() }">Planos</a></li>
			<li><a href="${ s:mvcUrl('sobreUrl').build() }">Sobre a Empresa</a></li>
			<li><a href="${ s:mvcUrl('contatoUrl').build() }">Contato</a></li>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${ s:mvcUrl('loginUrl').build() }">Entrar ao Sistema</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a href="${ s:mvcUrl('listarProdutoUrl').build() }">Menu administrativo</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>

<ul class="sidenav" id="mobile-demo">
	<li><a href="${ s:mvcUrl('homeUrl').build() }">Inicio</a></li>
	<li><a href="${ s:mvcUrl('planoUrl').build() }">Planos</a></li>
	<li><a href="${ s:mvcUrl('sobreUrl').build() }">Sobre a Empresa</a></li>
	<li><a href="${ s:mvcUrl('contatoUrl').build() }">Contato</a></li>
	<li><hr></li>
	<sec:authorize access="!isAuthenticated()">
		<li><a href="${ s:mvcUrl('loginUrl').build() }">Entrar ao Sistema</a></li>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<li><a href="${ s:mvcUrl('loginUrl').build() }">Menu administrativo</a></li>
	</sec:authorize>
</ul>