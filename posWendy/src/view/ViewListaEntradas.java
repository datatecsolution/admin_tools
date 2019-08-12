package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controlador.CtlEntradasListas;
import controlador.CtlSalidasListas;
import view.botones.BotonBuscar;
import view.botones.BotonKardex;
import view.botones.BotonReporte;
import view.rendes.TablaRenderizadorProveedor;
import view.tablemodel.TmEntradas;
import view.tablemodel.TmSalidas;
import javax.swing.JRadioButton;

public class ViewListaEntradas extends ViewTabla {
	
	private TmEntradas modelo;
	private JRadioButton rdbtnEmpleado;
	private BotonReporte btnReporte;

	public ViewListaEntradas(Window view) {
		// TODO Auto-generated constructor stub
		super(view, "Entradas cajas");
		btnAgregar.setEnabled(false);
		
		btnEliminar.setEnabled(true);
		
		btnReporte=new BotonReporte();
	    panelAccion.add(btnReporte);
		
		rdbtnEmpleado = new JRadioButton("Nombre Empleado");
		panelOpcioneBusqueda.add(rdbtnEmpleado);
		grupoOpciones.add(rdbtnEmpleado);
		
		
		
		modelo=new TmEntradas();
		
		tabla.setModel(modelo);
		
		TablaRenderizadorProveedor renderizador = new TablaRenderizadorProveedor();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(5);     //Tamaño de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(200);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//
		tabla.getColumnModel().getColumn(3).setPreferredWidth(10);		//
		
		

	}
	public TmEntradas getModelo(){
		return modelo;
	}
	
	public void concetarControlador(CtlEntradasListas c){
		
		
		
		
		rdbtnId.addActionListener(c);
		rdbtnId.setActionCommand("ID");
		
		
		
		btnReporte.addActionListener(c);
		btnReporte.setActionCommand("REPORTE");
		
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ELIMINAR");
		
		
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		 btnBuscar.addActionListener(c);
		 btnBuscar.setActionCommand("BUSCAR");
		 
		 this.rdbtnId.addActionListener(c);
		 rdbtnId.setActionCommand("ID");
		 
		 this.rdbtnEmpleado.addActionListener(c);
		 this.rdbtnEmpleado.setActionCommand("ESCRIBIR");
		 
		 this.rdbtnTodos.addActionListener(c);
		 this.rdbtnTodos.setActionCommand("TODOS");
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	/**
	 * @return the rdbtnEmpleado
	 */
	public JRadioButton getRdbtnEmpleado() {
		return rdbtnEmpleado;
	}

}
