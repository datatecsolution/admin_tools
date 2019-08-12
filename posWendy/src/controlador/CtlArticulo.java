package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;




import java.util.List;

import javax.swing.JOptionPane;

import modelo.Articulo;
import modelo.dao.ArticuloDao;
import modelo.dao.PrecioArticuloDao;
import modelo.Cliente;
import modelo.CodBarra;
import modelo.Conexion;
import modelo.Impuesto;
import modelo.PrecioArticulo;
import modelo.dao.ImpuestoDao;
import modelo.Categoria;
import view.ViewCrearArticulo;
import view.ViewListaArticulo;
import view.ViewListaCategorias;

public class CtlArticulo extends MouseAdapter implements ActionListener,KeyListener,  WindowListener {
	
	
	public ViewCrearArticulo view;
	private ArticuloDao myArticuloDao;
	private Articulo myArticulo=new Articulo();
	private boolean resultaOperacion=false;
	private ImpuestoDao myImpuestoDao;
	private PrecioArticuloDao precioDao=null;

	
	public CtlArticulo(ViewCrearArticulo view, ArticuloDao a){
		

		
		this.view=view;
		this.myArticuloDao=a;
		precioDao= new PrecioArticuloDao();
		
		cargarTabla(precioDao.getTipoPrecios());
		cargarComboBox();
	}
	
	
	
