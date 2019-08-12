package modelo;

import java.math.BigDecimal;

public class CuentaXCobrarFactura {
	
	private int noReguistro=-1;
	private String fecha="";
	private int codigoCuenta=-1;
	private String descripcion="";
	private BigDecimal saldo=new BigDecimal(0.0);
	private BigDecimal credito=new BigDecimal(0.0);
	private BigDecimal debito=new BigDecimal(0.0);
	

	public CuentaXCobrarFactura() {
		// TODO Auto-generated constructor stub
	}
	public void resetTotales(){
		saldo=BigDecimal.ZERO;
		credito=BigDecimal.ZERO;
		debito=BigDecimal.ZERO;
	}
	
	public void setCredito(BigDecimal t){
		credito=credito.add(t);
	}
	public BigDecimal getCredito(){
		return credito;
	}
	
	public void setDebito(BigDecimal t){
		debito=debito.add(t);
	}
	public BigDecimal getDebito(){
		return debito;
	}
	
	public void setSaldo(BigDecimal t){
		saldo=saldo.add(t);
	}
	public BigDecimal getSaldo(){
		return saldo;
	}
	
	public void setDescripcion(String d){
		descripcion=d;
	}
	public String getDescripcion(){
		return descripcion;
	}
	public void setNoReguistro(int n){
		noReguistro=n;
	}
	public int getNoReguistro(){
		return noReguistro;
	}
	public void setFecha(String f){
		fecha=f;
	}
	public String getFecha(){
		return fecha;
	}
	
	
	public int getCodigoCuenta() {
		return codigoCuenta;
	}
	public void setCodigoCuenta(int codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

}
