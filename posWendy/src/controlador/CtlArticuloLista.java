package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import modelo.Articulo;
import modelo.dao.ArticuloDao;
import modelo.dao.CodBarraDao;
import modelo.dao.DepartamentoDao;
import modelo.AbstractJasperReports;
import modelo.ConexionStatic;
import modelo.Departamento;
import view.ViewArticuloExistencias;
import view.ViewCrearArticulo;
import view.ViewFiltroRepVentasArticulo;
import view.ViewListaArticulo;

public class CtlArticuloLista extends MouseAdapter implements ActionListener, WindowListener {
	public ViewListaArticulo view;
	public ViewCrearArticulo viewArticulo;
	
	
	private Articulo myArticulo;
	private ArticuloDao myArticuloDao;
	private DepartamentoDao deptDao=null;
	
	//fila selecciona enla lista
	private int filaPulsada;
	
	
	public CtlArticuloLista(ViewListaArticulo v){
		//se estable el pool de conexiones del inicio y para su utilizacion
		
		this.view=v;
		myArticulo=new Articulo();
		myArticuloDao=new ArticuloDao();
		
		deptDao=new DepartamentoDao();
		
		
		view.conectarControlador(this);
		cargarComboBox();
		
	
		//se establece el filtro para el estada de los registros
		myArticuloDao.setEstado(1);
		
		myArticuloDao.setMyBodega(view.getModeloCbxDepartamento().getDepartamento(view.getCbxDepart().getSelectedIndex()));
		
		cargarTabla(myArticuloDao.todos(view.getModelo().getCanItemPag(),view.getModelo().getLimiteSuperior()));
		
		view.setVisible(true);
		//this.view.setVisible(true);
		
	}
	
