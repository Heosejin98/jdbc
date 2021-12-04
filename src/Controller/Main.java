/*
jdk = javaSE jre1.8.0_202
ide =이클립스 2021.09
fxml = fxml-17.0.1 
ScenceBuilder - 17.0.0.msi

*/



package Controller;


//자바fx 클래스
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/IntroView.fxml"));

        Scene scene = new Scene(root);
 
        stage.setTitle("DB응용 팀프로젝트");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
	

	public static void main(String[] args) {
		
		launch(args);
		
	}
}
