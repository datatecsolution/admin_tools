package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.AbstractJasperReports;
import modelo.Cliente;
import modelo.Conexion;
import modelo.ConexionStatic;
import modelo.Empleado;
import modelo.dao.ClienteDao;
import modelo.dao.EmpleadoDao;
import view.ViewFiltroPagos;
import view.ViewFiltroSalidas;
import view.ViewListaClientes;
import view.ViewListaEmpleados;

public class CtlFiltroRepPagos implements ActionListener, KeyListener  {
	private ViewFiltroPagos view;
	private Cliente myCliente=null;
	private ClienteDao myClienteDao;
	
	
	public CtlFiltroRepPagos(ViewFiltroPagos v){
		
		view =v;
		view.conectarCtl(this);
		myClienteDao=new ClienteDao();
		
		//se busca el empleado por defecto es con el id 0
		myCliente=myClienteDao.buscarPorId(1);
		if(myCliente!=null){
			view.getTxtCliente().setText(myCliente.getNombre());
			}
		Date horaLocal=new Date();
		view.getBuscar1().setDate(horaLocal);
		view.getBuscar2().setDate(horaLocal);
		view.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		String comando=e.getActionCommand();
		
		switch(comando){
		
			case "GENERAR":
					
				
				if(myCliente!=null)
				{
				
						try {
							
							AbstractJasperReports.createReportPagoCliente(ConexionStatic.getPoolConexion().getConnection(),view.getBuscar1().getDate(), view.getBuscar2().getDate(),myCliente.getId());
							
							AbstractJasperReports.showViewer(view);
						} catch (SQLException ee) {
							// TODO Auto-generated catch block
							ee.printStackTrace();
							
							JOptionPane.showMessageDialog(view, "No se puede mostrar el reporte.","Error de reporte",JOptionPane.ERROR_MESSAGE);
						}
				}else{
					JOptionPane.showMessageDialog(view, "Debe seleccionar un cliente priemero. Utilice F1 para buscar el cliente.","Error validacion.",JOptionPane.ERROR_MESSAGE);
				}
					
			break;
		}
		
	
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_F1:
			buscarCliente();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void buscarCliente(){
		
		ViewListaClientes viewBuscarCliente=new ViewListaClientes(view);
		CtlClienteBuscar ctBuscarCliente=new CtlClienteBuscar(viewBuscarCliente);
		viewBuscarCliente.pack();
		
		boolean resultado=ctBuscarCliente.buscarCliente(view);
		
		if(resultado){
			myCliente=ctBuscarCliente.getCliente();
			view.getTxtCliente().setText(myCliente.getNombre());
		}
		
	}

}
