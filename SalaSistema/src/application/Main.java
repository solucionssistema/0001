package application;

//Se importó el controlador postgresql-42.2.5.jar
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {//Recibe un objeto de tipo stage la ventana.
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));//Se carga la interfaz gráfica del usuario
			Scene scene = new Scene(root);//Objeto de tipo scene, una capa intermedia en un formulario y se le envia el panel principal el root.
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());//Se carga el estilo css.
			primaryStage.setScene(scene);//El formulario tendria el panel principal y todos sus componentes
			primaryStage.show();//se muestra el formulario
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);//internamente llama al método start.
	}
	
}
