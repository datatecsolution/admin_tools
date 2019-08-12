package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import view.botones.BotonAgregar;
import view.botones.BotonBuscar;
import view.botones.BotonCuenta;
import view.botones.BotonEliminar;
import view.botones.BotonLimpiar;
import view.botones.BotonReporte;
import view.rendes.PanelPadre;
import view.rendes.TablaRenderizadorProveedor;
import view.tablemodel.TablaModeloCliente;
import controlador.CtlClienteBuscar;
import controlador.CtlClienteLista;

public class ViewListaClientes extends ViewTabla {
	
	protected JButton btnLimpiar;
	
	
	
	private JRadioButton rdbtnNombre;
	private JRadioButton rdbtnRtn;

	
	private TablaModeloCliente modelo;

	private BotonReporte btnReporte;
	private BotonCuenta btnCuenta;
	
	
	
	public ViewListaClientes(Window view) {
		
		super(view,"Clientes");

        
        btnLimpiar = new BotonLimpiar();
        //btnLimpiar.setIcon(new ImageIcon(ViewListaClientes.class.getResource("/View/imagen/clear.png"))); // NOI18N
        panelAccion.add(btnLimpiar);
        
        btnReporte=new BotonReporte();
        panelAccion.add(btnReporte);
        
        btnCuenta=new BotonCuenta();
        panelAccion.add(btnCuenta);
        
		
		rdbtnNombre = new JRadioButton("Nombre",false);
		panelOpcioneBusqueda.add(rdbtnNombre);
		grupoOpciones.add(rdbtnNombre);
		
		rdbtnRtn = new JRadioButton("RTN",false);
		panelOpcioneBusqueda.add(rdbtnRtn);
		grupoOpciones.add(rdbtnRtn);
		
		 //tabla y sus componentes
		modelo=new TablaModeloCliente();
		
		tabla.setModel(modelo);
		TablaRenderizadorProveedor renderizador = new TablaRenderizadorProveedor();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(5);     //Tama�o de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(200);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//
		tabla.getColumnModel().getColumn(3).setPreferredWidth(10);	//
		
		
		
	}
	
	public TablaModeloCliente getModelo(){
		return modelo;
	}
	
	
	public JRadioButton getRdbtnNombre(){
		return rdbtnNombre;
	}
	public JRadioButton getRdbtnRtn(){
		return  rdbtnRtn;
		
	}
	
	public void conectarControladorBuscar(CtlClienteBuscar c){
		
		btnAgregar.addActionListener(c);
		btnAgregar.setActionCommand("NUEVO");
		
		rdbtnId.addActionListener(c);
		//rdbtnId.getActionCommand();
		rdbtnId.setActionCommand("ID");
		
		rdbtnNombre.addActionListener(c);
		rdbtnNombre.setActionCommand("ESCRIBIR");
		
		rdbtnRtn.addActionListener(c);
		rdbtnRtn.setActionCommand("ESCRIBIR");
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		txtBuscar.addActionListener(c);
		txtBuscar.setActionCommand("BUSCAR");
		
		
		btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		
		tabla.addMouseListener(c);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public void conectarControlador(CtlClienteLista c){
		btnAgregar.addActionListener(c);
		btnAgregar.setActionCommand("NUEVO");
		
		rdbtnId.addActionListener(c);
		//rdbtnId.getActionCommand();
		rdbtnId.setActionCommand("ID");
		
		rdbtnNombre.addActionListener(c);
		rdbtnNombre.setActionCommand("ESCRIBIR");
		
		rdbtnRtn.addActionListener(c);
		rdbtnRtn.setActionCommand("ESCRIBIR");
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		txtBuscar.addActionListener(c);
		txtBuscar.setActionCommand("BUSCAR");
		
		btnSiguiente.addActionListener(c);
		btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		 btnReporte.addActionListener(c);
		 btnReporte.setActionCommand("CUENTASCLIENTES");
		 
		 btnCuenta.addActionListener(c);
		 btnCuenta.setActionCommand("CUENTACLIENTE");
		 
		
		tabla.addMouseListener(c);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

}
