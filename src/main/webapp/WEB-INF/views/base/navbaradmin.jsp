<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<nav class="main-menu">
	<ul>
		<li><a href="/home"> <img class="logoAdm" src="/img/conveniosbrasil.png"></a></li>
		<li><a href="${ s:mvcUrl('perfilUsuarioUrl').build() }">
			<sec:authentication property="principal" var="usuario"/>
			<c:if test="${usuario.foto != null}">
				<img src="${ pageContext.request.contextPath }${usuario.foto}" class="imagemPerfilMenu left">
			</c:if>			
			<c:if test="${usuario.foto == null}">
				<img src="/img/user.png" class="imagemPerfilMenuSemFoto left">
			</c:if>
			<c:if test="${usuario.foto != null}">
				<span class="nav-text detalhesFotoMenu right">Detalhes do Usuário</span>
			</c:if>
			<c:if test="${usuario.foto == null}">
				<span class="nav-text detalhesMenuSemFoto">Detalhes do Usuário</span>
			</c:if>
		</a></li>
		<sec:authorize access="hasRole('ROLE_ADMIN')">			
			<li><a href="${ s:mvcUrl('listarUsuarioUrl').build() }"> 
				<i class="material-icons left">how_to_reg</i> <span class="nav-text">Usuarios</span></a>
			</li> 
			<li><a href="${ s:mvcUrl('listarPerfilUrl').build() }"> 
				<i class="material-icons left">transfer_within_a_station</i> <span class="nav-text">Perfil</span></a>
			</li> 
			<li><div class="divider"></div></li>
		</sec:authorize>
		<li><a href="${ s:mvcUrl('listarFornecedorUrl').build() }"> 
			<i class="material-icons left">people</i> <span class="nav-text">Fornecedores</span>
		</a></li>
		<li><a href="${ s:mvcUrl('listarCorretorUrl').build() }"> 
			<i class="material-icons left">folder_shared</i> <span class="nav-text">Corretores</span>
		</a></li>
		<li><a href="${ s:mvcUrl('listarProdutoUrl').build() }"> 
			<i class="material-icons left">widgets</i> <span class="nav-text">Produtos</span>
		</a></li>
		<li><a href="${ s:mvcUrl('listarPedidoCompraUrl').build() }"> 
			<i class="material-icons left">event_note</i> <span class="nav-text">Pedidos</span>
		</a></li>
		<li><a href="#"> 
			<i class="material-icons left">import_export</i> <span class="nav-text">Estoque</span>
		</a></li>
		<li><div class="divider"></div></li>
		<li><a href="${pageContext.request.contextPath}/logout"> 
			<i class="material-icons left">meeting_room</i> <span class="nav-text">Sair</span>
		</a></li>
	</ul>
</nav>