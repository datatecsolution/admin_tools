


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import controlador.CtlLogin;
import controlador.CtlMenuPrincipal;
import controlador.CtlOrdenVenta;
import modelo.AbstractJasperReports;
import modelo.ConexionStatic;
import modelo.ConfigUserFacturacion;
import modelo.Usuario;
import modelo.dao.ConfigUserFactDao;
import view.ViewLogin;
import view.ViewMenuPrincipal;
import view.ViewModuloFacturar;
import view.ViewOrdeneVenta;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		//se cargan todos los reportes
		AbstractJasperReports.loadFileReport();
		
		//se establece la conecion a la base de datos
		ConexionStatic.conectarBD();
		
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		//System.out.print("HOLA MUNDO");
		
		
		ViewLogin viewLogin =new ViewLogin(); 
		CtlLogin ctlLogin=new CtlLogin(viewLogin);
		
		boolean login=ctlLogin.login();
		
		if(login){
			Usuario user=ConexionStatic.getUsuarioLogin();
		/*	
		if(user.getCajas()!=null)
		{
			
			for(int x=0;x<user.getCajas().size();x++){
				JOptionPane.showMessageDialog(viewLogin,x+" de "+ user.getCajas().size());
				JOptionPane.showMessageDialog(viewLogin, user.getCajas().get(x).toString());
			}
		}*/
			if(ConexionStatic.getUsuarioLogin().getTipoPermiso()==3){
							
							ViewOrdeneVenta vistaOrdenes=new ViewOrdeneVenta(null);
							CtlOrdenVenta ctlOrdenes=new CtlOrdenVenta(vistaOrdenes );
							
							//vistaOrdenes.pack();
							vistaOrdenes.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
							vistaOrdenes.setVisible(true);
							System.exit(0);
							//boolean resul=ctlFacturas.buscarCotizaciones(null);
				}
			if(ConexionStatic.getUsuarioLogin().getTipoPermiso()==1){
				//este manejador de subprocesos el cual permite ejecutar el metodo run de l ctl cada cierta cantida de tiempo
				ScheduledExecutorService scheduler= Executors.newSingleThreadScheduledExecutor();
				
				ConexionStatic.getUsuarioLogin().setCajasEmpty();
				ViewMenuPrincipal principal=new ViewMenuPrincipal();
				CtlMenuPrincipal ctl=new CtlMenuPrincipal(principal);
				principal.conectarControlador(ctl);
				principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//segunto inicial
				int initialDelay = 1;
				//cada cuantos segundos se ejecuta
		        int periodicDelay =1;
		        //se progrma la ejecucion pasandole el ctl el 
		        scheduler.scheduleAtFixedRate(ctl, initialDelay, periodicDelay,
		                TimeUnit.SECONDS     );
				/*
				ViewMenuPrincipal principal=new ViewMenuPrincipal();
				CtlMenuPrincipal ctl=new CtlMenuPrincipal(principal,conexion);
				principal.conectarControlador(ctl);*/
			}
			if(ConexionStatic.getUsuarioLogin().getTipoPermiso()==2){
				/*
				ViewFacturar vistaFacturar=new ViewFacturar(null);
				CtlFacturar ctlFacturar=new CtlFacturar(vistaFacturar,conexion );
				vistaFacturar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
				vistaFacturar.setVisible(true);
				System.exit(0);*/
				
				if(user.getCajas()!=null){
					//se recogen las configuraciones para el usuario 
					ConfigUserFactDao configDao=new ConfigUserFactDao();
					
					ConfigUserFacturacion conf=configDao.buscarPorUser(user.getUser());
					
					if(conf == null)
						user.setConfig(new ConfigUserFacturacion()) ;
					else 
						user.setConfig(conf);
					
					 ViewModuloFacturar marcoEscritorio = new ViewModuloFacturar();
					 //CtlModuloFacturar ctlMarcoEscritorio=new CtlModuloFacturar(marcoEscritorio,conexion);
					  marcoEscritorio.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				  //marcoEscritorio.setSize( 1000, 800 ); // establece el tamaño del marco
					  marcoEscritorio.setVisible( true ); // muestra el marco
				}else{
					JOptionPane.showMessageDialog(viewLogin, "No tiene cajas asignadas para poder facturar.","Error",JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		
			
		
		}
		
		

	}

}
