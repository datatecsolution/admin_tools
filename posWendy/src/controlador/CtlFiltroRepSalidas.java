package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Date;

import modelo.AbstractJasperReports;
import modelo.Conexion;
import modelo.ConexionStatic;
import modelo.Empleado;
import modelo.dao.EmpleadoDao;
import view.ViewFiltroSalidas;
import view.ViewListaEmpleados;

public class CtlFiltroRepSalidas implements ActionListener, KeyListener  {
	private ViewFiltroSalidas view;
	private Empleado myEmpleado=null;
	private EmpleadoDao myEmpleadoDao;
	
	
	public CtlFiltroRepSalidas(ViewFiltroSalidas v){
		
		view =v;
		view.conectarCtl(this);
		myEmpleadoDao=new EmpleadoDao();
		
		//se busca el empleado por defecto es con el id 0
		myEmpleado=myEmpleadoDao.buscarPorId(1);
		if(myEmpleado!=null){
			view.getTxtEmpleado().setText(myEmpleado.toString());
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
					
					try {
						
						AbstractJasperReports.createReportSalidasEmpleado(ConexionStatic.getPoolConexion().getConnection(),view.getBuscar1().getDate(), view.getBuscar2().getDate(),myEmpleado.getCodigo());
						
						AbstractJasperReports.showViewer(view);
					} catch (SQLException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
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
			buscarEmpleado();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void buscarEmpleado(){
		ViewListaEmpleados viewBuscarEmpleado=new ViewListaEmpleados(view);
		CtlEmpleadosListaBuscar ctBuscarEmpleado=new CtlEmpleadosListaBuscar(viewBuscarEmpleado);
		viewBuscarEmpleado.pack();
		boolean resultado=ctBuscarEmpleado.buscar();
		
		if(resultado){
			myEmpleado=ctBuscarEmpleado.getEmpleadoSelected();
			view.getTxtEmpleado().setText(myEmpleado.toString());
		}
		
	}

}
