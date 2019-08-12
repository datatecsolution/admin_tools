package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import controlador.CtlFacturas;
import controlador.CtlPagoLista;
import view.botones.BotonAgregar;
import view.botones.BotonBuscar;
import view.botones.BotonCobrarSmall;
import view.botones.BotonEliminar;
import view.botones.BotonImprimirSmall;
import view.botones.BotonReporte;
import view.rendes.PanelPadre;
import view.rendes.RenderizadorTablaFacturas;
import view.tablemodel.TablaModeloFacturados;
import view.tablemodel.TablaModeloFacturas;
import view.tablemodel.TmPagos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewListaPagos extends ViewTabla {
	
	
	protected BotonImprimirSmall btnImprimir;
	
	
	
	
	
	
	
	private TmPagos modelo;







	private BotonReporte btnReporte;




	

	public ViewListaPagos(Window view) {
		super(view,"Pagos de clientes");
		
		
		
	
	    
	    btnImprimir = new BotonImprimirSmall();
	    btnImprimir.setEnabled(false);
	    //btnLimpiar.setIcon(new ImageIcon("recursos/clear.png")); // NOI18N
	    panelAccion.add(btnImprimir);
	    //panelAccion.setVisible(false);
	    
	    btnReporte=new BotonReporte();
	    panelAccion.add(btnReporte);
	    
	 
		
	    rdbtnFecha.setVisible(true);
		
		
				
		
        //tabla y sus componentes
		modelo=new TmPagos();
		tabla.setModel(modelo);
		RenderizadorTablaFacturas renderizador = new RenderizadorTablaFacturas();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(20);     //Tama�o de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(20);	//de las columnas
		tabla.getColumnModel().getColumn(2).setPreferredWidth(250);	//en la tabla
		tabla.getColumnModel().getColumn(3).setPreferredWidth(70);	//
		
		
		
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		//setSize(900,565);
	
	}
	
	
	public TmPagos getModelo(){
		return modelo;
	}
	
	public BotonImprimirSmall getBtnImprimir(){
		return btnImprimir;
	}

	
public void conectarControlador(CtlPagoLista c){
		this.addWindowListener(c);
		rdbtnTodos.addActionListener(c);
		rdbtnTodos.setActionCommand("TODAS");
		

		btnReporte.addActionListener(c);
		btnReporte.setActionCommand("REPORTE");
		
		rdbtnId.addActionListener(c);
		//rdbtnId.getActionCommand();
		rdbtnId.setActionCommand("ID");
		
		rdbtnFecha.addActionListener(c);
		rdbtnFecha.setActionCommand("FECHA");
		
		
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		// super.bbtnLimpiar.addActionListener(c);
		// btnLimpiar.setActionCommand("LIMPIAR");
		
		
		
		
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
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}


}
