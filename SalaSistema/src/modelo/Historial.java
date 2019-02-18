package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Historial{
	
	private IntegerProperty idHistorial;
	private IntegerProperty idUsuario;
	private Date fechaHora;
	private StringProperty Detalle;

	public Historial( int idUsuario, String Detalle) { 
		Date fecha = new Date();
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
		this.fechaHora = fecha;
		this.Detalle = new SimpleStringProperty(Detalle);
	}

	//Metodos atributo: idHistorial
	public int getIdHistorial() {
		
		return idHistorial.get();
		
	}
	
	public void setIdHistorial(int idHistorial) {
		
		this.idHistorial = new SimpleIntegerProperty(idHistorial);
		
	}
	
	public IntegerProperty IdHistorialProperty() {
		return idHistorial;
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
	
	//Metodos atributo: fechaHora
	public Date getFechaHora() {
		
		return fechaHora;
		
	}
	
	public void setFechaHora(Date fechaHora) {
		
		this.fechaHora = fechaHora;
		
	}
	
	//Metodos atributo: Detalle
	public String getDetalle() {
		
		return Detalle.get();
		
	}
	
	public void setDetalle(String Detalle) {
		
		this.Detalle = new SimpleStringProperty(Detalle);
		
	}
	
	public StringProperty DetalleProperty() {
		
		return Detalle;
		
	}
	
	public int grabarHistoria(Connection connection, Historial historiaRegistrada) {
		
		String registroHistoria = "El usuario ID: " + String.valueOf(historiaRegistrada.getIdUsuario()) + " "+ historiaRegistrada.getDetalle() + " Fue el día: " + historiaRegistrada.getFechaHora() + ".";
		
		try {
			
			PreparedStatement instruccion = connection.prepareStatement("INSERT INTO public.\"Historial\"( detalle, \"idUsuario\", \"fechaHora\")VALUES ( ?, ?, ?);");
			
			
			
			
			String pattern = "EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("da", "DK"));
            String date = simpleDateFormat.format(new Date());
			
			 
			 
			//Elementos parametrizados
			instruccion.setString(1, registroHistoria);
			instruccion.setInt(2, historiaRegistrada.getIdUsuario());
			instruccion.setString(3, date);
			
			
			
			//Ejecutar
			return instruccion.executeUpdate();
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al grabar en el historial, verifique la conexión a la base de datos.");
			e.printStackTrace();
		}
		
		return 0;
	}
	
}