	private void cargarComboBox(){
		//se crea el objeto para obtener de la bd los impuestos
		//myImpuestoDao=new ImpuestoDao(conexion);
	
		//se obtiene la lista de los impuesto y se le pasa al modelo de la lista
		this.view.getModeloCbxDepartamento().setLista(this.deptDao.todos());
		
		
		//se remueve la lista por defecto
		this.view.getCbxDepart().removeAllItems();
	
		this.view.getCbxDepart().setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//se recoge el comando programado en el boton que se hizo click
		String comando=e.getActionCommand();
		//se establece la bodega para todas las busqueda
		myArticuloDao.setMyBodega(view.getModeloCbxDepartamento().getDepartamento(view.getCbxDepart().getSelectedIndex()));
		
		
		switch(comando){
		
		case "REPORTE_VENTA":
				Articulo unoReporte=this.view.getModelo().getArticulo(view.getTabla().getSelectedRow());
				//JOptionPane.showMessageDialog(view, uno.toString());
				ViewFiltroRepVentasArticulo viewFiltroReportVentaArticulo=new ViewFiltroRepVentasArticulo(view);
				CtlFiltroRepVentasArticulo ctlFiltroRepVentasArticulo=new CtlFiltroRepVentasArticulo(viewFiltroReportVentaArticulo,unoReporte);
			break;
		
		case "EXISTENCIA":
			
			/*
			if(this.view.getTabla().getSelectedRow()>=0){
				
				ViewArticuloExistencias viewExistencias=new ViewArticuloExistencias(view);
				CtlArticuloExistencias ctlExistencia=new CtlArticuloExistencias(viewExistencias);
				boolean result=ctlExistencia.actualizar(view.getModelo().getArticulo(this.view.getTabla().getSelectedRow()), view.getModeloCbxDepartamento().getDepartamento(view.getCbxDepart().getSelectedIndex()));
	
				if(result){
					
						view.getRdbtnId().setSelected(true);
						this.view.getTxtBuscar().setText(view.getModelo().getArticulo(this.view.getTabla().getSelectedRow()).getId()+"");
						
						myArticulo=myArticuloDao.buscarPorId(Integer.parseInt(view.getTxtBuscar().getText()));
						this.view.getModelo().limpiarArticulos();
						if(myArticulo!=null){												
							this.view.getModelo().agregarArticulo(myArticulo);
						}
					
					
				}
		}*/
		
			break;
		
		case "REG_ACTIVO":
				this.myArticuloDao.setEstado(1);
			break;
			
		case "REG_INACTIVO":
			this.myArticuloDao.setEstado(0);
		break;
		
		case "REG_TODOS":
			this.myArticuloDao.setEstado(2);
		break;
		case "ESCRIBIR":
			view.setTamanioVentana(1);
			break;
		case "BUSCAR":
			view.getModelo().setPaginacion();
			
			//si se seleciono el boton ID
			if(this.view.getRdbtnId().isSelected()){  
				//myArticulo=myArticuloDao.buscarArticulo(Integer.parseInt(this.view.getTxtBuscar().getText()));
				myArticulo=myArticuloDao.buscarArticuloBarraCod(this.view.getTxtBuscar().getText());
				this.view.getModelo().limpiarArticulos();
				if(myArticulo!=null){												
					this.view.getModelo().agregarArticulo(myArticulo);
				}
			} 
			
			if(this.view.getRdbtnArticulo().isSelected()){ //si esta selecionado la busqueda por nombre	
				
				cargarTabla(myArticuloDao.buscarArticulo(this.view.getTxtBuscar().getText(),view.getModelo().getLimiteSuperior(),view.getModelo().getCanItemPag()));
		        
				}
			if(this.view.getRdbtnMarca().isSelected()){  
				cargarTabla(myArticuloDao.buscarArticuloMarca(this.view.getTxtBuscar().getText(),view.getModelo().getLimiteSuperior(),view.getModelo().getCanItemPag()));
				}
			
			if(this.view.getRdbtnTodos().isSelected()){  
				cargarTabla(myArticuloDao.todos(view.getModelo().getCanItemPag(),view.getModelo().getLimiteSuperior()));
				this.view.getTxtBuscar().setText("");
				}
			//se establece el numero de pagina en la view
			view.getTxtPagina().setText(""+view.getModelo().getNoPagina());
			break;
		
		case "INSERTAR":
			//crea la ventana para ingresar un nuevo proveedor
			viewArticulo= new ViewCrearArticulo(this.view);
			
			//se crea el controlador de la ventana y se le pasa la view
			CtlArticulo ctl=new CtlArticulo(viewArticulo,myArticuloDao);
			
			//se llama la metodo conectarCtl encargado de hacer set al manejador de eventos
			viewArticulo.conectarCtl(ctl);
			
			boolean resuldoGuarda=ctl.agregarArticulo();
			if(resuldoGuarda){
				
				view.getModelo().setPaginacion();
				//Se establece el departamento seleccionado
				myArticuloDao.setMyBodega(view.getModeloCbxDepartamento().getDepartamento(view.getCbxDepart().getSelectedIndex()));
				
				cargarTabla(myArticuloDao.todos(view.getModelo().getCanItemPag(),view.getModelo().getLimiteSuperior()));
				
				//se establece el numero de pagina en la view
				view.getTxtPagina().setText(""+view.getModelo().getNoPagina());
				
			}
			
			
			
			break;
		case "ELIMINAR":
			
			//Recoger qu� fila se ha pulsadao en la tabla
	        filaPulsada = this.view.getTabla().getSelectedRow();
	        
	        //si seleccion una fila
	        if(filaPulsada>=0){
	        	
	        	//Se recoge el id de la fila marcada
	          //  int identificador= (int)this.view.getModelo().getValueAt(filaPulsada, 0);
	            
	          //se consigue el proveedore de la fila seleccionada
	            myArticulo=this.view.getModelo().getArticulo(filaPulsada);
	            
	            int resul=JOptionPane.showConfirmDialog(view, "Desea dar de"+(myArticulo.isEstado() ?" BAJA ":" ALTA ")  +"el articulo \""+myArticulo.getArticulo()+"\"?");
				
				if(resul==0){
					myArticulo.setEstado((myArticulo.isEstado() ? false:true));
					//se manda a cambiar el estado
					if(myArticuloDao.actualizarEstado(myArticulo));{
						JOptionPane.showMessageDialog(view, "Se dio de "+(myArticulo.isEstado() ?" ALTA ":" BAJA ")  +" al articulo!!!", "Error al eliminar articulo", JOptionPane.INFORMATION_MESSAGE);
						this.actionPerformed(new ActionEvent(this, resul, "BUSCAR"));
			
					}
					
				}
	           
	            
	        }
			
			
			/*
			
			int resul=JOptionPane.showConfirmDialog(view, "Desea eleminar el articulo \""+myArticulo.getArticulo()+"\"?");
			
			if(resul==0){
				
					//se comprueba que el artiulo no tenga registro en facturas
					Conexion conexion=null;
					DetalleFacturaDao detalleFactura=new DetalleFacturaDao();
					if(detalleFactura.verificarArticuloEnDetalle(myArticulo.getId())){
						
								//se elemina el articulo de la BD y se procesa el resultado
							boolean resulEliminar=myArticuloDao.eliminar(myArticulo);
							if(resulEliminar){
								this.view.getModelo().eliminarArticulos(filaPulsada);
								this.view.getModelo().fireTableDataChanged();
								this.view.getBtnEliminar().setEnabled(false);
								this.view.getBtnLimpiar().setEnabled(false);
								JOptionPane.showMessageDialog(view, "Se elimino el articulo");
								
							}
					}else
						JOptionPane.showMessageDialog(view, "El Articulo ya esta facturado no lo puede elimimnar!!!", "Error al eliminar articulo", JOptionPane.ERROR_MESSAGE);
				
			}*/
			break;
			
		case "LIMPIAR":
			try {
				AbstractJasperReports.createReportCodBarra(ConexionStatic.getPoolConexion().getConnection(), myArticulo.getId());
				//AbstractJasperReports.ImprimirCodigo();
				AbstractJasperReports.showViewer(view);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.view.getBtnLimpiar().setEnabled(false);
			break;
			
		case "NEXT":
			//se establece los datos de la nueva pagina
			view.getModelo().netPag();
			
			if(this.view.getRdbtnTodos().isSelected()){  
				
				
				cargarTabla(myArticuloDao.todos(view.getModelo().getCanItemPag(),view.getModelo().getLimiteSuperior()));
				
				
			}
			if(view.getRdbtnArticulo().isSelected()){
				cargarTabla(myArticuloDao.buscarArticulo(this.view.getTxtBuscar().getText(),view.getModelo().getLimiteSuperior(),view.getModelo().getCanItemPag()));
			}
			if(this.view.getRdbtnMarca().isSelected()){  
				
				cargarTabla(myArticuloDao.buscarArticuloMarca(this.view.getTxtBuscar().getText(),view.getModelo().getLimiteSuperior(),view.getModelo().getCanItemPag()));
				}
			//se establece el numero de pagina en la view
			view.getTxtPagina().setText(""+view.getModelo().getNoPagina());
			break;
		case "LAST":
			//se establece los datos de la pagina anterior
			view.getModelo().lastPag();
			
			if(this.view.getRdbtnTodos().isSelected()){  

				cargarTabla(myArticuloDao.todos(view.getModelo().getCanItemPag(),view.getModelo().getLimiteSuperior()));
				
				
			}
			if(view.getRdbtnArticulo().isSelected()){
				cargarTabla(myArticuloDao.buscarArticulo(this.view.getTxtBuscar().getText(),view.getModelo().getLimiteSuperior(),view.getModelo().getCanItemPag()));
			}
			
			if(this.view.getRdbtnMarca().isSelected()){  
				
				cargarTabla(myArticuloDao.buscarArticuloMarca(this.view.getTxtBuscar().getText(),view.getModelo().getLimiteSuperior(),view.getModelo().getCanItemPag()));
				}
			//se establece el numero de pagina en la view
			view.getTxtPagina().setText(""+view.getModelo().getNoPagina());
			break;
			
		case "KARDEX":
			if(this.filaPulsada>=0){
				
				Departamento depart3= (Departamento) this.view.getCbxDepart().getSelectedItem();
				try {
					AbstractJasperReports.createReportKardex(ConexionStatic.getPoolConexion().getConnection(), myArticulo.getId(), depart3.getId(), ConexionStatic.getUsuarioLogin().getUser());
					//AbstractJasperReports.ImprimirCodigo();
					AbstractJasperReports.showViewer(view);
					//AbstractJasperReports.showViewer(view);
					//this.view.getBtnBarCode().setEnabled(false);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
				JOptionPane.showMessageDialog(view, "Selecione un articulo!!!", "Informacion", JOptionPane.NO_OPTION);
			}
			break;
			
		case "INVENTARIO":
			
			//si esta seleccionado el filtro de categoria se crea un reporte de inventario por filtrado por categoria filtrada
			if(this.view.getRdbtnMarca().isSelected()){ 
				
				
			
				if(view.getModelo().getArticulos().size()>0){
					Departamento depart4= (Departamento) this.view.getCbxDepart().getSelectedItem();
					Integer idCategoria=view.getModelo().getArticulo(0).getMarcaObj().getId();
					try {
						AbstractJasperReports.createReportInventarioBodegaCategoria(ConexionStatic.getPoolConexion().getConnection(), ConexionStatic.getUsuarioLogin().getUser(), depart4.getId(),idCategoria);
						//AbstractJasperReports.ImprimirCodigo();
						AbstractJasperReports.showViewer(view);
						//AbstractJasperReports.showViewer(view);
						//this.view.getBtnBarCode().setEnabled(false);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(view, "Debe realizar la busqueda primero para generar el reporte por categoria!!!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}else{
			
		
							
				Departamento depart4= (Departamento) this.view.getCbxDepart().getSelectedItem();
				try {
					AbstractJasperReports.createReportInventarioBodega(ConexionStatic.getPoolConexion().getConnection(), ConexionStatic.getUsuarioLogin().getUser(), depart4.getId());
					//AbstractJasperReports.ImprimirCodigo();
					AbstractJasperReports.showViewer(view);
					//AbstractJasperReports.showViewer(view);
					//this.view.getBtnBarCode().setEnabled(false);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
						
			/*
				KardexDao kardexDao=new KardexDao(conexion);
				if(this.filaPulsada>0)
					if(kardexDao.buscarExistencia(myArticulo.getId(), 1)!=null)
						JOptionPane.showMessageDialog(view, "La Existencia de "+myArticulo.getArticulo()+" es:"+kardexDao.buscarExistencia(myArticulo.getId(), 1));
						*/
			break;
			
				
			}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		//Recoger qu� fila se ha pulsadao en la tabla
        filaPulsada = this.view.getTabla().getSelectedRow();
        
        //si seleccion una fila
        if(filaPulsada>=0){
        	
        	//Se recoge el id de la fila marcada
          //  int identificador= (int)this.view.getModelo().getValueAt(filaPulsada, 0);
            
          //se consigue el proveedore de la fila seleccionada
            myArticulo=this.view.getModelo().getArticulo(filaPulsada);
        
            //fsdf
        	//si fue doble click mostrar modificar
        	if (e.getClickCount() == 2) {
        		
        		//se consigue el articulo de la fila donde se hizo doble click
	        	myArticulo=this.view.getModelo().getArticulo(filaPulsada);
        		//myArticulo=this.view.getModelo().getArticulo(filaPulsada);//se consigue el Marca de la fila seleccionada
	           
	        	//crea la ventana para modificar
				viewArticulo= new ViewCrearArticulo(this.view);
				
				//se crea el controlador de la ventana y se le pasa la view
				CtlArticulo ctlActulizarArticulo=new CtlArticulo(viewArticulo,myArticuloDao);
				viewArticulo.conectarCtl(ctlActulizarArticulo);
				
				//se crea el objeto para casultar los codigos de barra en la bd
				CodBarraDao myCodBarraDao=new CodBarraDao();
				
				//se estable los codigos de bara encontrados al objeto myArticulo;
				myArticulo.setCodBarras(myCodBarraDao.getCodsArticulo(myArticulo.getId()));
				
				//se llama del metodo actualizar marca para que se muestre la ventanda y procesa la modificacion
				boolean resultado=ctlActulizarArticulo.actualizarArticulo(myArticulo);
				
				//se proceso el resultado de modificar la marca
				if(resultado){
					this.view.getModelo().cambiarArticulo(filaPulsada, ctlActulizarArticulo.getArticulo());//se cambia en la vista
					this.view.getModelo().fireTableDataChanged();//se refrescan los cambios
					this.view.getTabla().getSelectionModel().setSelectionInterval(filaPulsada,filaPulsada);//se seleciona lo cambiado
				}	
			
				
				
				
	        }//fin del if del doble click
        	else{//si solo seleccion la fila se guarda el id de proveedor para accion de eliminar
        		
        		this.view.getBtnEliminar().setEnabled(true);
        		this.view.getBtnLimpiar().setEnabled(true);
        		/*idProveedor=identificador;
        		filaTabla=filaPulsada;*/
        		
        	}
		}
		
	}

	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void cargarTabla(List<Articulo> articulos){
		//JOptionPane.showMessageDialog(view, articulos);
		this.view.getModelo().limpiarArticulos();
		
		if(articulos!=null){
			for(int c=0;c<articulos.size();c++){
				this.view.getModelo().agregarArticulo(articulos.get(c));
				
				if(articulos.get(c).isEstado()==false){
					//view.getTabla().getrow
				}
				
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		//this.myArticulorDao.close();
		this.view.setVisible(false);
		//this.conexion.desconectar();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// maneja el evento de oprimir el bot�n del rat�n
		public void mousePressed( MouseEvent evento )
		{
			check(evento);
			checkForTriggerEvent( evento ); // comprueba el desencadenador
		} // fin del m�todo mousePressed
		
		// maneja el evento de liberaci�n del bot�n del rat�n
		public void mouseReleased( MouseEvent evento )
		{
			
			
			int r = view.getTabla().rowAtPoint(evento.getPoint());
	        if (r >= 0 && r < view.getTabla().getRowCount()) {
	        	view.getTabla().setRowSelectionInterval(r, r);
	        } else {
	        	view.getTabla().clearSelection();
	        }

	        int rowindex = view.getTabla().getSelectedRow();
	        if (rowindex < 0)
	            return;
	        
	        check(evento);
			checkForTriggerEvent( evento ); // comprueba el desencadenador
	        
		} // fin del m�todo mouseReleased
		
		// determina si el evento debe desencadenar el men� contextual
		private void checkForTriggerEvent( MouseEvent evento )
		{
			if ( evento.isPopupTrigger() )
				this.view.getMenuContextual().show(evento.getComponent(), evento.getX(), evento.getY() );
		} // fin del m�todo checkForTriggerEvent
		
		public void check(MouseEvent e)
		{ 
			if (e.isPopupTrigger()) { //if the event shows the menu 
				//this.view.getListCodigos().setSelectedIndex(this.view.getListCodigos().locationToIndex(e.getPoint())); //select the item 
				//view.getTabla().setColumnSelectionInterval(index0, index1); 
				//JOptionPane.showMessageDialog(view, "Donde se dio clip "+e.getPoint());
			} 
			
			
		}

}
