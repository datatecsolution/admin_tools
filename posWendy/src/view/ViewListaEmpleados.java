package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ButtonGroup;
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

import controlador.CtlEmpleadosLista;
import controlador.CtlEmpleadosListaBuscar;
import controlador.CtlUsuariosLista;
import view.botones.BotonAgregar;
import view.botones.BotonBuscar;
import view.botones.BotonEliminar;
import view.botones.BotonLimpiar;
import view.rendes.PanelPadre;
import view.rendes.TablaRenderizadorProveedor;
import view.tablemodel.TmEmpleados;
import view.tablemodel.TmUsuarios;

public class ViewListaEmpleados extends ViewTabla  {
	
	
	protected JButton btnLimpiar;
	
	
	
	private JRadioButton rdbtnApellido;
	private JRadioButton rdbtnNombre;

	
	private TmEmpleados modelo;
	
	public ViewListaEmpleados(Window view){
		super(view,"Empleados");
		Init();
	}
	
	public void Init() {
		
		
        
        btnLimpiar = new BotonLimpiar();
        //btnLimpiar.setIcon(new ImageIcon(ViewListaMarca.class.getResource("/View/imagen/clear.png"))); // NOI18N
        panelAccion.add(btnLimpiar);
        
		
		rdbtnNombre = new JRadioButton("Nombre",false);
		panelOpcioneBusqueda.add(rdbtnNombre);
		grupoOpciones.add(rdbtnNombre);
		
		rdbtnApellido = new JRadioButton("Apellido",false);
		panelOpcioneBusqueda.add(rdbtnApellido);
		grupoOpciones.add(rdbtnApellido);
		
		
		
		//tabla y sus componentes
		modelo=new TmEmpleados();
		
		tabla.setModel(modelo);
		TablaRenderizadorProveedor renderizador = new TablaRenderizadorProveedor();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(5);     //Tama�o de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//
		tabla.setAutoCreateRowSorter(true);

	}
	
	public TmEmpleados getModelo(){
		return modelo;
	}
	
	public void conectarCtl(CtlEmpleadosLista c){
		
		
		rdbtnApellido.addActionListener(c);
		rdbtnApellido.setActionCommand("ESCRIBIR");
		
		
		
		rdbtnNombre.addActionListener(c);
		rdbtnNombre.setActionCommand("ESCRIBIR");
		
		btnAgregar.addActionListener(c);
		btnAgregar.setActionCommand("INSERTAR");
		
		btnEliminar.addActionListener(c);
		btnEliminar.setActionCommand("ELIMINAR");
		
		txtBuscar.addActionListener(c);
		txtBuscar.setActionCommand("BUSCAR");
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		tabla.addMouseListener(c);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
	}
	
	public JRadioButton getRdbtnApellido(){
		return rdbtnApellido;
	}
	public JRadioButton getRdbtnNombre(){
		return rdbtnNombre;
	}
	
	public void conectarCtlBuscar(CtlEmpleadosListaBuscar c) {
		// TODO Auto-generated method stub
		this.addWindowListener(c);
		
		rdbtnApellido.addActionListener(c);
		rdbtnApellido.setActionCommand("ESCRIBIR");
		
		rdbtnNombre.addActionListener(c);
		rdbtnNombre.setActionCommand("ESCRIBIR");
		
		btnAgregar.addActionListener(c);
		btnAgregar.setActionCommand("INSERTAR");
		btnAgregar.addKeyListener(c);
		
		btnEliminar.addActionListener(c);
		btnEliminar.setActionCommand("ELIMINAR");
		btnAgregar.addKeyListener(c);
		
		txtBuscar.addActionListener(c);
		txtBuscar.setActionCommand("BUSCAR");
		btnAgregar.addKeyListener(c);
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		btnAgregar.addKeyListener(c);
		
		btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 btnAgregar.addKeyListener(c);
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 btnAgregar.addKeyListener(c);
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 txtBuscar.addKeyListener(c);
		
		
		tabla.addMouseListener(c);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}

}
