package com.adatasoft.productos;

import java.util.Date;

public class Productos {
	
	private String codArticulo;
	private String seccion;
	private String nombreArticulo;
	private Double precio;
	private Date   fecha;
	private String importado;
	private String paisDeOrigen;
	
	public Productos(String codArticulo, String seccion, String nombreArticulo, Double precio, Date fecha,
			String importado, String paisDeOrigen) {

		this.codArticulo = codArticulo;
		this.seccion = seccion;
		this.nombreArticulo = nombreArticulo;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.paisDeOrigen = paisDeOrigen;
	}

	public Productos(String seccion, String nombreArticulo, Double precio, Date fecha, String importado,
			String paisDeOrigen) {
		this.seccion = seccion;
		this.nombreArticulo = nombreArticulo;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.paisDeOrigen = paisDeOrigen;
	}

	@Override
	public String toString() {
		return "Productos [codArticulo=" + codArticulo + ", seccion=" + seccion + ", nombreArticulo=" + nombreArticulo
				+ ", precio=" + precio + ", fecha=" + fecha + ", importado=" + importado + ", paisDeOrigen="
				+ paisDeOrigen + "]";
	}

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getImportado() {
		return importado;
	}

	public void setImportado(String importado) {
		this.importado = importado;
	}

	public String getPaisDeOrigen() {
		return paisDeOrigen;
	}

	public void setPaisDeOrigen(String paisDeOrigen) {
		this.paisDeOrigen = paisDeOrigen;
	}

}
