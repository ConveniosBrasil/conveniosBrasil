<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<footer class="page-footer blue lighten-5">
	<div class="container-fluid">
		<div class="row footer-dados">
			<div class="col l4 s12">
				<ul class="lista-footer">
                    <li><a href="${ s:mvcUrl('homeUrl').build() }">Inicio</a></li>
                    <li><a href="${ s:mvcUrl('planoUrl').build() }">Planos</a></li>
                    <li><a href="${ s:mvcUrl('sobreUrl').build() }">Sobre a Empresa</a></li>
                    <li><a href="${ s:mvcUrl('contatoUrl').build() }">Contato</a></li>
                    <sec:authorize access="!isAuthenticated()">
						<li><a href="${ s:mvcUrl('loginUrl').build() }">Entrar ao Sistema</a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li><a href="${ s:mvcUrl('listarFornecedorUrl').build() }">Menu administrativo</a></li>
					</sec:authorize>
                </ul>
			</div>
			<div class="col l4 s12">
				Convênios Brasil<br>
                CNPJ: 65.587.884/0001-78<br>
                Endereço: R. Cel. Fernando Prestes, 326<br> 
                Centro, Santo André - SP, CEP: 09020-110<br> 
			</div>
			<div class="col l4 s12">
				<img src="/img/phone.png" class="phone" alt="Telefone"> (11) 4972-6523 <br>
                <img src="/img/whatsapp-icone.png" class="whatsapp" alt="Whatsapp"> (11) 96658-9455
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container center">
				<a href="${ s:mvcUrl('politicaPrivacidadeUrl').build() }">Politica de Privacidade</a> | Copyright &copy; <label class="anoAtualRodape"></label> Tecnovação - <a href="${ s:mvcUrl('homeUrl').build() }">Convênios Brasil</a>
	            <img id="voltarTopo" class="right" src="/img/imgTopo.png" title="Voltar ao topo" />
			</div>
		</div>
	</div>
</footer>