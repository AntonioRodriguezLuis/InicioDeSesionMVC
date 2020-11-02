package dad.javafx.iniciodesesion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private InicioSesionController inicioSesionController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		inicioSesionController = new InicioSesionController(primaryStage);
		
		Scene scene = new Scene(inicioSesionController.getView(),320,150);
	
		primaryStage.setTitle("Inicio de sesion MVC");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
