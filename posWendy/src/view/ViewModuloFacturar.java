package view;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import controlador.CtlCobro;
import controlador.CtlFacturarFrame;
import controlador.CtlModuloFacturar;
import controlador.CtlPagoProveedor;
import controlador.CtlSalidaCaja;
import modelo.Conexion;
import modelo.ConexionStatic;
import modelo.Usuario;
import view.botones.BotonAgregar;
import view.botones.BotonBuscarClientes;
import view.botones.BotonCantidad;
import view.botones.BotonCliente;
import view.botones.BotonDescuento;
import view.botones.BotonPrecio;
import view.botones.BotonProveedor;
import view.botones.BotonSalida;
import view.botones.BotonesApp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ViewModuloFacturar extends JFrame {
	
	private JDesktopPane elEscritorio;
	
	private List<ViewFacturarFrame> ventanas=new ArrayList<ViewFacturarFrame>();
	private BotonAgregar btnAgregar;
	private BotonSalida btnSalidas;
	private BotonCliente btnClientes;
	private BotonProveedor btnProveedores;
	public BotonesApp btnCaja;

	private BotonDescuento btnDescuento;

	private BotonPrecio btnPrecio;

	private BotonCantidad btnCantidad;

	public ViewModuloFacturar() {
		super( "Facturacion" );
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewFacturar.class.getResource("/view/recursos/logo-admin-tool1.png")));
		
		JMenuBar barra = new JMenuBar();
		setJMenuBar( barra ); // establece la barra de menús para esta aplicación
		

		elEscritorio = new JDesktopPane(); // crea el panel de escritorio
		getContentPane().add( elEscritorio ); // agrega el panel de escritorio al marco
		
		btnAgregar = new BotonAgregar();
		barra.add(btnAgregar);
		
		btnDescuento =new BotonDescuento();
		btnDescuento.addActionListener(new ActionListener() {
			

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				ViewFacturarFrame activo=(ViewFacturarFrame)elEscritorio.getSelectedFrame();
				if(activo!=null){
					KeyEvent key = new KeyEvent(btnDescuento, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_F7);
					activo.getCtl().keyPressed(key);
				}
			}
		});
		barra.add(btnDescuento);
		
		btnPrecio=new BotonPrecio();
		btnPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ViewFacturarFrame activo=(ViewFacturarFrame)elEscritorio.getSelectedFrame();
				if(activo!=null){
					KeyEvent key = new KeyEvent(btnPrecio, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_F8);
					activo.getCtl().keyPressed(key);
				}
			}
		});
		barra.add(btnPrecio);
		
		btnCantidad=new BotonCantidad();
		btnCantidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ViewFacturarFrame activo=(ViewFacturarFrame)elEscritorio.getSelectedFrame();
				if(activo!=null){
					KeyEvent key = new KeyEvent(btnCantidad, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_F9);
					activo.getCtl().keyPressed(key);
				}
			}
		});
		barra.add(btnCantidad);
		
		btnClientes = new BotonCliente();
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCobro viewCobro=new ViewCobro(null);
				CtlCobro ctlCobro=new CtlCobro(viewCobro);
				
				viewCobro.dispose();
				viewCobro=null;
				ctlCobro=null;
			}
		});
		barra.add(btnClientes);
		
		btnProveedores = new BotonProveedor();
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewPagoProveedor vPagoProveedores=new ViewPagoProveedor(null);
				vPagoProveedores.getCbFormaPago().setEnabled(false);
				CtlPagoProveedor cPagoProveedores=new CtlPagoProveedor(vPagoProveedores);
				
				vPagoProveedores.dispose();
				cPagoProveedores=null;
			}
		});
		barra.add(btnProveedores);
		
		btnSalidas = new BotonSalida();
		btnSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSalidaCaja viewSalida = new ViewSalidaCaja(null);
				CtlSalidaCaja ctlSalida = new CtlSalidaCaja(viewSalida);

				viewSalida.dispose();
				viewSalida = null;
				ctlSalida = null;
			}
		});
		barra.add(btnSalidas);
		
		
		
		btnCaja=new BotonesApp();
		btnCaja.setText(ConexionStatic.getUsuarioLogin().getCajaActiva().getDescripcion());
		barra.add(btnCaja);
		
		
		// establece componente de escucha para el elemento de menú nuevoMarco
		btnAgregar.addActionListener(

		new ActionListener() // clase interna anónima
		{
		 // muestra la nueva ventana interna
		 public void actionPerformed( ActionEvent evento )
		 {
			 // crea el marco interno
			 ViewFacturarFrame marco = new ViewFacturarFrame(
					 "Factura1", true, true, true, true );
			 CtlFacturarFrame ctlMarco=new CtlFacturarFrame(marco,ventanas);
			 
			 elEscritorio.add( marco ); // adjunta marco interno
			 
			 try {
				marco.setSelected(true);
				 //diz que a janela interna é maximizável   
				 marco.setMaximizable(true);   
		            //set o tamanho máximo dela, que depende da janela pai   
				 marco.setMaximum(true); 
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	           
			 ventanas.add(marco);
			 ctlMarco.actualizarVentanas();
			
	
		
		 
		 marco.setVisible( true ); // muestra marco interno
		 } // fin del método actionPerformed
		 } // fin de la clase interna anónima
		 ); // fin de la llamada a addActionListener
		
		
		setSize(this.getToolkit().getScreenSize());
		
		//this.setPreferredSize(new Dimension(1024, 600));
		//this.setResizable(false);
		//centrar la ventana en la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public void conectarContralador(CtlModuloFacturar c){
		btnAgregar.addActionListener(c);
		btnAgregar.setActionCommand("NUEVO");
		
	}
}

