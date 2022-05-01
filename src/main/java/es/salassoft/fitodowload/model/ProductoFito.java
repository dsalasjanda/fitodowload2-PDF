package es.salassoft.fitodowload.model;

public class ProductoFito {
	
	private String nRegistro;
	private String nombreComercial;	
	private String titular;
	private String formulado;
	

	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getFormulado() {
		return formulado;
	}
	public void setFormulado(String formulado) {
		this.formulado = formulado;
	}
	public String getnRegistro() {
		return nRegistro;
	}
	public void setnRegistro(String nRegistro) {
		this.nRegistro = nRegistro;
	}

}
