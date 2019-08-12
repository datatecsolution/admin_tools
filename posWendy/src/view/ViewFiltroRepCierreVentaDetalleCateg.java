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
import controlador.CtlFiltroRepCierreVentasDetalleCateg;
import modelo.Caja;
import modelo.Categoria;
import view.rendes.PanelPadre;
import view.tablemodel.CbxTmCajas;
import view.tablemodel.CbxTmCategoria;

public class ViewFiltroRepCierreVentaDetalleCateg extends  JDialog {
	private JButton btnBuscar;
	private JComboBox<Categoria> cbxCajas;
	private CbxTmCategoria modeloListaCajas;

	public ViewFiltroRepCierreVentaDetalleCateg(Window view) {
		
		super(view,"Filtro reporte caja categoria",Dialog.ModalityType.DOCUMENT_MODAL);
		getContentPane().setLayout(null);
		getContentPane().setBackground(PanelPadre.color1);
		
		btnBuscar = new JButton("Ver Reporte");
		btnBuscar.setBounds(69, 61, 168, 49);
		getContentPane().add(btnBuscar);
		
		
		modeloListaCajas=new CbxTmCategoria();//comentar para poder mostrar en forma de diseno la ventana
		modeloListaCajas.agregar(new Categoria());
		
		
		cbxCajas = new JComboBox<Categoria>(modeloListaCajas);
		cbxCajas.setBounds(16, 19, 275, 30);
		getContentPane().add(cbxCajas);
		
		JLabel lblCaja = new JLabel("Categoria");
		lblCaja.setBounds(16, 4, 61, 16);
		getContentPane().add(lblCaja);
		
		this.setPreferredSize(new Dimension(212, 264));
		this.setSize(305, 161);
		// TODO Auto-generated constructor stub
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	
	public void conectarCtl(CtlFiltroRepCierreVentasDetalleCateg c){
		btnBuscar.addActionListener( c);
		btnBuscar.setActionCommand("GENERAR");
	}
	/**
	 * @return the cbxCajas
	 */
	public JComboBox<Categoria> getCbxCategorias() {
		return cbxCajas;
	}
	/**
	 * @return the modeloListaCajas
	 */
	public CbxTmCategoria getModeloListaCategorias() {
		return modeloListaCajas;
	}
}
