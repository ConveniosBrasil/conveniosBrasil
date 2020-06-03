<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<footer class="page-footer blue lighten-5">
	<div class="container-fluid">
		<div class="row footer-dados">
			<div class="col l2 s12">
				<ul class="lista-footer">
                    <li><a href="${ s:mvcUrl('listarUsuarioUrl').build() }">Usu�rios</a></li>
                    <li><a href="${ s:mvcUrl('listarFornecedorUrl').build() }">Fornecedores</a></li>
                    <li><a href="${ s:mvcUrl('listarCorretorUrl').build() }">Corretores</a></li>
                    <li><a href="${ s:mvcUrl('listarProdutoUrl').build() }">Produtos</a></li>
                </ul>
			</div>
			<div class="col l2 s12">
				<ul class="lista-footer">
					<li><a href="#">Pedido</a></li>
                    <li><a href="#">Estoque</a></li>
                    <li><a href="${ s:mvcUrl('homeUrl').build() }">Inicio</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
                </ul>
			</div>
			<div class="col l4 s12">
				Conv�nios Brasil<br>
                CNPJ: 65.587.884/0001-78<br>
                Endere�o: R. Cel. Fernando Prestes, 326<br> 
                Centro, Santo Andr� - SP, CEP: 09020-110<br>
			</div>
			<div class="col l4 s12">
				<img src="/img/phone.png" class="phone" alt="Telefone"> (11) 4972-6523 <br>
                <img src="/img/whatsapp-icone.png" class="whatsapp" alt="Whatsapp"> (11) 96658-9455
			</div>
		</div>
		<div class="footer-copyright">
			<div class="container center">
				<a href=${ s:mvcUrl('politicaPrivacidadeUrl').build() }>Politica de Privacidade</a> | Copyright &copy; <label class="anoAtualRodape"></label> Tecnova��o - <a href="${ s:mvcUrl('homeUrl').build() }">Conv�nios Brasil</a>
	            <img id="voltarTopo" class="right" src="/img/imgTopo.png" title="Voltar ao topo" />
			</div>
		</div>
	</div>
</footer>