/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author 허세진
 */
public class StoreManagerMainViewController implements Initializable {

	@FXML
	private Button btn_storemanage;
	@FXML
	private Button btn_servemanage;
	@FXML
	private Button btn_logout;
	@FXML
	private AnchorPane main_stage;
	@FXML
	private AnchorPane sub_stage;
	@FXML
	private AnchorPane third_stage;
	@FXML
	private Label label_name;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		sub.username_set(IntroViewController.getField, label_name);
		sub.goback(btn_logout);
		
		btn_storemanage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				sub_stage.getChildren().remove(third_stage);

				try {
					third_stage = FXMLLoader.load(getClass().getResource("/fxml/StoreManagerMenuManageView.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				sub_stage.getChildren().add(third_stage);
			}
		});

		btn_servemanage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				sub_stage.getChildren().remove(third_stage);

				try {
					third_stage = FXMLLoader.load(getClass().getResource("/fxml/StoreOrderCheckView.fxml"));
				} catch (IOException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}

				sub_stage.getChildren().add(third_stage);
			}
		});

		

	}
}