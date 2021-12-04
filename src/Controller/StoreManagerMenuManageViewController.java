package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DB.StoreDB;
import DB.loginDB;
import List.MenuList;
import List.StoreList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author 허세진
 */
public class StoreManagerMenuManageViewController implements Initializable {

	@FXML
	private Button btn_menu_registor;
	@FXML
	private Button btn_menu_modify;
	@FXML
	private Button btn_menu_delete;
	@FXML
	private Button btn_store_modify;
	@FXML
	private Button btn_store_register;
	@FXML
	private ListView<String> list_menuname;
	@FXML
	private ListView<String> list_price;
	@FXML
	private TextField field_storename;
	@FXML
	private TextField field_storetel;
	@FXML
	private TextField field_storeaddress;
	@FXML
	private TextField field_max;
	@FXML
	private TextField field_open_time;
	@FXML
	private TextField field_end_time;
	@FXML
	private TextField field_registor_menuname;
	@FXML
	private TextField field_registor_price;

	private List<StoreList> Store_List = new ArrayList<>(); // 가게 정보 출력 : String (가게이름, 가게, 가게타입, 가게연락처 )

	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		StoreDB sd = new StoreDB();
		loginDB ld = new loginDB();
		System.out.print("어디?");
		String id = IntroViewController.getField; // 로그인한 사람의 id를 얻어옴
		System.out.print("id" + id);
		int s_key = ld.storekey_val(id); // 스토어 관리자 스토어 키값을 초기화
		System.out.print(s_key);

		// 로그인한 ID가 store_manger에 없다면
		if (!(sd.checkStore(s_key))) {
			btn_store_modify.setVisible(false); // 가게 수정 버튼은 사라진다

			btn_store_register.setOnMouseClicked(new EventHandler<MouseEvent>() { // 마우스 등록버튼 클릭시 발생 이벤트 현재 매장 정보 등록
				@Override
				public void handle(MouseEvent event) {
					// 텍스트 필드 값으로 초기화
					String s_name = field_storename.getText();
					String s_addr = field_storeaddress.getText();
					String s_tel = field_storetel.getText();
					int s_open = Integer.parseInt(field_open_time.getText());
					int s_close = Integer.parseInt(field_end_time.getText());
					int timemax = Integer.parseInt(field_max.getText());

					if (!(sd.checkStore(s_key))) {

						btn_store_register.setVisible(false);// 가게 등록 버튼은 사라진다
						btn_store_modify.setVisible(true); // 수정 버튼 생김
						sd.store_signup_call(s_key, s_name, s_addr, s_open, s_close, s_tel, timemax);// DB에 매장등록
					}

				}

			});
		} else {// 로그인한 ID가 store에 있으면
			Store_List = sd.getStringStoreInformaiton(s_key);
			btn_store_register.setVisible(false); // 가게 등록 버튼은 사라진다

			// 텍스트 필드 값으로 초기화
			String s_name = Store_List.get(0).getS_name();
			String s_addr = Store_List.get(0).getS_addr();
			int s_open = Store_List.get(0).getS_open();
			int s_close = Store_List.get(0).getS_close();
			String s_tel = Store_List.get(0).getS_tel();
			int timemax = Store_List.get(0).getTimemax();

			// DB에 넣어둔 값을 fieldtext에 출력
			field_storename.setText(s_name);
			field_storeaddress.setText(s_addr);
			field_storetel.setText(s_tel);
			field_open_time.setText(Integer.toString(s_open));
			field_end_time.setText(Integer.toString(s_close));
			field_max.setText(Integer.toString(timemax));
			
			//메뉴 출력
			sub.Menu_Print(list_menuname, list_price, s_key);

		}

		btn_store_modify.setOnMouseClicked(new EventHandler<MouseEvent>() { // 수정 버튼 클릭시 현재 매장 정보 수정
			@Override
			public void handle(MouseEvent event) {
				// 모든 Textfield가 Null이 아니면
				if (!(field_end_time.getText().equals("") & !(field_storename.getText().equals(""))
						& !(field_storetel.getText().equals("")) & !(field_storeaddress.getText().equals(""))
						& !(field_max.getText().equals(""))) & !(field_open_time.getText().equals(""))) {

					// 텍스트 필드 값으로 초기화
					String s_name = field_storename.getText();
					String s_addr = field_storeaddress.getText();
					String s_tel = field_storetel.getText();
					int s_open = Integer.parseInt(field_open_time.getText());
					int s_close = Integer.parseInt(field_end_time.getText());
					int timemax = Integer.parseInt(field_max.getText());

					sd.ChangeStoreinfo(s_name, s_addr, s_open, s_close, s_tel, timemax, s_key);// s_key값을 가진 매장 수정

					sub.setWindow("매장정보변경");
				} else {
					sub.setWindow("모든 칸을 채워주세요");
				}
			}
		});

		btn_menu_registor.setOnMouseClicked(new EventHandler<MouseEvent>() {// 메뉴 등록 버튼 클릭시 현재 매장 메뉴 등록
			@Override
			public void handle(MouseEvent event) {
				// 모든 Textfield가 Null이 아니면
				if (!(field_registor_menuname.getText().equals("") & !(field_registor_price.getText().equals("")))) {

					// 텍스트 필드 값으로 초기화
					String m_name = field_registor_menuname.getText();
					int m_price = Integer.parseInt(field_registor_price.getText());

					sd.menu_signup_call(m_name, m_price, s_key); // DB에 메뉴 등록
					sub.setWindow(m_name + "메뉴를 추가 했습니다");
					
					//메뉴 출력
					sub.Menu_Print(list_menuname, list_price,  s_key);

				}
			}

		});

		btn_menu_modify.setOnMouseClicked(new EventHandler<MouseEvent>() {// 메뉴 수정 버튼 클릭시 현재 매장 메뉴 가격 수정
			@Override
			public void handle(MouseEvent event) {

				String A = field_registor_menuname.getText();
				if (!(field_registor_menuname.getText().equals("") & !(field_registor_price.getText().equals("")))) {
					
					// 텍스트 필드 값으로 초기화
					String m_name = field_registor_menuname.getText();
					int m_price = Integer.parseInt(field_registor_price.getText());

					sd.Modifymenu(s_key, m_name, m_price);// DB에 메뉴 수정
					sub.setWindow(m_name + " 메뉴를 수정 했습니다");
					
					//메뉴 출력
					sub.Menu_Print(list_menuname, list_price,  s_key);

				}

			}
		});

		btn_menu_delete.setOnMouseClicked(new EventHandler<MouseEvent>() {// 메뉴 삭제 버튼 클릭시 현재 매장 메뉴 삭제
			@Override
			public void handle(MouseEvent event) {

				if (!(field_registor_menuname.getText().equals(""))) {
					
					// 텍스트 필드 값으로 초기화
					String m_name = field_registor_menuname.getText();
					
					sd.Delmenu(m_name, s_key); // DB에 메뉴 삭제
					sub.setWindow(m_name + " 메뉴를 삭제했습니다");
					
					//메뉴 출력
					sub.Menu_Print(list_menuname, list_price, s_key);
				}
			}

		});

		list_menuname.setOnMouseClicked(new EventHandler<MouseEvent>() { // 리스트뷰 클릭 시 메뉴이름 텍스트박스에 출력
			@Override
			public void handle(MouseEvent event) {
				Object obj = list_menuname.getSelectionModel().getSelectedItem();
				field_registor_menuname.setText(obj.toString());

			}
		});

	}

}