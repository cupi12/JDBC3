package co.yedam.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable{

	@FXML BorderPane rootLayout;
	@FXML MenuBar menubar;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@FXML
	public void empView(ActionEvent event) {
		try {
			BorderPane empView = FXMLLoader.load(getClass().getResource("emp.fxml"));
			rootLayout.setCenter(empView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void deptView(ActionEvent event) {
		try {
			BorderPane deptView = FXMLLoader.load(getClass().getResource("dept.fxml"));
			rootLayout.setCenter(deptView);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@FXML
	public void loginView(ActionEvent event) {
		try {
			AnchorPane loginView = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
			Stage primaryStage = (Stage) menubar.getScene().getWindow();

			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			dialog.setTitle("로그인");

			Scene scene = new Scene(loginView);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
