<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Productos</title>
<style type="text/css">
	.cabecera{
		background-color: #3349FF;
		color:white;
		font-size: 1.2em;
		font-weight: bold;
		text-align: center;
	}
	.fila{
		background-color: #EDFEFD;
		text-align: center;
	}
	table{
		float: left;
	}
	#contenedorBotones{
		margin-left: 900px;
	}
</style>
</head>
<body>
    <table>
	<tr class="cabecera">
		<th> Código Artículo </th>
		<th> Sección </th>
		<th> Nombre Artículo </th>
		<th> Fecha </th>
		<th> Precio </th>
		<th> Importado </th>
		<th> Pais de Origen </th>
		<th> Acción </th>
	</tr>
	<c:forEach var="tempProd" items="${lista_productos}">
		<!-- Link para actualizar producto con su campo clave -->
		<c:url var="linkActualiza" value="ControladorProductos">
			<c:param name="instruccion" value="mostrarBBDD"></c:param>	
			<c:param name="cod_art_mostrar" value="${ tempProd.codArticulo }"></c:param>
		</c:url>
		<!-- Link para eliminar producto con su campo clave -->
		<c:url var="linkElimina" value="ControladorProductos">
			<c:param name="instruccion" value="eliminarBBDD"></c:param>	
			<c:param name="cod_art_eliminar" value="${ tempProd.codArticulo }"></c:param>
		</c:url>
		<tr class="fila">
			<td>${ tempProd.codArticulo }</td>
			<td>${ tempProd.seccion }</td>
			<td>${ tempProd.nombreArticulo }</td>
			<td>${ tempProd.fecha }</td>
			<td>${ tempProd.precio }</td>
			<td>${ tempProd.importado }</td>
			<td>${ tempProd.paisDeOrigen }</td>
			<td><a href="${linkActualiza}">Actualizar</a> &nbsp; &nbsp; <a href="${linkElimina}">Eliminar</a></td>
		</tr> 
	</c:forEach>
	</table>
	<div id="contenedorBotones">
		<input type="button" value="Insertar Registro" onclick="window.location.href='InsertaProducto.jsp'"/>
	</div>
	
</body>
</html>