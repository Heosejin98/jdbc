/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.IntroViewController.getField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DB.StoreDB;
import DB.loginDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 허세진
 */
public class StoreOrderCheckViewController implements Initializable {

	@FXML
	private Button btn_table_check;
	@FXML
	private Button btn_pay_cash;
	@FXML
	private ListView<String> list_now_table;
	@FXML
	private ListView list_menuname;
	@FXML
	private ListView list_price;
	@FXML
	private TextField field_total_price;
	@FXML
	private TextField field_table_check;

	private ObservableList<String> cReserveMenuAmountList;
	private ObservableList<String> cReserveMenuList;
	private ArrayList<String> ReservationMenu = new ArrayList<>();
	private ArrayList<String> ReserveMenuAmount = new ArrayList<>();

	String allprice_query, allprice_value, allprice_value2, id;
	String store_name = "";
	String guest_name = "";
	int g_key;
	ArrayList<Integer> allprice = new ArrayList<>();

	String store_id = IntroViewController.getField;
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		StoreDB sd = new StoreDB();
		loginDB ld = new loginDB();

		String id = IntroViewController.getField; // 로그인한 사람의 id를 얻어옴
		int s_key = ld.storekey_val(id); // 스토어 관리자 스토어 키값을 초기화
		
		List<String> List = new ArrayList<>();
		ObservableList<String> cGuestList = FXCollections.observableArrayList();
		ObservableList<String> MenuList = FXCollections.observableArrayList();

		// 주문자 이름 출력
		List = sd.getGuestList(s_key);
		for (int i = 0; i < List.size(); i++) {
			cGuestList.add(List.get(i));
		}
		list_now_table.setItems(cGuestList);

		list_now_table.setOnMouseClicked(new EventHandler<MouseEvent>() { // 현재 테이블 클릭시 테이블 필드에 테이블 값이 set 된다.
			@Override
			public void handle(MouseEvent event) {
				if (list_now_table.getSelectionModel().getSelectedItem() == null) {
					sub.setWindow("빈 테이블을 선택하셨습니다.");

				} else {
					Object obj = list_now_table.getSelectionModel().getSelectedItem();
					field_table_check.setText(obj.toString());
				}

			}
		});

		btn_table_check.setOnMouseClicked(new EventHandler<MouseEvent>() { // 테이블 조회 버튼 클릭시
			@Override
			public void handle(MouseEvent event) {
				
				// 초기화
				list_menuname.getItems().clear();
				list_price.getItems().clear();
				guest_name = field_table_check.getText();
				System.out.println(guest_name);
				g_key = sd.getguestkey(s_key, guest_name);
				// 오더 번호 검출
				int ordnum = 0;
				ordnum = sd.get_ord_num(s_key, g_key);
				System.out.println(ordnum);
				// 메뉴출력
				List<String> M_List = new ArrayList<>();
				M_List = sd.GetOrderMenuList(g_key, s_key, ordnum);
				for (int i = 0; i < M_List.size(); i++) {
					MenuList.add(M_List.get(i));
				}
				list_menuname.setItems(MenuList);

				// 총 결제 금액 출력
				String total = Integer.toString(sd.gettotalprice(s_key, g_key, ordnum));
				field_total_price.setText(total);

			}
		});

		btn_pay_cash.setOnMouseClicked(new EventHandler<MouseEvent>() { // 현금 결제 버튼 클릭시
			@Override
			public void handle(MouseEvent event) {
				
				
				// 오더 번호 검출
				int ordnum = 0;
				ordnum = sd.get_ord_num(s_key, g_key);
				
				//최종 결제 update
				sd.final_payment(s_key, g_key, ordnum);
				
				
				refresh();
				
				//예약 손님 화면 다시 띄우기
				ObservableList<String> payGuestList = FXCollections.observableArrayList();
				List<String> List = new ArrayList<>();
				List = sd.getGuestList(s_key);
				for (int i = 0; i < List.size(); i++) {
					payGuestList.add(List.get(i));
				}
				list_now_table.setItems(payGuestList);
				
				
			}

		});
	}

	public void refresh() {

		list_now_table.getItems().remove(field_table_check.getText());
		// list_now_table.setItems(cGuestList);
		list_menuname.getItems().clear();
		list_price.getItems().clear();
	}
}