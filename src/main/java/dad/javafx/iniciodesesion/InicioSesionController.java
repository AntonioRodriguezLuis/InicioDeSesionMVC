package dad.javafx.iniciodesesion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InicioSesionController {
	private InicioSesionModel model = new InicioSesionModel();
	private InicioSesionView view = new InicioSesionView();
	// Esto esta aqui para cerrar la ventana ya que necesito acceder a primaryStage
	private Stage primaryStage;

	private ArrayList<String> usuarioCorrecto = new ArrayList<>();
	private ArrayList<String> passwordCorrecto = new ArrayList<>();

	public InicioSesionController(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		//bindings
		model.usuarioProperty().bind(view.getUsuarioText().textProperty());
		model.passwordProperty().bind(view.getPasswordText().textProperty());
		// Funciones lamda utilizamos esto para enlazar la accion del bonton con el
		// boton de la vista.
		view.getAccederButton().setOnAction(e -> onAccederButtonAction(e));
		view.getCancelarButton().setOnAction(e -> onCancelarButtonAction(e));
	}

	public void onCancelarButtonAction(ActionEvent e) {
		primaryStage.close();
	}

	public void onAccederButtonAction(ActionEvent e) {
		readCSV();
		String passwordMd5 = DigestUtils.md5Hex(model.getPassword()).toUpperCase();
		if (usuarioCorrecto.contains(model.getUsuario()) && passwordCorrecto.contains(passwordMd5)) {
			Alert infoAlert = new Alert(AlertType.INFORMATION);
			infoAlert.setTitle("Iniciar sesión");
			infoAlert.setHeaderText("Acceso permitido");
			infoAlert.setContentText("Las credenciales de acceso son válidas.");

			infoAlert.showAndWait();
		} else {
			Alert errAlert = new Alert(AlertType.ERROR);
			errAlert.setTitle("Iniciar sesión");
			errAlert.setHeaderText("Acceso denegado");
			errAlert.setContentText("El usuario y/o contraseña no son válidos.");

			errAlert.showAndWait();
		}
	}

	private void readCSV() {
		URL url = getClass().getResource("/dad/javafx/iniciodesesion/resources/users.csv");
		String separador = ",";

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(url.getFile()));

			String line;
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(separador, -1);
				usuarioCorrecto.add(datos[0]);
				passwordCorrecto.add(datos[1]);
			}

			br.close();
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
		}

	}

	public InicioSesionView getView() {
		return view;
	}
}
