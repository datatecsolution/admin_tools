package controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.ViewFiltroRepSarVentas;
import modelo.AbstractJasperReports;
import modelo.Caja;
import modelo.Conexion;
import modelo.ConexionStatic;
import modelo.dao.CajaDao;

public class CtlFiltroRepSarVentas implements ActionListener {
	
	private ViewFiltroRepSarVentas view;
	private CajaDao cajaDao;

	public CtlFiltroRepSarVentas(ViewFiltroRepSarVentas v) {
		// TODO Auto-generated constructor stub
		
		view =v;
		view.conectarCtl(this);
		
		cajaDao=new CajaDao();
		cargarComboBox();
		
		
		view.setVisible(true);
	}
	
	private void cargarComboBox(){
		//se crea el objeto para obtener de la bd los impuestos
		//myImpuestoDao=new ImpuestoDao(conexion);
	
		//se obtiene la lista de los impuesto y se le pasa al modelo de la lista
		this.view.getModeloListaCajas().setLista(this.cajaDao.todosVector());
		
		
		//se remueve la lista por defecto
		this.view.getCbxCajas().removeAllItems();
	
		this.view.getCbxCajas().setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando=e.getActionCommand();
		
		switch(comando){
		case "GENERAR":
			
			Caja myCaja=(Caja)view.getCbxCajas().getSelectedItem();

			int mes=view.getMes().getMonth()+1;
			int anio=view.getAnio().getYear();
			
			//JOptionPane.showMessageDialog(null, mes+" Hizo clik en el boton "+anio);
			//AbstractJasperReports.loadFileReport();
			
			try {
				//panel2.setVisible(false);
				//panel2.d
				//elEscritorio.setVisible(false);
				AbstractJasperReports.createReportSarVentas(ConexionStatic.getPoolConexion().getConnection(),mes, anio, ConexionStatic.getUsuarioLogin().getUser(),myCaja.getNombreBd());
				
				AbstractJasperReports.showViewer(view);
			} catch (SQLException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			}
		
			break;
		}
	}

}
