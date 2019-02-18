package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario{
	private IntegerProperty idUsuario;
	private StringProperty usuario;
	private StringProperty contrasena;
	private StringProperty nombreCompleto;
	private StringProperty telefono;
	private StringProperty correoElectronico;
	private IntegerProperty Categoria_idCategoria;

	public Usuario(int idUsuario, String usuario, String contrasena, String nombreCompleto, String telefono, String correoElectronico, int Categoria_idCategoria) { 
		
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
		this.usuario = new SimpleStringProperty(usuario);
		this.contrasena = new SimpleStringProperty(contrasena);
		this.nombreCompleto = new SimpleStringProperty(nombreCompleto);
		this.telefono = new SimpleStringProperty(telefono);
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
		this.Categoria_idCategoria = new SimpleIntegerProperty(Categoria_idCategoria);
		
	}

	//Metodos atributo: idUsuario
	public int getIdUsuario() {
		return idUsuario.get();
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
	}
	
	public IntegerProperty IdUsuarioProperty() {
		return idUsuario;
	}
	
	//Metodos atributo: usuario
	public String getUsuario() {
		return usuario.get();
	}
	
	public void setUsuario(String usuario) {
		this.usuario = new SimpleStringProperty(usuario);
	}
	
	public StringProperty UsuarioProperty() {
		return usuario;
	}
	
	//Metodos atributo: contrasena
	public String getContrasena() {
		return contrasena.get();
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = new SimpleStringProperty(contrasena);
	}
	
	public StringProperty ContrasenaProperty() {
		return contrasena;
	}
	
	//Metodos atributo: nombreCompleto
	public String getNombreCompleto() {
		return nombreCompleto.get();
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = new SimpleStringProperty(nombreCompleto);
	}
	
	public StringProperty NombreCompletoProperty() {
		return nombreCompleto;
	}
	
	//Metodos atributo: telefono
	public String getTelefono() {
		return telefono.get();
	}
	
	public void setTelefono(String telefono) {
		this.telefono = new SimpleStringProperty(telefono);
	}
	
	public StringProperty TelefonoProperty() {
		return telefono;
	}
	
	//Metodos atributo: correoElectronico
	public String getCorreoElectronico() {
		return correoElectronico.get();
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
	}
	
	public StringProperty CorreoElectronicoProperty() {
		return correoElectronico;
	}
	
	//Metodos atributo: Categoria_idCategoria
	public int getCategoria_idCategoria() {
		return Categoria_idCategoria.get();
	}
	
	public void setCategoria_idCategoria(int Categoria_idCategoria) {
		this.Categoria_idCategoria = new SimpleIntegerProperty(Categoria_idCategoria);
	}
	
	public IntegerProperty Categoria_idCategoriaProperty() {
		return Categoria_idCategoria;
	}
	
	
	//METODO PARA INICIAR SESION
	public static boolean iniciarSesion(Connection connection, String usuario, String contrasena) {
			
		boolean estadoLogueo = false;
		
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("SELECT \"idUsuario\", usuario, contrasena, \"nombreCompleto\", telefono, \"correoElectronico\","
					+ " \"Categoria_idCategoria\" FROM public.\"Usuario\" WHERE usuario = '"+usuario+"' AND contrasena = '"+contrasena+"';");
			
			while (resultado.next()){
				
				Historial historiaRegistrada = new Historial(resultado.getInt("idUsuario"), ", nombre: " + resultado.getString("usuario") + ", inicio sesión.");
				historiaRegistrada.grabarHistoria(connection, historiaRegistrada);
				estadoLogueo=true;
			}
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse con el servidor  para buscar el usuario en la base de datos.");
			e.printStackTrace();
			
		}
		
		return estadoLogueo;
		
	}
	
	
}