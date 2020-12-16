<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar Producto</title>
<style>
	table{
		background:#EDFEFD;
		padding:10px;
		border:solid 2px #3349FF;
	}
	td{
		padding:5px 0;
	}
</style>
</head>
<body>
	<h1 style="text-align:center">Actualizar Producto</h1>
	<form name="form_producto" method="get" action="ControladorProductos">
		<input type="hidden" name="instruccion" value="actualizarBBDD">
		<input type="hidden" name="cod_art_actualizar" value="${ producto_actualizar.codArticulo }">
		<table>
			<!-- No debe mostrar el codigo del articulo 
			<tr>
				<td><label for="codigoArticulo">Código Artículo</label></td> 
				<td><input type="text" name="codArticulo" id="codigoArticulo" value="${ producto.codigoArticulo }"></td>
			</tr>
			 -->
			<tr>
				<td><label for="seccion">Sección</label></td>                
				<td><input type="text" name="seccion" id="seccion" value="${ producto_actualizar.seccion }"></td>
			</tr>
			<tr>
				<td><label for="nombreArticulo">Nombre Artículo</label></td>
				<td><input type="text" name="nombreArticulo" id="nombreArticulo" value="${ producto_actualizar.nombreArticulo }"></td>
			</tr>
			<tr>
				<td><label for="fecha">Fecha</label></td>                    
				<td><input id="fecha" type="date" name="fecha" value="${ producto_actualizar.fecha }"></td>
			</tr>
			<tr>
				<td><label for="precio">Precio</label></td>                  
				<td><input type="number" name="precio" id="precio" value="${ producto_actualizar.precio }"></td>
			</tr>
			<tr>
				<td><label for="importado">Importado</label></td>            
				<td><input type="text" name="importado" id="importado" value="${ producto_actualizar.importado }"></td>
			</tr>
			<tr>
				<td><label for="paisDeOrigen">Pais de Origen</label></td>    
				<td><input type="text" name="paisDeOrigen" id="paisDeOrigen" value="${ producto_actualizar.paisDeOrigen }"></td>
			</tr>
			<tr>
				<td><input type="submit" id="envio" name="envio" value="Actualizar"></td>
				<td><input type="reset"  id="borrar" name="borrar" value="Reestablecer"></td>
			</tr>
		</table>
	</form>
</body>
</html>