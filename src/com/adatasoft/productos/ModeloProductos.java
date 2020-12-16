package com.adatasoft.productos;

import java.sql.*;
import java.util.*;
//import java.util.Date;

import javax.sql.DataSource;

public class ModeloProductos {
	
	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos) {
		
		this.origenDatos = origenDatos;

	}
	
	public List<Productos> getProductos() throws Exception{
		
		List<Productos> productos = new ArrayList<>();
		
		//Establece conexion con la BBDD
		Connection cn = origenDatos.getConnection();
		
		//Crear instruccion Sql y Statement
		String sql = "SELECT * FROM PRODUCTOS";
		Statement st = cn.createStatement();
		
		//Ejecutar instrucción Sql
		ResultSet rs = st.executeQuery(sql);
		
		//Recorrer el result set
		while (rs.next()) {
			
			Productos prod = new Productos(rs.getString("codarticulo")
				                          ,rs.getString("seccion")
					                      ,rs.getString("nombrearticulo")
					                      ,rs.getDouble("precio")
					                      ,rs.getDate("fecha")
					                      ,rs.getString("importado")
					                      ,rs.getString("paisdeorigen"));			
			productos.add(prod);

		}

		//Cerrar la conexión
		rs.close();
		st.close();
		cn.close();
		
		return productos;

	}
	
	public Productos getProducto(String codArticulo) throws Exception{

		String codigo = codArticulo;
		Productos producto = null;
		
		//Establece conexion con la BBDD
		Connection cn = origenDatos.getConnection();
		
		//Crear Statement
		String sql = "SELECT * FROM PRODUCTOS WHERE codarticulo=?";
			
		PreparedStatement st = cn.prepareStatement(sql);
		st.setString(1, codigo);
		
		//Ejecutar instrucción Sql
		ResultSet rs = st.executeQuery();
		
		//Conversion de Fecha paquete util a date paquete sql
		java.util.Date utilDate = null;
		java.sql.Date  sqlDate  = null; 

		while(rs.next()) {
			utilDate = rs.getDate("fecha");
			sqlDate  = new java.sql.Date(utilDate.getTime()); 
			producto = new Productos(rs.getString("codarticulo")
					                ,rs.getString("seccion")
				                    ,rs.getString("nombrearticulo")
				                    ,rs.getDouble("precio")
				                    ,sqlDate
				                    ,rs.getString("importado")
				                    ,rs.getString("paisdeorigen"));
		}
		
		//Cerrar la conexión
		rs.close();
		st.close();
		cn.close();
		
		return producto;
		
	}
	
	public void insertar(Productos producto) throws SQLException{

		//Establece conexion con la BBDD
		Connection cn = origenDatos.getConnection();
		
		//Crear instruccion Sql
		String sql = "INSERT INTO PRODUCTOS(codarticulo,seccion,nombrearticulo,precio,fecha,importado,paisdeorigen)" 
				   +" VALUES(?,?,?,?,?,?,?)";
		
		//Conversion de Fecha paquete util a date paquete sql
		java.util.Date utilDate = producto.getFecha();
		java.sql.Date  sqlDate  = new java.sql.Date(utilDate.getTime()); 
		
		//Crear Statement
		PreparedStatement st = cn.prepareStatement(sql);
		st.setString(1,producto.getCodArticulo());
		st.setString(2,producto.getSeccion());
		st.setString(3,producto.getNombreArticulo());
		st.setDouble(4,producto.getPrecio());
		st.setDate  (5,sqlDate);
		st.setString(6,producto.getImportado());
		st.setString(7,producto.getPaisDeOrigen());

		//Ejecutar instrucción Sql
		st.execute();	

		//Cerrar conexion
		st.close();
		cn.close();

	}
	
	public void actualizar(Productos producto) throws SQLException{
	
		//Establece conexion con la BBDD
		Connection cn = origenDatos.getConnection();
		
		//Conversion de Fecha paquete util a date paquete sql
		java.util.Date utilDate = producto.getFecha();
		java.sql.Date  sqlDate  = new java.sql.Date(utilDate.getTime()); 
		
		
		//Crear instruccion Sql		
		String sql = "UPDATE PRODUCTOS SET"
		           +" seccion = ?"
				   +",nombrearticulo =?"
		           +",precio = ?"
		           +",fecha = ?"
				   +",importado = ?"
		           +",paisdeorigen = ?"
				   +" WHERE codarticulo = ?";


		//Crear Statement
		PreparedStatement st = cn.prepareStatement(sql);
		st.setString(1, producto.getSeccion());
		st.setString(2, producto.getNombreArticulo());
		st.setDouble(3, producto.getPrecio());
		st.setDate  (4, sqlDate);
		st.setString(5, producto.getImportado());
		st.setString(6, producto.getPaisDeOrigen());
		st.setString(7, producto.getCodArticulo());
		
		//Ejecutar instrucción Sql
		st.executeUpdate();
		
		//Cerrar conexion
		st.close();
		cn.close();
	
	}
	
	public void eliminar(String codArticulo) throws SQLException{

		//Establece conexion con la BBDD
		Connection cn = origenDatos.getConnection();
		
		//Crear instruccion Sql
		String sql = "DELETE FROM PRODUCTOS WHERE codarticulo = ?";


		//Crear Statement
		PreparedStatement st = cn.prepareStatement(sql);
		st.setString(1, codArticulo);
		
		//Ejecutar instrucción Sql
		st.execute();

		//Cerrar la conexión
		st.close();
		cn.close();
		
	}

}
