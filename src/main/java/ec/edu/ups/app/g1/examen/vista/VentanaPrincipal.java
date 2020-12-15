package ec.edu.ups.app.g1.examen.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ec.edu.ups.app.g1.examen.modelo.Cliente;
import ec.edu.ups.app.g1.examen.negocio.GestionClientesONRemoto;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField textFecha;
	private GestionClientesONRemoto on;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
					frame.referenciarONCliente();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		setTitle("VentanaPrincipal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(41, 49, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(41, 85, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setBounds(41, 127, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_3.setBounds(41, 175, 116, 14);
		contentPane.add(lblNewLabel_3);
		
		txtDni = new JTextField();
		txtDni.setBounds(242, 46, 86, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(242, 82, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(242, 124, 86, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(242, 172, 86, 20);
		contentPane.add(textFecha);
		textFecha.setColumns(10);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente();
				c.setDni(txtDni.getText());
				c.setNombre(txtNombre.getText());
				c.setApellido(txtApellido.getText());
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				try {
					c.setFechaNacimiento(sdf.parse(textFecha.getText()));
					System.out.println(sdf.parse(textFecha.getText()));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					on.registrarCliente(c);
					JOptionPane.showMessageDialog(null, "Se guardó correctamente");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error al guardar " + e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(239, 265, 89, 23);
		contentPane.add(btnNewButton);
	}
	protected void referenciarONCliente() throws Exception {
		// TODO Auto-generated method stub
		try {  
            final Hashtable<String, Comparable> jndiProperties =  new Hashtable<String, Comparable>();  
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");  
            jndiProperties.put("jboss.naming.client.ejb.context", true);  
            jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");  
            jndiProperties.put(Context.SECURITY_PRINCIPAL, "examen");  
            jndiProperties.put(Context.SECURITY_CREDENTIALS, "examen");  
            final Context context = new InitialContext(jndiProperties);  
            final String lookupName = "ejb:/ExamenGuaichaAndresServidor/GestionClientesON!ec.edu.ups.app.g1.examen.negocio.GestionClientesONRemoto";  
            this.on = (GestionClientesONRemoto) context.lookup(lookupName);  
        } catch (Exception ex) {  
            ex.printStackTrace();  
            throw ex;  
        } 
	}
}
