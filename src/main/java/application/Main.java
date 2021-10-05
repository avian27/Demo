package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//logging.info("Logs Generated");
			Parent root = FXMLLoader.load(getClass().getResource("/applicationLayout/Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			//logging.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
