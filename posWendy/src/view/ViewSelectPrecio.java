package view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JDialog;

import view.botones.BotonAceptar;
import view.botones.BotonActualizar;
import view.botones.BotonCancelar;
import view.botones.BotonGuardar;
import view.rendes.PanelPadre;
import view.tablemodel.CbxTmDepartamento;
import view.tablemodel.CbxTmPrecios;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import controlador.CtlCaja;
import controlador.CtlSelectPrecio;
import modelo.PrecioArticulo;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ViewSelectPrecio extends JDialog {
	
	private BotonCancelar btnCancelar;
	private BotonAceptar btnGuardar;
	private JComboBox<PrecioArticulo> cbPrecios;
	private CbxTmPrecios modeloPrecioCb;
	private JCheckBox chckbxAplicarAToda;
	
	public ViewSelectPrecio(Window view){
		super(view,"Aplicar precio",Dialog.ModalityType.DOCUMENT_MODAL);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(PanelPadre.color1);
		this.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		
		
		
		btnCancelar = new BotonCancelar();
		btnCancelar.setSize(128, 78);
		btnCancelar.setLocation(166, 110);
		getContentPane().add(btnCancelar);
		
		btnGuardar = new BotonAceptar();	
		btnGuardar.setLocation(15, 111);
		getContentPane().add(btnGuardar);
		
		JLabel lblDescripcion = new JLabel("Eleja el precio que desea aplicar");
		lblDescripcion.setBounds(22, 6, 272, 16);
		getContentPane().add(lblDescripcion);
		
		modeloPrecioCb=new CbxTmPrecios();
		
		cbPrecios = new JComboBox<PrecioArticulo>();
		cbPrecios.setBounds(22, 25, 272, 40);
		cbPrecios.setModel(modeloPrecioCb);
		getContentPane().add(cbPrecios);
		
		chckbxAplicarAToda = new JCheckBox("Aplicar a toda la factura?");
		chckbxAplicarAToda.setSelected(true);
		chckbxAplicarAToda.setBounds(22, 70, 272, 23);
		getContentPane().add(chckbxAplicarAToda);
		
		this.setSize(309, 231);
		
		//centrar la ventana en la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	

	
	public void conectarControlador(CtlSelectPrecio c){
		
		btnGuardar.addActionListener(c);
		btnGuardar.setActionCommand("GUARDAR");
		btnGuardar.addKeyListener(c);
		
		
		this.btnCancelar.addActionListener(c);
		this.btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.addKeyListener(c);
		
	}

	/**
	 * @return the btnCancelar
	 */
	public BotonCancelar getBtnCancelar() {
		return btnCancelar;
	}


	/**
	 * @return the btnGuardar
	 */
	public BotonAceptar getBtnGuardar() {
		return btnGuardar;
	}




	/**
	 * @return the cbPrecios
	 */
	public JComboBox<PrecioArticulo> getCbPrecios() {
		return cbPrecios;
	}




	/**
	 * @param cbPrecios the cbPrecios to set
	 */
	public void setCbPrecios(JComboBox<PrecioArticulo> cbPrecios) {
		this.cbPrecios = cbPrecios;
	}




	/**
	 * @return the modeloPrecioCb
	 */
	public CbxTmPrecios getModeloPrecioCb() {
		return modeloPrecioCb;
	}




	/**
	 * @param modeloPrecioCb the modeloPrecioCb to set
	 */
	public void setModeloPrecioCb(CbxTmPrecios modeloPrecioCb) {
		this.modeloPrecioCb = modeloPrecioCb;
	}




	/**
	 * @return the chckbxAplicarAToda
	 */
	public JCheckBox getChckbxAplicarAToda() {
		return chckbxAplicarAToda;
	}
}
