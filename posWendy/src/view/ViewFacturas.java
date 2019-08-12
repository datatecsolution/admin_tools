package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
 







import controlador.CtlFacturas;
import modelo.Caja;

import javax.swing.JLabel;

import view.botones.BotonAgregar;
import view.botones.BotonBuscar;
import view.botones.BotonDevolucion;
import view.botones.BotonEliminar;
import view.botones.BotonImprimirSmall;
import view.rendes.PanelPadre;
import view.rendes.RenderizadorTablaFacturas;
import view.tablemodel.CbxTmCajas;
import view.tablemodel.TablaModeloFacturados;
import com.toedter.calendar.JDateChooser;

public class ViewFacturas extends ViewTabla {
	
	
	protected BotonImprimirSmall btnImprimir;
	
	
	
	
	private JComboBox<Caja> cbxCajas;
	private CbxTmCajas modeloListaCajas;
	
	
	private TablaModeloFacturados modelo;


	private BotonDevolucion btnDevolucion;
	private JRadioButton rdbtnCliente;
	

	public JRadioButton getRdbtnCliente() {
		return rdbtnCliente;
	}

	public void setRdbtnCliente(JRadioButton rdbtnCliente) {
		this.rdbtnCliente = rdbtnCliente;
	}

	public ViewFacturas(JFrame view) {
		super(view,"Facturas");
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		btnAgregar.setEnabled(false);
		

	
	
		
		btnEliminar.setToolTipText("Anular Facturas");
		btnEliminar.setEnabled(true);
	    
	    btnImprimir = new BotonImprimirSmall();
	    //btnLimpiar.setIcon(new ImageIcon("recursos/clear.png")); // NOI18N
	    panelAccion.add(btnImprimir);
	    //panelAccion.setVisible(false);
	    
	    btnDevolucion = new BotonDevolucion();
	    panelAccion.add(btnDevolucion);
		
		rdbtnCliente = new JRadioButton("Cliente");
		panelOpcioneBusqueda.add(rdbtnCliente);
		grupoOpciones.add(rdbtnCliente);
	  
		rdbtnFecha.setVisible(true);
		

		modeloListaCajas=new CbxTmCajas();//comentar para poder mostrar en forma de diseno la ventana
		modeloListaCajas.agregar(new Caja());
		
		cbxCajas = new JComboBox<Caja>(modeloListaCajas);
		panelOpcioneBusqueda.add(cbxCajas);
		
	    
	    //tabla y sus componentes
		modelo=new TablaModeloFacturados();
		
		tabla.setModel(modelo);
		RenderizadorTablaFacturas renderizador = new RenderizadorTablaFacturas();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(60);     //Tama�o de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(90);	//de las columnas
		tabla.getColumnModel().getColumn(2).setPreferredWidth(70);	//en la tabla
		tabla.getColumnModel().getColumn(3).setPreferredWidth(280);	//
		
		
		
		
	}
	/**
	 * @return the modeloListaCajas
	 */
	public CbxTmCajas getModeloListaCajas() {
		return modeloListaCajas;
	}
	/**
	 * @return the cbxCajas
	 */
	public JComboBox<Caja> getCbxCajas() {
		return cbxCajas;
	}
	
public void conectarControlador(CtlFacturas c){
		
		rdbtnTodos.addActionListener(c);
		rdbtnTodos.setActionCommand("TODAS");
		
		rdbtnId.addActionListener(c);
		//rdbtnId.getActionCommand();
		rdbtnId.setActionCommand("ID");
		
		rdbtnFecha.addActionListener(c);
		rdbtnFecha.setActionCommand("FECHA");
		
		
		rdbtnCliente.addActionListener(c);
		rdbtnCliente.setActionCommand("ESCRIBIR");
		
		
		
		
		txtBuscar.addActionListener(c);
		txtBuscar.setActionCommand("BUSCAR");
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		 btnAgregar.addActionListener(c);
		 btnAgregar.setActionCommand("INSERTAR");
		 
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ANULARFACTURA");
		 
		 btnImprimir.addActionListener(c);
		 btnImprimir.setActionCommand("IMPRIMIR");
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		 btnDevolucion.addActionListener(c);
		 btnDevolucion.setActionCommand("DEVOLUCION");
		 
		 cbxCajas.addActionListener(c);
		cbxCajas.setActionCommand("CAMBIOCOMBOBOX");
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	
	public TablaModeloFacturados getModelo(){
		return modelo;
	}
	
	public BotonImprimirSmall getBtnImprimir(){
		return btnImprimir;
	}
	public JRadioButton getRdbtnFecha(){
		return rdbtnFecha;
	}
	public JRadioButton getRdbtnTodos(){
		return rdbtnTodos;
		
	}

	

}
