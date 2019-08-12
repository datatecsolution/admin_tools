package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import view.botones.BotonAgregar;
import view.botones.BotonBuscar;
import view.botones.BotonCobrarSmall;
import view.botones.BotonEliminar;
import view.botones.BotonImprimirSmall;
import view.rendes.PanelPadre;
import view.rendes.RenderizadorTablaFacturas;
import view.tablemodel.TablaModeloFacturas;
import controlador.CtlArticuloLista;
import controlador.CtlCotizacionLista;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewListaCotizacion extends ViewTabla {
	
	protected BotonImprimirSmall btnImprimir;
	
	
	
	private JRadioButton rdbtnCliente;

	
	
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	
	
	
	private TablaModeloFacturas modelo;
	
	public ViewListaCotizacion(Window view){
		
		super(view,"Cotizaciones");
		
		
		
		
        btnImprimir = new BotonImprimirSmall();
       // btnImprimir.setEnabled(false);
        //btnLimpiar.setIcon(new ImageIcon("recursos/clear.png")); // NOI18N
        panelAccion.add(btnImprimir);
        
    
        
        
        rdbtnFecha.setVisible(true);
	
        rdbtnCliente=new JRadioButton("Cliente");
		panelOpcioneBusqueda.add(rdbtnCliente);
		grupoOpciones.add(rdbtnCliente);
		
	
		
		
				
	
        //tabla y sus componentes
		modelo=new TablaModeloFacturas();
		
		
		tabla.setModel(modelo);
		RenderizadorTablaFacturas renderizador = new RenderizadorTablaFacturas();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(100);     //Tama�o de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(300);	//de las columnas
		tabla.getColumnModel().getColumn(2).setPreferredWidth(70);	//en la tabla
		tabla.getColumnModel().getColumn(2).setPreferredWidth(70);	//
		
		
	}
	
	
public void conectarControlador(CtlCotizacionLista c){
		
		
		rdbtnId.addActionListener(c);
		//rdbtnId.getActionCommand();
		rdbtnId.setActionCommand("ID");
		
		rdbtnCliente.addActionListener(c);
		rdbtnCliente.setActionCommand("ESCRIBIR");
		
		rdbtnFecha.addActionListener(c);
		rdbtnFecha.setActionCommand("MARCA");
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
	
		 btnAgregar.addActionListener(c);
		 btnAgregar.setActionCommand("INSERTAR");
		 
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ELIMINAR");
		 
		 btnImprimir.addActionListener(c);
		 btnImprimir.setActionCommand("IMPRIMIR");
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		 
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	public TablaModeloFacturas getModelo(){
		return modelo;
	}
	
	
	public BotonImprimirSmall getBtnCobrar(){
		return btnImprimir;
	}
	public JRadioButton getRdbtnCliente(){
		return rdbtnCliente;
	}
	

	

}
