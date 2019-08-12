package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JDialog;

import controlador.CtlBanco;
import view.botones.BotonActualizar;
import view.botones.BotonCancelar;
import view.botones.BotonGuardar;
import view.rendes.PanelPadre;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ViewCrearBanco extends JDialog {
	
	private BotonGuardar btnGuardar;
	private BotonCancelar btnCancelar;
	private BotonActualizar btnActualizar;
	private JTextField txtNombre;
	private JTextField txtNoCuenta;
	private JComboBox cbTipoCuenta;
	
	
	
	public ViewCrearBanco(Window view){
		
		this.setTitle("Crear Cuentas de Bancos");
		this.setLocationRelativeTo(view);
		this.setModal(true);
		
		this.setResizable(false);
		getContentPane().setBackground(PanelPadre.color1);
		getContentPane().setLayout(null);
		
		
		
		
		btnGuardar = new BotonGuardar();
		btnGuardar.setLocation(39, 204);
		getContentPane().add(btnGuardar);
		
		btnCancelar = new BotonCancelar();
		btnCancelar.setLocation(227, 204);
		getContentPane().add(btnCancelar);
		
		btnActualizar=new BotonActualizar();
		btnActualizar.setLocation(39, 204);
		getContentPane().add(btnActualizar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(17, 9, 136, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(17, 25, 376, 42);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNoDeCuenta = new JLabel("No de cuenta");
		lblNoDeCuenta.setBounds(17, 69, 115, 16);
		getContentPane().add(lblNoDeCuenta);
		
		txtNoCuenta = new JTextField();
		txtNoCuenta.setBounds(17, 86, 376, 42);
		getContentPane().add(txtNoCuenta);
		txtNoCuenta.setColumns(10);
		
		JLabel lblTipoDeCuenta = new JLabel("Tipo de Cuenta");
		lblTipoDeCuenta.setBounds(17, 129, 158, 16);
		getContentPane().add(lblTipoDeCuenta);
		
		cbTipoCuenta = new JComboBox();
		cbTipoCuenta.setModel(new DefaultComboBoxModel(new String[] {"NA", "Cuenta de ahorro", "Cuenta de cheque", "Cuenta moneda extranjera"}));
		cbTipoCuenta.setBounds(17, 150, 376, 42);
		getContentPane().add(cbTipoCuenta);
		
		this.setSize(423, 330);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
	}
	public void conectarCtl(CtlBanco c){
		
		btnGuardar.addActionListener(c);
		btnGuardar.setActionCommand("GUARDAR");
		
		btnCancelar.addActionListener(c);
		btnCancelar.setActionCommand("CANCELAR");
		
		btnActualizar.addActionListener(c);
		btnActualizar.setActionCommand("ACTUALIZAR");
		
	}
	/**
	 * @return the txtNombre
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	/**
	 * @return the txtNoCuenta
	 */
	public JTextField getTxtNoCuenta() {
		return txtNoCuenta;
	}

	/**
	 * @return the cbTipoCuenta
	 */
	public JComboBox getCbTipoCuenta() {
		return cbTipoCuenta;
	}
	/**
	 * @return the btnGuardar
	 */
	public BotonGuardar getBtnGuardar() {
		return btnGuardar;
	}
	/**
	 * @return the btnCancelar
	 */
	public BotonCancelar getBtnCancelar() {
		return btnCancelar;
	}
	/**
	 * @return the btnActualizar
	 */
	public BotonActualizar getBtnActualizar() {
		return btnActualizar;
	}
	
}
