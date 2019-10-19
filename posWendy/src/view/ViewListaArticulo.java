package view;


import java.awt.Color;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.Window;



import javax.swing.JButton;

import javax.swing.JRadioButton;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import view.botones.BotonBarcode;
import view.botones.BotonBuscar;
import view.botones.BotonCuenta;
import view.botones.BotonExistencia;
import view.botones.BotonKardex;
import view.botones.BotonLimpiar;
import view.rendes.PanelPadre;
import view.rendes.TablaRenderizadorArticulos;
import view.rendes.TablaRenderizadorProveedor;
import view.tablemodel.CbxTmDepartamento;
import view.tablemodel.TmArticulo;
import controlador.CtlArticuloBuscar;
import controlador.CtlArticuloLista;
import modelo.Departamento;

import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ViewListaArticulo extends ViewTabla {
	
	
	
	
	
	protected JButton btnLimpiar;
	
	
	private JRadioButton rdbtnMarca;

	private TmArticulo modelo;
	
	
	
	private BotonKardex btnKardex;
	private BotonCuenta btnInventario;


	private JRadioButton rdbtnArticulo;
	private JComboBox<Departamento> cbxDepart;
	
	private CbxTmDepartamento modeloCbx;


	private BotonExistencia btnExistecia;
	
	
	
	
	

	
	private JPopupMenu menuContextual; // permite al usuario seleccionar el color
	private JMenuItem mntmReporteVenta;


	

	
	
	
	public ViewListaArticulo(Window view){
		super(view,"Articulos");
		modeloCbx=new CbxTmDepartamento();//comentar para ver en forma de dise�o
		
		
		
		super.panelEstadoRegistro.setVisible(true);
		
		menuContextual = new JPopupMenu(); // crea el men� contextual
		
		//opcion del menu flotante
		mntmReporteVenta = new JMenuItem("Reporte Venta");
		menuContextual.add(mntmReporteVenta);
		
        
        btnLimpiar = new BotonBarcode();
        panelAccion.add(btnLimpiar);
        btnLimpiar.setEnabled(false);
        
        btnKardex=new BotonKardex();
        panelAccion.add(btnKardex);
        
        btnInventario=new BotonCuenta();
        panelAccion.add(btnInventario);
        
        btnExistecia=new BotonExistencia();
        panelAccion.add(btnExistecia);
        
		
		
		
		rdbtnArticulo = new JRadioButton("Articulo",false);
		panelOpcioneBusqueda.add(rdbtnArticulo);
		grupoOpciones.add(rdbtnArticulo);
		
		rdbtnMarca = new JRadioButton("Categoria",false);
		panelOpcioneBusqueda.add(rdbtnMarca);
		grupoOpciones.add(rdbtnMarca);
		
		cbxDepart = new JComboBox<Departamento>();
		cbxDepart.setModel(modeloCbx);
		panelOpcioneBusqueda.add(cbxDepart);
		//cbxDepart.setSize(30, 10);
		//cbxDepart.setPreferredSize(new Dimension(70, 15));
		
        
        //tabla y sus componentes
		modelo=new TmArticulo();
		
		tabla.setModel(modelo);
		TablaRenderizadorArticulos renderizador = new TablaRenderizadorArticulos();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(5);     //Tamaño de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(200);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//
		tabla.getColumnModel().getColumn(3).setPreferredWidth(10);		//
		
		
		//this.setSize(1000, 442);

		
	}
	
	
	public void conectarControlador(CtlArticuloLista c){
		
		
		this.addWindowListener(c);
		
		btnExistecia.addActionListener(c);
		btnExistecia.setActionCommand("EXISTENCIA");
		
		rdbtnActivos.addActionListener(c);
		rdbtnActivos.setActionCommand("REG_ACTIVO");
		
		rdbtnInactivo.addActionListener(c);
		rdbtnInactivo.setActionCommand("REG_INACTIVO");
		
		rdbtnTodosEstado.addActionListener(c);
		rdbtnTodosEstado.setActionCommand("REG_TODOS");
		
		rdbtnId.addActionListener(c);
		rdbtnId.setActionCommand("ID");
		
		rdbtnArticulo.addActionListener(c);
		rdbtnArticulo.setActionCommand("ESCRIBIR");
		
		rdbtnMarca.addActionListener(c);
		rdbtnMarca.setActionCommand("ESCRIBIR");
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		 btnAgregar.addActionListener(c);
		 btnAgregar.setActionCommand("INSERTAR");
		 
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ELIMINAR");
		 
		 btnLimpiar.addActionListener(c);
		 btnLimpiar.setActionCommand("LIMPIAR");
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		 mntmReporteVenta.addActionListener(c);
		 mntmReporteVenta.setActionCommand("REPORTE_VENTA");
		 
		 btnKardex.addActionListener(c);
		 btnKardex.setActionCommand("KARDEX");
		 
		 btnInventario.addActionListener(c);
		 btnInventario.setActionCommand("INVENTARIO");
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	
	public TmArticulo getModelo(){
		return modelo;
	}
	public JButton getBtnEliminar(){
		return btnEliminar;
	}
	
	public JRadioButton getRdbtnArticulo(){
		return rdbtnArticulo;
	}
	public JRadioButton getRdbtnMarca(){
		return  rdbtnMarca;
		
	}
	public JPopupMenu getMenuContextual(){
		return menuContextual;
		
	}
	public JButton getBtnLimpiar(){
		return btnLimpiar;
	}
	
	public JComboBox getCbxDepart(){
		return cbxDepart;
	}
	public CbxTmDepartamento getModeloCbxDepartamento(){
		return modeloCbx;
	}
	
public void conectarControladorBuscar(CtlArticuloBuscar c){
	
		this.addWindowListener(c);
	
	
		rdbtnTodos.addKeyListener(c);
		
		rdbtnId.addActionListener(c);
		rdbtnId.setActionCommand("ID");
		rdbtnId.addKeyListener(c);
		
		rdbtnArticulo.addActionListener(c);
		rdbtnArticulo.setActionCommand("ESCRIBIR");
		rdbtnArticulo.addKeyListener(c);
		
		rdbtnMarca.addActionListener(c);
		rdbtnMarca.setActionCommand("ESCRIBIR");
		rdbtnMarca.addKeyListener(c);
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		btnBuscar.addKeyListener(c);
		
		 btnAgregar.addActionListener(c);
		 btnAgregar.setActionCommand("INSERTAR");
		 btnAgregar.addKeyListener(c);
		 
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ELIMINAR");
		 btnEliminar.addKeyListener(c);
		 
		 btnLimpiar.addActionListener(c);
		 btnLimpiar.setActionCommand("LIMPIAR");
		 btnLimpiar.addKeyListener(c);
		 
		
		 
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 txtBuscar.addKeyListener(c);
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 tabla.addKeyListener(c);
		 
	}
	

}