package ec.edu.ups.app.g1.examen.negocio;



import java.util.List;


import ec.edu.ups.app.g1.examen.modelo.Cliente;


public interface GestionClientesONRemoto {
	public boolean registrarCliente(Cliente cliente) throws Exception;
	public List<Cliente> listarClienteDNI(String dni);
}