	public void cargarTabla(List<PrecioArticulo> precios){
		//JOptionPane.showMessageDialog(view, articulos);
		this.view.getModeloPrecio().limpiar();
		for(int c=0;c<precios.size();c++){
			this.view.getModeloPrecio().agregarPrecio(precios.get(c));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String comando=e.getActionCommand();
		
		switch (comando){
		
		case "BUSCAR":
				ViewListaCategorias viewListaM=new ViewListaCategorias(view);
				CtlCategoriaBuscar ctl=new CtlCategoriaBuscar(viewListaM);
				viewListaM.conectarControladorBusqueda(ctl);
				//se crea una marca y se llena con la busqueda que selecciona el usuario
				Categoria myMarca=ctl.buscarMarca();
				
			//se compara si el usuario selecciono un marca
				if(myMarca.getDescripcion()!=null && myMarca.getId()!=0){
					//se pasa la marca buscada y selecciona al nuevo articulo
					myArticulo.setMarcaObj(myMarca);
					
					//se muestra el nombre de la marca en la caja de texto
					view.getTxtMarca().setText(myMarca.getDescripcion());
				
				}else{
					JOptionPane.showMessageDialog(this.view,"No seleciono una Categoria");
				}
			break;
		case "GUARDAR":
			//filtrar los codigos de barra del articulo
			if(this.view.getModeloCodBarra().getSize()==0){
				int resul=JOptionPane.showConfirmDialog(view, "Desea guardar un articulos sin condigos de barra?");
				if(resul==0){
					guardarArticulo();
					this.view.dispose();
				}
			}else{
				guardarArticulo();
				this.view.dispose();
			}
			
			
			break;
		case "NUEVOCODIGO":
			CodBarra newCodigo=new CodBarra();
			newCodigo.setCodigoBarra(this.view.getTxtCodigo().getText());
			newCodigo.setCodArticulo(0);
			this.view.getModeloCodBarra().addCodBarra(newCodigo);
			this.view.getTxtCodigo().setText("");
			break;
		case "ELIMINARCODIGO":
				//se obtiene el index del codigo selecionado
				int indexCodigoSelecionado=this.view.getListCodigos().getSelectedIndex();
				
				//con el index del codigo seleccionado se obtiene el objeto codigo selecionado			
				CodBarra cod= this.view.getModeloCodBarra().getCodBarra(indexCodigoSelecionado);
				
				//se pregunta si en verdad se quiere borrar el codigo de bgarra
				int confirmacion=JOptionPane.showConfirmDialog(view, "Desea eliminar el codigo de barra "+cod+" ?");
				
				// si se confirma la eliminacion se procede a eliminar
				if(confirmacion==0){
					
					//se eliminar el codigo en la view list y se coloca el eliminado en una lista especial para eliminar de la bd
					this.view.getModeloCodBarra().eliminarCodBarra(indexCodigoSelecionado);
					
					
				}
				
				
			break;
		case "ACTUALIZAR":
			cargarDatosArticuloView();
				//se actulizar el articulo en la base de datos
				if(myArticuloDao.actualizarArticulo(myArticulo,this.view.getModeloCodBarra().getCodsElimnar())){
					JOptionPane.showMessageDialog(view, "Se Actualizo el articulo");
					resultaOperacion=true;
					this.view.dispose();
				}
			break;
		case "CANCELAR":
			//cargarDatosArticuloView();
			//JOptionPane.showMessageDialog(view, this.view.getCbxTipo().getSelectedIndex());
			this.view.setVisible(false);
			
			break;
		}
		
	}
	
	/*<<<<<<<<<<<<<<<<<<<<<<<<< metodo que devuelve el articulo guardado >>>>>>>>>>>>>>>>>>>>>>>>>         */
	public Articulo getArticuloGuardado(){
		return myArticulo;
		
		
	}
	
	public void guardarArticulo(){
		
		cargarDatosArticuloView();
		
		boolean existeCodigo=false;
		
		//se verificara que no exista otro articulo con el mismo codigo de barra
		//se recorre la lista de codigo de barra en busqueda de duplicados
		for(int x=0; x<view.getModeloCodBarra().getSize();x++){
			
			//se obtiene el primer codigo de barra del la lista
			String codigo=view.getModeloCodBarra().getCodBarra(x).getCodigoBarra();
			
			//se busca en la base de datos por el codigo de barra
			Articulo articuloBusqueda=myArticuloDao.buscarArticuloBarraCod(codigo);
			
			//si nos retorna un articulo significa que existe el codigo de barra
			if(articuloBusqueda!=null){
				
				//se estable que si exite el codigo de barra
				existeCodigo=true;
				//JOptionPane.showMessageDialog(view, "El codigo de barra '"+codigo+"' ya esta asignado al articulo.\n"+articuloBusqueda.toString());
				JOptionPane.showMessageDialog(view, "El codigo de barra '"+codigo+"' ya esta asignado al articulo.\n"+articuloBusqueda.toString(), "Error al guardar articulo", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		//se verifica que en la busqueda no se encontro un codigo de barra igual
		if(existeCodigo==false){
		
			//se ejecuta la accion de guardar
			if(myArticuloDao.registrar(myArticulo)){
				
				JOptionPane.showMessageDialog(this.view, "Se ha registrado Exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
				myArticulo.setId(myArticuloDao.getIdArticuloRegistrado());//se completa el proveedor guardado con el ID asignado por la BD
				resultaOperacion=true;
				this.view.setVisible(false);
				//this.myArticuloDaoRemote.registrarArticulo(getArticulo());
				
				
			}
			else{
				JOptionPane.showMessageDialog(this.view, "No se Registro","Error",JOptionPane.ERROR_MESSAGE);
				resultaOperacion=false;
				this.view.setVisible(false);
			}
		}//fin verificacion de codigos de barras
		else{
			JOptionPane.showMessageDialog(view, "No se guardo el articulo, ","Error al guardar articulo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cargarDatosArticuloView(){
		//Se establece el nombre del articulo
		myArticulo.setArticulo(this.view.getTxtNombre().getText());
		
		//Se establece el impuesto seleccionado
		Impuesto imp= (Impuesto) this.view.getCbxImpuesto().getSelectedItem();
		myArticulo.setImpuestoObj(imp);
		
		//JOptionPane.showMessageDialog(view, this.view.getCbxTipo().getSelectedItem());
		//Se establece los codigos de barra
		myArticulo.setCodBarras(this.view.getModeloCodBarra().getCodsBarras());
		
		//se establece el precion de articulo
		//myArticulo.setPrecioVenta(Double.parseDouble(this.view.getTxtPrecio().getText()));
		
		//se Estable los precios de ventas del articulo
		myArticulo.setPreciosVenta(view.getModeloPrecio().getPrecios());
		
		int x=this.view.getCbxTipo().getSelectedIndex();
		if(x==0){
			myArticulo.setTipoArticulo(1);
		}
		if(x==1){
			myArticulo.setTipoArticulo(2);
		}
	}
	
	
	public boolean agregarArticulo(){
		view.setVisible(true);
		return resultaOperacion;
	}
	
	
	public boolean actualizarArticulo(Articulo a){
		//se carga la configuracionde la view articulo para la actulizacion
		this.view.configActualizar();
		
		
		//se establece el nombre de articulo en la view
		this.view.getTxtNombre().setText(a.getArticulo());
		
		//se establece la marca en la view
		this.view.getTxtMarca().setText(a.getMarcaObj().getDescripcion());
		
		//si el articulo tiene una lista de codigos de barra se incluyen en la view
		if(a.getCodBarra()!=null){
			this.view.getModeloCodBarra().setCodsBarra(a.getCodBarra());
		}
		
		
		//se consigue el index del impuesto del articulo
		int indexImpuesto=this.view.getListaCbxImpuesto().buscarImpuesto(a.getImpuestoObj());
		//se selecciona el impuesto apropiado para el articulo
		this.view.getCbxImpuesto().setSelectedIndex(indexImpuesto);
		
		
		//se establece el articulo de la clase this
		myArticulo=a;
		
		//se estable el precio del articulo en la view
		//this.view.getTxtPrecio().setText(""+a.getPrecioVenta());
		
		//se estable el tipo de articulo
		if(a.getTipoArticulo()==1){
			this.view.getCbxTipo().setSelectedIndex(0);
		}
		if(a.getTipoArticulo()==2){
			this.view.getCbxTipo().setSelectedIndex(1);
		}
		//se cargar los precios de los articulos si los hay
		//this.cargarTabla(this.precioDao.getPreciosArticulo(myArticulo.getId()));
		List<PrecioArticulo> preciosArticulo=precioDao.getPreciosArticulo(myArticulo.getId());
		
		
		List<PrecioArticulo> precios=precioDao.getTipoPrecios();
		
		//se estable el valor de los tipos de precios que se encontraron en la base de datos
		if(preciosArticulo!=null && precios!=null){
			
			//se recorren todos los precios
			for(int y=0;y<precios.size();y++){
				//se recorren los precios de los articulos en busca algun valor para el tipo de precio
				for(int xx=0;xx<preciosArticulo.size();xx++){
					if(precios.get(y).getCodigoPrecio()==preciosArticulo.get(xx).getCodigoPrecio()){
						precios.get(y).setPrecio(preciosArticulo.get(xx).getPrecio());
						
					}
				}
			}
		
			this.cargarTabla(precios);
		}
		
		
				
		// se hace visible la ventana modal
		this.view.setVisible(true);
		
		
		
		return this.resultaOperacion;
	}
	
	public Articulo getArticulo(){
		return myArticulo;
	}
	
	private void cargarComboBox(){
		//se crea el objeto para obtener de la bd los impuestos
		myImpuestoDao=new ImpuestoDao();
	
		//se obtiene la lista de los impuesto y se le pasa al modelo de la lista
		this.view.getListaCbxImpuesto().setLista(myImpuestoDao.todoImpuesto());
		
		
		//se remueve la lista por defecto
		this.view.getCbxImpuesto().removeAllItems();
	
		this.view.getCbxImpuesto().setSelectedIndex(1);
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
			this.view.getListCodigos().setSelectedIndex(this.view.getListCodigos().locationToIndex(e.getPoint())); //select the item 
			//menuContextual.show(listCodigos, e.getX(), e.getY()); //and show the menu 
		} 
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
		this.view.setVisible(false);
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
	
}
