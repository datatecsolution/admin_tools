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
import view.rendes.RenderizadorTablaCuentasBanco;
import view.rendes.RenderizadorTablaFacturaCompra;
import view.rendes.TablaRenderizadorArticulos;
import view.rendes.TablaRenderizadorProveedor;
import view.tablemodel.CbxTmCuentasBancos;
import view.tablemodel.CbxTmDepartamento;
import view.tablemodel.TmArticulo;
import view.tablemodel.TmCuentasBanco;
import controlador.CtlArticuloBuscar;
import controlador.CtlArticuloLista;
import controlador.CtlCuentasBanco;
import modelo.Banco;
import modelo.Departamento;

import javax.swing.JComboBox;

public class ViewCuentaBanco extends ViewTabla {

	private TmCuentasBanco modelo;
	private BotonCuenta btnReporte;
    private JComboBox<Banco> cbxBancos;
	private CbxTmCuentasBancos modeloCuentasBancos;


	

	
	
	
	public ViewCuentaBanco(Window view){
		super(view,"Saldos de cuenta");
		
        super.btnBuscar.setVisible(false);
        super.txtBuscar.setVisible(false);
        btnReporte=new BotonCuenta();
        panelAccion.add(btnReporte);
		
		
		
		modeloCuentasBancos=new CbxTmCuentasBancos();
		
		cbxBancos = new JComboBox<Banco>();
		cbxBancos.setModel(modeloCuentasBancos);
		panelOpcioneBusqueda.add(cbxBancos);
		
        
        //tabla y sus componentes
		modelo=new TmCuentasBanco();
		
		tabla.setModel(modelo);
		RenderizadorTablaCuentasBanco renderizador = new RenderizadorTablaCuentasBanco();
		tabla.setDefaultRenderer(String.class, renderizador);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(100);     //Tamaño de las columnas de las tablas
		tabla.getColumnModel().getColumn(1).setPreferredWidth(300);	//
		tabla.getColumnModel().getColumn(2).setPreferredWidth(30);	//
		tabla.getColumnModel().getColumn(3).setPreferredWidth(50);		//
		tabla.getColumnModel().getColumn(4).setPreferredWidth(50);		//
		tabla.getColumnModel().getColumn(5).setPreferredWidth(50);		//
		
		
		//this.setSize(1000, 442);

		
	}
	
	
	public void conectarControlador(CtlCuentasBanco c){
		
		
		this.addWindowListener(c);
		
		this.cbxBancos.addItemListener(c);
		
		
		
		
		rdbtnId.addActionListener(c);
		rdbtnId.setActionCommand("ID");
		
		
		
		btnBuscar.addActionListener(c);
		btnBuscar.setActionCommand("BUSCAR");
		
		 btnAgregar.addActionListener(c);
		 btnAgregar.setActionCommand("INSERTAR");
		 
		 btnEliminar.addActionListener(c);
		 btnEliminar.setActionCommand("ELIMINAR");
		 
		
		 
		 txtBuscar.addActionListener(c);
		 txtBuscar.setActionCommand("BUSCAR");
		 
		 btnSiguiente.addActionListener(c);
		 btnSiguiente.setActionCommand("NEXT");
		 
		 btnAnterior.addActionListener(c);
		 btnAnterior.setActionCommand("LAST");
		 
		
		 
		 btnReporte.addActionListener(c);
		 btnReporte.setActionCommand("REPORTE");
		 
		 tabla.addMouseListener(c);
		 tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	
	public TmCuentasBanco getModelo(){
		return modelo;
	}
	public JButton getBtnEliminar(){
		return btnEliminar;
	}
	
	
	
	public JComboBox<Banco> getCbxBanco(){
		return cbxBancos;
	}
	

	/**
	 * @return the modeloCuentasBancos
	 */
	public CbxTmCuentasBancos getModeloCuentasBancos() {
		return modeloCuentasBancos;
	}

	

}