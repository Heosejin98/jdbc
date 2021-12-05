package Controller;

//자바 클래스
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//javafx 클래스
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuestMainViewController implements Initializable {

	@FXML
	private Button btn_go_reserve;
	@FXML
	private Button btn_go_reserveinfo;
	@FXML
	private Button btn_go_review;
	@FXML
	private Button btn_logout;
	@FXML
	public Label label_guestname;
	@FXML 
	public Label label_point;
	@FXML 
	public Label label_coin;
	@FXML
	public Label label_membership;
	@FXML
	private AnchorPane guestempty_stage;
	@FXML
	private AnchorPane reserve_stage;
	@FXML
	private AnchorPane reserveinfo_stage;
	@FXML
	private AnchorPane main_stage;
	@FXML
	private AnchorPane sub_stage;
	@FXML
	private AnchorPane third_stage;
	@FXML
	private Button btn_re;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String id = IntroViewController.getField;
		
		sub.username_set(id, label_guestname);
		sub.coin_label_set(id, label_coin);
		sub.point_set(id, label_point);
		sub.membership_set(id, label_membership);
		
		btn_go_reserve.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sub_stage.getChildren().remove(third_stage);

				try {
					third_stage = FXMLLoader.load(getClass().getResource("/fxml/GuestReserve.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				sub_stage.getChildren().add(third_stage);
			}
		});
		
		btn_re.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sub.coin_label_set(id, label_coin);
				sub.point_set(id, label_point);
				sub.membership_set(id, label_membership);
				
			}
		});

		btn_go_reserveinfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sub_stage.getChildren().remove(third_stage);

				try {
					third_stage = FXMLLoader.load(getClass().getResource("/fxml/GuestReserveInfo.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				sub_stage.getChildren().add(third_stage);
			}
		});

		btn_go_review.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sub_stage.getChildren().remove(third_stage);

				try {
					third_stage = FXMLLoader.load(getClass().getResource("/fxml/GuestReview.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				sub_stage.getChildren().add(third_stage);
			}
		});

		btn_logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				Stage stage = (Stage) btn_logout.getScene().getWindow();
				Parent main = null;

				try {
					main = FXMLLoader.load(getClass().getResource("/fxml/IntroView.fxml"));
				} catch (IOException ex) {
					Logger.getLogger(IntroViewController.class.getName()).log(Level.SEVERE, null, ex);
				}
				Scene scene = new Scene(main);
				stage.setScene(scene);
				stage.show();

			}
		});

	}
}
