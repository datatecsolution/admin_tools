package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.botones.BotonActualizar;
import view.botones.BotonCancelar;
import view.botones.BotonGuardar;
import view.rendes.PanelPadre;
import controlador.CtlCliente;

import java.awt.Color;

import javax.swing.JPanel;

public class ViewCrearCliente extends JDialog{
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtMovil;
	private JTextField txtRtn;
	
	private BotonCancelar btnCancelar;
	private BotonActualizar btnActualizar;
	private BotonGuardar btnGuardar;
	private JTextField txtVendedor;
	
	public ViewCrearCliente() {
		
	
	
		
		setTitle("Crear Cliente");
		
		this.setSize(350,464);
		getContentPane().setLayout(null);
		
		JPanel JplPrincipal = new PanelPadre();
		JplPrincipal.setBounds(0, 0, 349, 428);
		getContentPane().add(JplPrincipal);
		JplPrincipal.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(19, 4, 60, 14);
		JplPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(19, 22, 311, 32);
		JplPrincipal.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(19, 58, 64, 14);
		JplPrincipal.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(19, 76, 311, 32);
		JplPrincipal.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(19, 112, 60, 14);
		JplPrincipal.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(19, 130, 311, 32);
		JplPrincipal.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblMovil = new JLabel("Movil:");
		lblMovil.setBounds(19, 166, 64, 14);
		JplPrincipal.add(lblMovil);
		
		txtMovil = new JTextField();
		txtMovil.setBounds(19, 184, 311, 32);
		JplPrincipal.add(txtMovil);
		txtMovil.setColumns(10);
		
		JLabel lblRtn = new JLabel("RTN:");
		lblRtn.setBounds(19, 220, 60, 14);
		JplPrincipal.add(lblRtn);
		
		txtRtn = new JTextField();
		txtRtn.setBounds(19, 238, 311, 32);
		JplPrincipal.add(txtRtn);
		txtRtn.setColumns(10);
		
		// botones de accion
		btnCancelar = new BotonCancelar();
		btnCancelar.setLocation(180, 345);
		JplPrincipal.add(btnCancelar);
		
		btnGuardar = new BotonGuardar();
		btnGuardar.setLocation(19, 345);
		JplPrincipal.add(btnGuardar);
		
		btnActualizar=new BotonActualizar();
		btnActualizar.setLocation(19, 345);
		JplPrincipal.add(btnActualizar);
		
		JLabel lblVendedor = new JLabel("F1 Vendedor");
		lblVendedor.setBounds(19, 275, 119, 14);
		JplPrincipal.add(lblVendedor);
		
		txtVendedor = new JTextField();
		txtVendedor.setColumns(10);
		txtVendedor.setBounds(19, 290, 311, 32);
		JplPrincipal.add(txtVendedor);
		btnActualizar.setVisible(false);
		
		
	}
	public JTextField getTxtNombre(){
		return txtNombre;
	}
	public  JTextField getTxtDireccion(){
		return  txtDireccion;
	}
	public JTextField getTxtTelefono(){
		return txtTelefono;
	}
	public JTextField getTxtMovil(){
		return txtMovil;
	}
	public JTextField getTxtRtn(){
		return txtRtn;
	}
	public BotonActualizar getBtnActualizar(){
		return btnActualizar;
	}
	public BotonGuardar getBtnGuardar(){
		return btnGuardar;
	}
	public void conectarControlador(CtlCliente c){
		
		btnCancelar.addActionListener(c);
		btnCancelar.setActionCommand("CANCELAR");
		
		btnGuardar.addActionListener(c);
		btnGuardar.setActionCommand("GUARDAR");
		
		btnActualizar.addActionListener(c);
		btnActualizar.setActionCommand("ACTUALIZAR");
		
		btnGuardar.addKeyListener(c);
		btnCancelar.addKeyListener(c);
		txtNombre.addKeyListener(c);
		txtDireccion.addKeyListener(c);
		txtTelefono.addKeyListener(c);
		txtMovil.addKeyListener(c);
		txtMovil.addKeyListener(c);
		txtVendedor.addKeyListener(c);

	}
	public void configActualizar() {
		// TODO Auto-generated method stub
		this.btnActualizar.setVisible(true);
		this.btnGuardar.setVisible(false);
		
	}
	/**
	 * @return the txtVendedor
	 */
	public JTextField getTxtVendedor() {
		return txtVendedor;
	}
}
