package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Conexion;
import view.ViewFacturarFrame;
import view.ViewModuloFacturar;

public class CtlModuloFacturar  implements ActionListener{
	
	private ViewModuloFacturar view;
	private Conexion conexion;
	
	public CtlModuloFacturar(ViewModuloFacturar v,Conexion conn){
		view=v;
		conexion=conn;
		view.conectarContralador(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando=e.getActionCommand();
		switch(comando){
		
			case "NUEVO":
					// crea el marco interno
					 ViewFacturarFrame marco = new ViewFacturarFrame(
							 "Factura1", true, true, true, true );
					// CtlFacturarFrame ctlMarco=new CtlFacturarFrame(marco,conexion);
					 
					 view.add( marco ); // adjunta marco interno
					 marco.setVisible( true ); // muestra marco interno
					 view.repaint();
				break;
		}
		
	}

}
