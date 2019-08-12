

package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Principal;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;



/**
 * Clase que permite conectar con la base de datos
 * @author jdmayorga
 *
 */
public abstract class ConexionStatic {
	
	   
	  
	   
	   private static Usuario usuarioLogin=null;

	  
   
	   private static DataSource poolConexiones=null;
	  
   
	   private static boolean nivelFac=false;
   
   
	   public static void setNivelFac(boolean n){
		   nivelFac=n;
	   }
	   
	   public static void conectarBD(){
		   poolConexiones=setDataSource("mysql");
		   
	   }
   
   
   

	   public static boolean getNivelFact(){
		   return ConexionStatic.nivelFac;
	   }
	   
	   public static Usuario getUsuarioLogin(){
		   return ConexionStatic.usuarioLogin;
	   }
	   public static void setUsuarioLogin(Usuario u){
		   ConexionStatic.usuarioLogin=u;
		   
	   }
  
   
	   public static DataSource getPoolConexion(){
		   return ConexionStatic.poolConexiones;
	   }
   

	   public void setUsuario(Usuario u){
		   ConexionStatic.usuarioLogin=u;
	   }
   public boolean getConnectionStatus () {
       boolean conStatus = false;
       try {
           URL u = new URL("https://www.google.com/");
           HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
           huc.connect();
           conStatus = true;
       } catch (Exception e) { 
           conStatus = false;
       }        
       return conStatus;
   }
   
   
   private static DataSource setDataSource(String dbType){
	   
	   
       Properties props = new Properties();
      // File file = new File("/config/db.config");
       InputStream file=null;
       FileInputStream fis = null;
       //InputStream fis=null;
       BasicDataSource ds = new BasicDataSource();
       
       //fis=new FileInputStream(file);
       file=Principal.class.getResourceAsStream("/config/db.config"); 
       
	     try {
			props.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       if("mysql".equals(dbType)){
    	   
    	   ds.setDriverClassName(props.getProperty("MYSQL_DB_DRIVER_CLASS"));
           ds.setUrl("jdbc:mysql://"+props.getProperty("MYSQL_DB_URL")+":3306/");
           ds.setDefaultCatalog(props.getProperty("MYSQL_DB"));
           ds.setUsername(props.getProperty("MYSQL_DB_USERNAME"));
           ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
           
           /*ds.setDriverClassName(driver);
           ds.setUrl(url);
           ds.setUsername(login);
           ds.setPassword(password);*/
          // ds.setMinIdle(20);
           ds.setMaxActive(3);
           ds.setMaxIdle(3);
           ds.setMinIdle(3);
           ds.setInitialSize(3);
           
         
           ds.setInitialSize(3);
           ds.setValidationQuery("select 1;");
           //maxWait = 180000
        		   
        		   ds.setMaxWait(-1);
	       // minEvictableIdleTimeMillis = 1000 * 60 * 15
	        		
	        	ds.setMinEvictableIdleTimeMillis( 1000 * 60 * 15);
	     
	       // timeBetweenEvictionRunsMillis = 1000 * 60 * 15
	        		ds.setTimeBetweenEvictionRunsMillis(1000 * 60 * 15);
	       // numTestsPerEvictionRun = 3
	        		ds.setNumTestsPerEvictionRun(1);
	       // testOnBorrow = true
	        		ds.setTestOnBorrow(true);
	       // testWhileIdle = true
	        		ds.setTestWhileIdle(true);
	       // testOnReturn = false
	        		
	        ds.setTestOnReturn(true);
	    
       }
       else{
           return null;
       }
        
       return ds;
   }

}
