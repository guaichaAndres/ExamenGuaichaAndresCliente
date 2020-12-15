package ec.edu.ups.app.g1.examen.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue
	@Column(name="cliente_id")
	private int codigo;
	@OneToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<CuentaBancaria> listaCuenta;
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CuentaBancaria> getListaCuenta() {
		return listaCuenta;
	}

	public void setListaCuenta(List<CuentaBancaria> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}

}
