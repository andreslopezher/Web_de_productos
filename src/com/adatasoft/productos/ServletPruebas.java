package com.adatasoft.productos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.sql.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * Servlet implementation class ServletPruebas
 * 
 */


@WebServlet("/ServletPruebas")

public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		dataSource = getPool();
		
        PrintWriter salida = response.getWriter();
		
		response.setContentType("text/plain");

        Connection con = null;
        
        try {
        
        	con = dataSource.getConnection();
        	Statement st = con.createStatement();
        	ResultSet rs = st.executeQuery("select * from productos");
        	
        	while (rs.next()) {
        		salida.println(rs.getString(3));
        	}
        	
        	rs.close();
        	st.close();
        
        } catch(Exception e){
        
        	e.printStackTrace();
        
        }finally {
        
        	if (con!=null) try {con.close();}catch (Exception ignore) {}
        
        }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
