package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Conexion;
import modelo.Historial;
import modelo.Usuario;

//ESTE ES EL ELEMENTO DONDE SE DEFINEN LAS ACCIONES DEL USUARIO, DESDE ACA SE HACE REFERENCIA A LOS ELEMENTOS DE LA INTERFAZ GRAFICA DE USUARIO DE JAVAFX A TRAVES DE 
//LA ETIQUETA @FXML.


public class IniciarSesionController {
	
	
	private Conexion conexion;
	
	
	
	
	@FXML
	private Button btnEntrar;//Hay que enlazar este objeto boton con la interfaz grafica de usaurio en scene builder.
	//En Controller hay que enlazarlo con este archivo, luego en fx:id colocar btnEntrar como nombre desde la lista que se despliega.
	@FXML private TextField txtUsuario;
	@FXML private PasswordField txtPassword;
	
	
	
	@FXML
	public void iniciarSesionBoton() {
		
		btnEntrar.setDisable(true);
		
		boolean estadoLogeo;
		conexion = new Conexion();
		conexion.establecerConexion();
		estadoLogeo=Usuario.iniciarSesion(conexion.getConnection(), txtUsuario.getText(), txtPassword.getText());
		
		if(estadoLogeo) { 
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Inicio de sesión correcto");
			mensaje.setContentText("Bienvenido al sistema.");
			mensaje.setHeaderText("usted esta logeado con el nombre de usuario: " + txtUsuario.getText());
			mensaje.show();
			btnEntrar.setDisable(false);
		}else {
			Historial historiaRegistrada = new Historial(1, "Error al intentar acceder al sistema con el Usuario: " + txtUsuario.getText());
			historiaRegistrada.grabarHistoria(conexion.getConnection(), historiaRegistrada);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Falló el inicio de sesión");
			mensaje.setContentText("Usuario o contraseña incorrectos.");
			mensaje.setHeaderText("intente nuevamente por favor.");
			mensaje.show();
			btnEntrar.setDisable(false);
			
		}
		
	}
	
	
}
