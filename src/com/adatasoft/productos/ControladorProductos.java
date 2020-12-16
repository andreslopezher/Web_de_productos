package com.adatasoft.productos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * Servlet implementation class ControladorProductos
 */
@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ModeloProductos modeloProductos;
	
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("instruccion");
		
		if (accion == null) accion= "listarProductos";
		
		try {
		
			switch (accion) {
			case "insertarBBDD":
				
				insertarProducto(request, response);
				break;
				
			case "mostrarBBDD":
				
				mostrarProducto(request, response);
				break;
				
			case "actualizarBBDD":
				
				actualizarProducto(request, response);
				break;

			case "eliminarBBDD":
				
				eliminarProducto(request, response);
				break;
				
			default:
				
				listarProductos(request, response);
				break;
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}

	}

	@Override
	public void init() throws ServletException {

		super.init();
		
		try {

			dataSource = getPool();
			modeloProductos = new ModeloProductos(dataSource);
			
		} catch(Exception e) {
			
			throw new ServletException(e);
			
		}

	}

	private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			
			//Obtener lista de productos desde el modelo.
			List<Productos> productos = modeloProductos.getProductos();

			//Agregar lista de productos al requets.
			request.setAttribute("lista_productos", productos);
			
			//Enviar el request a la pagina JSP.
			RequestDispatcher rd = request.getRequestDispatcher("/ListaProductos.jsp");
			rd.forward(request, response);
		
		} catch(Exception e) {
			
			throw new ServletException(e);

		}
		
	}

	private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		
		try {
			
			//Leer la informacion del producto que viene del formulario.
			String codArticulo    = request.getParameter("codArticulo");
			String seccion        = request.getParameter("seccion");
			String nombreArticulo = request.getParameter("nombreArticulo");
			Double precio         = Double.parseDouble(request.getParameter("precio"));
			String fecha          = request.getParameter("fecha").replace("/", "-");
			String importado      = request.getParameter("importado");
			String paisDeOrigen   = request.getParameter("paisDeOrigen");
			
			//Convertir String a Date			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dfecha = formatter.parse(fecha);
			
			//Crear un objeto de tipo producto.
			Productos producto = new Productos(codArticulo,seccion,nombreArticulo,precio,dfecha,importado,paisDeOrigen);
			
			//Enviar el objeto al modelo para que inserte en la BBDD.
			modeloProductos.insertar(producto);

			//Redirigir a la pagina de listar productos para validar que fue registrado.
			listarProductos(request, response);
			
			
		} catch(Exception e) {
			
			throw new ServletException(e);
			
		}

	}
	
	private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			
			//Leer la informacion del producto que viene del formulario.
			String codArticulo    = request.getParameter("cod_art_actualizar");
			String seccion        = request.getParameter("seccion");
			String nombreArticulo = request.getParameter("nombreArticulo");
			Double precio         = Double.parseDouble(request.getParameter("precio"));
			String fecha          = request.getParameter("fecha").replace("/", "-");
			String importado      = request.getParameter("importado");
			String paisDeOrigen   = request.getParameter("paisDeOrigen");
			
			//Convertir String a Date			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dfecha = formatter.parse(fecha);
			
			//Crear un objeto de tipo producto.
			Productos producto = new Productos(codArticulo,seccion,nombreArticulo,precio,dfecha,importado,paisDeOrigen);
			
			//Enviar el objeto al modelo para que actualice en la BBDD.
			modeloProductos.actualizar(producto);

			//Redirigir a la pagina de listar productos para validar que fue actualizado.
			listarProductos(request, response);
			
			
		} catch(Exception e) {
			
			throw new ServletException(e);
			
		}
	
	}

	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		try {
		
			//Leer la informacion que viene por parametro.
			String codArticulo = request.getParameter("cod_art_eliminar");
			
			//Enviar el objeto al modelo para que elimine en la BBDD.
			modeloProductos.eliminar(codArticulo);

			//Redirigir a la pagina de listar productos para validar que fue eliminado.
			listarProductos(request, response);
			
		} catch(Exception e) {

			throw new ServletException(e);
			
		}
		
	}

	private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		try {
			
			//Leer la informacion del producto que viene por parametro.
			String codArticulo = request.getParameter("cod_art_mostrar");
			
			//Obtener la informacion del producto.
			Productos producto = modeloProductos.getProducto(codArticulo);
				
			//Enviar a la vista los campos del producto.
			request.setAttribute("producto_actualizar",producto);
			
			//Enviar el request a la pagina JSP.
			RequestDispatcher rd = request.getRequestDispatcher("/ActualizaProducto.jsp");
			rd.forward(request, response);	
	
		} catch(Exception e) {
			
			throw new ServletException(e);
			
		}

	}
	
	private DataSource getPool() {
		
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/pildoras_infor?useLegacyDatetimeCode=false&serverTimezone=UTC");
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("12345");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
          "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
          "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        
        DataSource ds = new DataSource();
        ds.setPoolProperties(p);
        
        return ds;

	}

}
