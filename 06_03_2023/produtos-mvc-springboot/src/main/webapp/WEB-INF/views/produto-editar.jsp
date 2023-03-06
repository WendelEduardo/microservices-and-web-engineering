<%@page import="br.com.fiap.model.ProdutoModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- ATALHOS PARA OS ARQUIVOS ESTATICOS DO WEBJAR -->
<spring:url value="${contextPath}/webjars/bootstrap/5.2.3/css" var="css" />
<spring:url value="${contextPath}/webjars/jquery" var="jquery" />
<spring:url value="${contextPath}/webjars/bootstrap/js" var="js" />

<!-- APONTAMENTO PARA O CSS DO BOOTSTRAP -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- CSS PARA NOSAS CUSTOMIZACOES -->
<link href="/css/style.css" rel="stylesheet">


<!-- LINKS PARA USAR FONTE CUSTOMIZAVEL DO GOOGLE FONTES -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
	rel="stylesheet">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand fonte-titulo" href="${contextPath}/produtos">Produtos</a>
			</div>
		</nav>
	</header>

	<main>
		<section id="formulario" class="bg-light pb-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="well">

							<h2 class="fonte-titulo text-danger">Produto</h2>

							<form action="${contextPath}/produto/update" method="POST">

								<div class="form-group d-none">
									<label class="control-label" for="identificador">ID:</label> <input
										type="text" name="identificador" id="identificador" value="${produto.id}"
										class="form-control" maxlength="50" size="50" required />
								</div>

								<div class="form-group">
									<label class="control-label" for="nome">Nome:</label> <input
										type="text" name="nome" id="nome" value="${produto.nome}"
										class="form-control" maxlength="50" size="50" required />
								</div>
								<div class="form-group">
									<label class="control-label" for="sku">SKU:</label> <input
										type="text" value="${produto.sku}" name="sku" id="sku" class="form-control"
										maxlength="50" size="50" required />
								</div>

								<div class="form-group">
									<label class="control-label" for="descricao">Descrição:</label>
									<textarea class="form-control" name="descricao" id="descricao"
										rows="4" cols="100" required>${produto.descricao}</textarea>
								</div>

								<div class="form-group">
									<label class="control-label" for="preco">Preço:</label> <input
										type="number" id="preco" value="${produto.preco}" name="preco" step=".01"
										class="form-control" required />
								</div>

								<div class="form-group">
									<label class="control-label" for="caracteristica">Características:</label>
									<textarea id="caracteristica" class="form-control"
										name="caracteristicas" rows="4" cols="100" required>${produto.caracteristicas}</textarea>
								</div>
								<hr>

								<a class="btn btn-secondary btn-sm"
									href="${contextPath}/produtos">Cancelar</a>
								<button type="submit" class="btn btn-primary btn-sm">Gravar</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>

	<script src="${jquery}/jquery.min.js"></script>
	<script src="${js}/bootstrap.min.js"></script>

</body>
</html>