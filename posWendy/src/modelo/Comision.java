package modelo;

import java.math.BigDecimal;

public class Comision {
	private int codigo=0;
	private String vendedorNombre="";
	private int codigoVendedor=0;
	private Integer clienteAtendidos=0;
	private String fecha1="";
	private String fecha2="";
	private double comision=0.0;
	private double totalVentas=0.0;
	private double porcentaje=0.0;
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the vendedorNombre
	 */
	public String getVendedorNombre() {
		return vendedorNombre;
	}
	/**
	 * @param vendedorNombre the vendedorNombre to set
	 */
	public void setVendedorNombre(String vendedorNombre) {
		this.vendedorNombre = vendedorNombre;
	}
	/**
	 * @return the codigoVendedor
	 */
	public int getCodigoVendedor() {
		return codigoVendedor;
	}
	/**
	 * @param codigoVendedor the codigoVendedor to set
	 */
	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
	/**
	 * @return the fecha1
	 */
	public String getFecha1() {
		return fecha1;
	}
	/**
	 * @param fecha1 the fecha1 to set
	 */
	public void setFecha1(String fecha1) {
		this.fecha1 = fecha1;
	}
	/**
	 * @return the fecha2
	 */
	public String getFecha2() {
		return fecha2;
	}
	/**
	 * @param fecha2 the fecha2 to set
	 */
	public void setFecha2(String fecha2) {
		this.fecha2 = fecha2;
	}
	/**
	 * @return the comision
	 */
	public double getComision() {
		return comision;
	}
	/**
	 * @param comision the comision to set
	 */
	public void setComision(double c) {
	
		comision=c;
	}
	/**
	 * @return the porcentaje
	 */
	public double getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje the porcentaje to set
	 */
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	/**
	 * @return the clienteAtendidos
	 */
	public Integer getClienteAtendidos() {
		return clienteAtendidos;
	}
	/**
	 * @param clienteAtendidos the clienteAtendidos to set
	 */
	public void setClienteAtendidos(Integer c) {
		clienteAtendidos = clienteAtendidos+c;
	}
	/**
	 * @return the totalVentas
	 */
	public double getTotalVentas() {
		return totalVentas;
	}
	/**
	 * @param totalVentas the totalVentas to set
	 */
	public void setTotalVentas(double t) {
		this.totalVentas = totalVentas + t;
	}
	
	public void resetTotales(){
		this.comision=0;
		this.porcentaje=0;
		this.totalVentas=0;
		clienteAtendidos=0;
	}
	
	
	public void calcularComision() {
		// TODO Auto-generated method stub
			setComision((getPorcentaje()/100)*totalVentas);
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comision [codigo=" + codigo + ", vendedorNombre=" + vendedorNombre + ", codigoVendedor="
				+ codigoVendedor + ", clienteAtendidos=" + clienteAtendidos + ", fecha1=" + fecha1 + ", fecha2="
				+ fecha2 + ", comision=" + comision + ", totalVentas=" + totalVentas + ", porcentaje=" + porcentaje
				+ "]";
	}
	

}
