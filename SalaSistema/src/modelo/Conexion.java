package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	
	private Connection connection;
    String url ="jdbc:postgresql://192.168.1.37:5454/salasistema";
	private String usuario = "administrador";
	private String contrasena = "administracioncentral";
	
	
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void establecerConexion() {
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, usuario, contrasena);
			
		} catch (ClassNotFoundException e) {
			
            JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXIÓN A LA BASE DE DATOS.");
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "ERROR EN EL CONTROLADOR DE LA BASE DE DATOS.");
			e.printStackTrace();
			
		}
	
	}
	
	//Para no sobrepasar el umbral del gestor de la base de datos.
	public void cerrarConexion() {
		try {
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CERRAR LA CONEXIÓN CON LA BASE DE DATOS.");
			e.printStackTrace();
		}
	}
	
}
