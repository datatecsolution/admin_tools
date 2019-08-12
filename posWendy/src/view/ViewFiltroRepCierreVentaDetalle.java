package view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import com.toedter.calendar.JMonthChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JYearChooser;

import controlador.CtlFiltroRepSarVentas;
import controlador.CtlFiltroRepCierreVentasDetalle;
import modelo.Caja;
import view.rendes.PanelPadre;
import view.tablemodel.CbxTmCajas;

public class ViewFiltroRepCierreVentaDetalle extends  JDialog {
	private JButton btnBuscar;
	private JComboBox<Caja> cbxCajas;
	private CbxTmCajas modeloListaCajas;

	public ViewFiltroRepCierreVentaDetalle(Window view) {
		
		super(view,"Filtro reporte CAJA",Dialog.ModalityType.DOCUMENT_MODAL);
		getContentPane().setLayout(null);
		getContentPane().setBackground(PanelPadre.color1);
		
		btnBuscar = new JButton("Ver Reporte");
		btnBuscar.setBounds(69, 61, 168, 49);
		getContentPane().add(btnBuscar);
		
		
		modeloListaCajas=new CbxTmCajas();//comentar para poder mostrar en forma de diseno la ventana
		modeloListaCajas.agregar(new Caja());
		
		
		cbxCajas = new JComboBox<Caja>(modeloListaCajas);
		cbxCajas.setBounds(16, 19, 275, 30);
		getContentPane().add(cbxCajas);
		
		JLabel lblCaja = new JLabel("Caja");
		lblCaja.setBounds(16, 4, 61, 16);
		getContentPane().add(lblCaja);
		
		this.setPreferredSize(new Dimension(212, 264));
		this.setSize(305, 161);
		// TODO Auto-generated constructor stub
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	
	public void conectarCtl(CtlFiltroRepCierreVentasDetalle c){
		btnBuscar.addActionListener( c);
		btnBuscar.setActionCommand("GENERAR");
	}
	/**
	 * @return the cbxCajas
	 */
	public JComboBox<Caja> getCbxCajas() {
		return cbxCajas;
	}
	/**
	 * @return the modeloListaCajas
	 */
	public CbxTmCajas getModeloListaCajas() {
		return modeloListaCajas;
	}
}
