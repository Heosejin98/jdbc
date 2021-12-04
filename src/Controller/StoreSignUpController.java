package Controller;

import DB.SignUpDB;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StoreSignUpController implements Initializable {

	@FXML
	private Button btn_store_idcheck;
	@FXML
	private Button btn_store_signup;
	@FXML
	private Button btn_goback;

	@FXML
	private TextField field_store_signupid;
	@FXML
	private PasswordField field_store_signuppw;
	@FXML
	private PasswordField field_store_signuppwcheck;
	@FXML
	private TextField field_store_signupname;
	@FXML
	private TextField field_store_signupaddress;
	@FXML
	private TextField field_store_signuptel;
	@FXML
	private TextField field_store_signupmoney;

	int check_id = 0; // id 체크를 위해 사용, 1인경우는 중복된 아이디가 있다.
	SignUpDB signupdb = new SignUpDB();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO 자동 생성된 메소드 스텁
		
	sub.goback(btn_goback);
		
	btn_store_idcheck.setOnMouseClicked(new EventHandler<MouseEvent>() { // id 중복확인 체크
	

			@Override
			public void handle(MouseEvent event) {
				if (signupdb.checkId(field_store_signupid.getText())) { // id가 중복이면
					check_id = 2;
					System.out.println("중복된 ID 입니다.");
					sub.setWindow("이미 존재하는 ID 입니다.");
				} else {
					check_id = 1;
					System.out.println("사용가능한 ID 입니다.");
					sub.setWindow("사용 가능한 ID 입니다.");
				}
			}
		});
	
	btn_store_signup.setOnMouseClicked(new EventHandler<MouseEvent>() { // 회원가입
		@Override
		public void handle(MouseEvent event) {
			String id = field_store_signupid.getText();
			String pw = field_store_signuppw.getText();
			String pw_ck = field_store_signuppwcheck.getText();
			String name = field_store_signupname.getText();
			String address = field_store_signupaddress.getText();
			String tel = field_store_signuptel.getText();
			String coin = field_store_signupmoney.getText();
			int i_coin = Integer.parseInt(coin);

			if (check_id == 1 & pw.contains(pw_ck) & !(pw_ck.equals("")) & !(name.equals(""))
					& !(address.equals("")) & !(tel.equals("")) & !(coin.equals(""))) {

				signupdb.signUpStoreManger(id, pw_ck, name, tel, address, i_coin);
				System.out.println("회원가입이 완료되었습니다.");
				sub.setWindow("회원가입이 완료되었습니다.");
				Stage stage = (Stage) btn_goback.getScene().getWindow();
				Parent main = null;
				
				try {
					main = FXMLLoader.load(getClass().getResource("/fxml/IntroView.fxml"));
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				
				Scene scene = new Scene(main);
				stage.setScene(scene);
				stage.show();
			} else if (check_id == 0) {
				sub.setWindow("ID 중복 체크를 해주세요");
			} else {
				sub.setWindow("빈 공간 없이 입력해주세요");
			}
		}
	});
	
	}

}
