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

import controlador.CtlRequisicionesLista;
import view.botones.BotonAgregar;
import view.botones.BotonBuscar;
import view.botones.BotonEliminar;
import view.botones.BotonImprimirSmall;
import view.botones.BotonLimpiar;
import view.rendes.PanelPadre;
import view.rendes.TablaRenderizadorProveedor;
import view.tablemodel.TmRequisicionesEncabezados;

public class ViewListaRequisiciones extends ViewTabla {
	
	public TmRequisicionesEncabezados modelo;
		
	
	

	public ViewListaRequisiciones(Window view) {
		//super("Proveedores");
				super(view,"Requisiciones");
				
				modelo = new TmRequisicionesEncabezados();//se crea el modelo de los datos de la tabla
				tabla.setModel(modelo);
				//Estitlo para la tabla		
				TablaRenderizadorProveedor renderizador = new TablaRenderizadorProveedor();
				tabla.setDefaultRenderer(String.class, renderizador);
				//tama�o de las columnas
				tabla.getColumnModel().getColumn(0).setPreferredWidth(50);     //Tama�o de las columnas de las tablas
				tabla.getColumnModel().getColumn(1).setPreferredWidth(50);	//de las columnas
				tabla.getColumnModel().getColumn(2).setPreferredWidth(100);	//en la tabla
				tabla.getColumnModel().getColumn(3).setPreferredWidth(100);	//
				tabla.getColumnModel().getColumn(4).setPreferredWidth(50);	//
				tabla.getColumnModel().getColumn(5).setPreferredWidth(50);	//
				
						
				rdbtnFecha.setVisible(true);
				
				//btnEliminar.setEnabled(true);
	
				
		        
		       // btnImprimir = new BotonImprimirSmall();
		        //btnLimpiar.setIcon(new ImageIcon(ViewListaProveedor.class.getResource("/View/imagen/clear.png"))); // NOI18N
		        //panelAccion.add(btnImprimir);
		
	}

	public TmRequisicionesEncabezados getModelo() {
		// TODO Auto-generated method stub
		return modelo;
	}
	
	public JTable getTabla(){
		return tabla;
	}
	public void conectarCtl(CtlRequisicionesLista c){
		rdbtnTodos.addActionListener(c);
		rdbtnTodos.setActionCommand("TODAS");
		
		rdbtnId.addActionListener(c);
		//rdbtnId.getActionCommand();
		rdbtnId.setActionCommand("ID");
		
		rdbtnFecha.addActionListener(c);
		rdbtnFecha.setActionCommand("FECHA");
		
		
		
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		 btnAgregar.addActionListener(c);
		 btnAgregar.setActionCommand("INSERTAR");
		 
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ANULARFACTURA");
		 
		
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
	}
	

}
