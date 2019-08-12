package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import modelo.Caja;
import modelo.Categoria;
import modelo.CodBarra;
import modelo.Conexion;
import modelo.ConexionStatic;
import modelo.ConfigUserFacturacion;
import modelo.Departamento;
import modelo.Empleado;
import modelo.Requisicion;
import modelo.Usuario;

public class ConfigUserFactDao extends ModeloDaoBasic {
	private String sqlBaseJoin=null;

	public ConfigUserFactDao() {
		super("config_user_facturacion", "id");
		
	}

	@Override
	public boolean eliminar(Object c) {
		// TODO Auto-generated method stub
		Caja caja=(Caja)c;
		
		//int resultado=0;
		Connection conn=null;
		
		try {
			conn=ConexionStatic.getPoolConexion().getConnection();
			super.psConsultas=conn.prepareStatement(super.getQueryDelete()+" WHERE id= ?");
			super.psConsultas.setInt( 1, caja.getCodigo() );
			psConsultas.executeUpdate();
			return true;
			
		} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error en la base de datos",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false;
		}
		finally{
				try{
								
					//if(res != null) res.close();
	                if(psConsultas != null)psConsultas.close();
	                if(conn != null) conn.close();
	                
					
					} // fin de try
					catch ( SQLException excepcionSql )
					{
						excepcionSql.printStackTrace();
						//Sconexion.desconectar();
					} // fin de catch
		}
		//return false;
	}

	@Override
	public boolean registrar(Object c) {
		// TODO Auto-generated method stub
		ConfigUserFacturacion config=(ConfigUserFacturacion)c;
		ResultSet rs=null;
		
		//int resultado=0;
		Connection conn=null;
		
		try {
			conn=ConexionStatic.getPoolConexion().getConnection();
			super.psConsultas=conn.prepareStatement(super.getQueryInsert()+"(usuario,formato_factura,ventana_vendedor,pwd_descuento,pwd_precio,descuento_porcentaje,ventana_observaciones,precio_redondiar,facturar_sin_inventario,impr_report_categ_cierre,impr_report_salida,show_report_salida,impr_report_entrada,show_report_entrada,activar_busqueda_facturacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			super.psConsultas.setString(1, config.getUsuario());
			super.psConsultas.setString(2, config.getFormatoFactura());
			super.psConsultas.setBoolean( 3, config.isVentanaVendedor());
			super.psConsultas.setBoolean( 4, config.isPwdDescuento());
			super.psConsultas.setBoolean( 5, config.isPwdPrecio());
			super.psConsultas.setBoolean( 6, config.isDescPorcentaje());
			super.psConsultas.setBoolean( 7, config.isVentanaObservaciones());
			super.psConsultas.setBoolean(8, config.isPrecioRedondear());
			
			super.psConsultas.setBoolean(9, config.isFacturarSinInventario());
			super.psConsultas.setBoolean(10, config.isImprReportCategCierre());
			super.psConsultas.setBoolean(11, config.isImprReportSalida());
			super.psConsultas.setBoolean(12, config.isShowReportSalida());
			super.psConsultas.setBoolean(13, config.isImprReportEntrada());
			super.psConsultas.setBoolean(14, config.isShowReportEntrada());
			super.psConsultas.setBoolean(15, config.isActivarBusquedaFacturacion());
			
			

		
			
			psConsultas.executeUpdate();
			
			
			rs=psConsultas.getGeneratedKeys(); //obtengo las ultimas llaves generadas
			while(rs.next()){
				config.setId(rs.getInt(1));
				
				
			}
			
			return true;
			
		} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error en la base de datos",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false;
		}
		finally{
				try{
								
					//if(res != null) res.close();
	                if(psConsultas != null)psConsultas.close();
	                if(conn != null) conn.close();
	                
					
					} // fin de try
					catch ( SQLException excepcionSql )
					{
						excepcionSql.printStackTrace();
						//Sconexion.desconectar();
					} // fin de catch
		}
	}

	@Override
	public boolean actualizar(Object c) {
		// TODO Auto-generated method stub
		ConfigUserFacturacion config=(ConfigUserFacturacion)c;
		
		//int resultado=0;
		Connection conn=null;
		
		try {
			
			//JOptionPane.showMessageDialog(null, "Datos Caja en modelo dao:"+caja.toString(),"Exito",JOptionPane.INFORMATION_MESSAGE);
			conn=ConexionStatic.getPoolConexion().getConnection();
			super.psConsultas=conn.prepareStatement(super.getQueryUpdate()+" SET formato_factura=?,ventana_vendedor=?,pwd_descuento=?,pwd_precio=?,descuento_porcentaje=?,ventana_observaciones=?, precio_redondiar=? ,facturar_sin_inventario=?,impr_report_categ_cierre=?,impr_report_salida=?,show_report_salida=?,impr_report_entrada=?,show_report_entrada=?,activar_busqueda_facturacion=? WHERE usuario=?");
			super.psConsultas.setString(1, config.getFormatoFactura());
			super.psConsultas.setBoolean( 2, config.isVentanaVendedor());
			super.psConsultas.setBoolean( 3, config.isPwdDescuento());
			super.psConsultas.setBoolean( 4, config.isPwdPrecio());
			super.psConsultas.setBoolean( 5, config.isDescPorcentaje());
			super.psConsultas.setBoolean( 6, config.isVentanaObservaciones());
			
			super.psConsultas.setBoolean(7, config.isPrecioRedondear());
			
			super.psConsultas.setBoolean(8, config.isFacturarSinInventario());
			super.psConsultas.setBoolean(9, config.isImprReportCategCierre());
			super.psConsultas.setBoolean(10, config.isImprReportSalida());
			super.psConsultas.setBoolean(11, config.isShowReportSalida());
			super.psConsultas.setBoolean(12, config.isImprReportEntrada());
			super.psConsultas.setBoolean(13, config.isShowReportEntrada());
			super.psConsultas.setBoolean(14, config.isActivarBusquedaFacturacion());
			
			super.psConsultas.setString(15, config.getUsuario());
			psConsultas.executeUpdate();
			return true;
			
		} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error en la base de datos",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return false;
		}
		finally{
				try{
								
					//if(res != null) res.close();
	                if(psConsultas != null)psConsultas.close();
	                if(conn != null) conn.close();
	                
					
					} // fin de try
					catch ( SQLException excepcionSql )
					{
						excepcionSql.printStackTrace();
						//Sconexion.desconectar();
					} // fin de catch
		}
	}
	@Override
	public List<ConfigUserFacturacion> todos(int limInf, int limSupe) {
		// TODO Auto-generated method stub
		List<ConfigUserFacturacion> configs=new ArrayList<ConfigUserFacturacion>();
		
		ResultSet res=null;
		
		Connection conn=null;
		
		boolean existe=false;
		
		try {
			
			
			conn=ConexionStatic.getPoolConexion().getConnection();
			super.psConsultas = conn.prepareStatement(super.getQueryRecord());
			super.psConsultas.setInt(1, limSupe);
			super.psConsultas.setInt(2, limInf);
			//System.out.println(psConsultas);
			res = super.psConsultas.executeQuery();
			
			while(res.next()){
				ConfigUserFacturacion config=new ConfigUserFacturacion();
				
				config.setId(res.getInt("id"));
				config.setUsuario(res.getString("usuario"));
				config.setFormatoFactura(res.getString("formato_factura"));
				config.setVentanaVendedor(res.getBoolean("ventana_vendedor"));
				config.setPwdDescuento(res.getBoolean("pwd_descuento"));
				config.setPwdPrecio(res.getBoolean("pwd_descuento"));
				config.setDescPorcentaje(res.getBoolean("descuento_porcentaje"));
				config.setVentanaObservaciones(res.getBoolean("ventana_observaciones"));
				config.setPrecioRedondear(res.getBoolean("precio_redondiar"));
				config.setFacturarSinInventario(res.getBoolean("facturar_sin_inventario"));
				config.setImprReportCategCierre(res.getBoolean("impr_report_categ_cierre"));
				config.setImprReportSalida(res.getBoolean("impr_report_salida"));
				config.setShowReportSalida(res.getBoolean("show_report_salida"));
				config.setImprReportEntrada(res.getBoolean("impr_report_entrada"));
				config.setShowReportEntrada(res.getBoolean("show_report_entrada"));
				config.setActivarBusquedaFacturacion(res.getBoolean("activar_busqueda_facturacion"));
				
				configs.add(config);
				existe=true;
			 }
			//res.close();
			//conexion.desconectar();
					
					
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error en la base de datos",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		finally
		{
			try{
				if(res != null) res.close();
	            if(super.psConsultas != null)super.psConsultas.close();
	            if(conn != null) conn.close();
				
				} // fin de try
				catch ( SQLException excepcionSql )
				{
					excepcionSql.printStackTrace();
					//conexion.desconectar();
				} // fin de catch
		} // fin de finally
		
			if (existe) {
				return configs;
			}
			else return null;
	}

	

	


		public ConfigUserFacturacion buscarPorUser(String user) {
			// TODO Auto-generated method stub
			ConfigUserFacturacion config=new ConfigUserFacturacion();
			Connection con = null;
			ResultSet res=null;
			
			boolean existe=false;
			
			try {
				con = ConexionStatic.getPoolConexion().getConnection();
				
				//psConsultas = con.prepareStatement(super.getQuerySelect()+" where v_requisiciones.codigo_requisicion=?;");
				psConsultas = con.prepareStatement(super.getQuerySearch("usuario", "="));
				psConsultas.setString(1, user);
				psConsultas.setInt(2, 0);
				psConsultas.setInt(3, 1);
				res = psConsultas.executeQuery();
				while(res.next()){
					existe=true;
					
					config.setId(res.getInt("id"));
					config.setUsuario(res.getString("usuario"));
					config.setFormatoFactura(res.getString("formato_factura"));
					config.setVentanaVendedor(res.getBoolean("ventana_vendedor"));
					config.setPwdDescuento(res.getBoolean("pwd_descuento"));
					config.setPwdPrecio(res.getBoolean("pwd_descuento"));
					config.setDescPorcentaje(res.getBoolean("descuento_porcentaje"));
					config.setVentanaObservaciones(res.getBoolean("ventana_observaciones"));
					config.setPrecioRedondear(res.getBoolean("precio_redondiar"));
					config.setPwdPrecio(res.getBoolean("pwd_precio"));
					
					config.setFacturarSinInventario(res.getBoolean("facturar_sin_inventario"));
					config.setImprReportCategCierre(res.getBoolean("impr_report_categ_cierre"));
					config.setImprReportSalida(res.getBoolean("impr_report_salida"));
					config.setShowReportSalida(res.getBoolean("show_report_salida"));
					config.setImprReportEntrada(res.getBoolean("impr_report_entrada"));
					config.setShowReportEntrada(res.getBoolean("show_report_entrada"));
					config.setActivarBusquedaFacturacion(res.getBoolean("activar_busqueda_facturacion"));
					
					
					
					
				 }
						
				} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Error en la base de datos",JOptionPane.ERROR_MESSAGE);
						System.out.println(e);
				}
			finally
			{
				try{
					
					if(res != null) res.close();
	                if(psConsultas != null)psConsultas.close();
	                if(con != null) con.close();
	                
					
					} // fin de try
					catch ( SQLException excepcionSql )
					{
						excepcionSql.printStackTrace();
						//conexion.desconectar();
					} // fin de catch
			} // fin de finally
			
			
			if (existe) {
				return config;
			}
			else return null;
		}

		@Override
		public Object buscarPorId(int id) {
			// TODO Auto-generated method stub
			return null;
		}

	

		

}
