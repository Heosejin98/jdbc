package Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DB.GuestDB;
import DB.StoreDB;
import DB.loginDB;
import List.MenuList;
import List.StoreList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sub {

	public static void setWindow(String text) { // String 매개 변수를 받아 메시지창을 띄우는 함수
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("음식 예약 프로그램 : Waiter");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}

	public static void okWindow() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("음식 예약 프로그램 : Waiter");
		alert.setHeaderText(null);
		alert.setContentText("정상적으로 로그인되었습니다.");
		alert.showAndWait();
	}

	public static void noWindow() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("음식 예약 프로그램 : Waiter");
		alert.setHeaderText(null);
		alert.setContentText("ID 또는 PW를 확인하세요.");
		alert.showAndWait();
	}

	// 뒤로가기
	public static void goback(Button btn_goback) {
		btn_goback.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Stage stage = (Stage) btn_goback.getScene().getWindow();
				Parent main = null;
				try {
					main = FXMLLoader.load(getClass().getResource("/fxml/IntroView.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				Scene scene = new Scene(main);
				stage.setScene(scene);
				stage.show();
			}
		});
	}

	// 유저 이름 띄우기
	public static Label username_set(String username, Label lable) {
		loginDB lgd = new loginDB();
		String M_Name = username;
		
		lable.setText(lgd.getname(M_Name));
		return lable;

	}
	
	// 코인 띄우기
	public static Label coin_label_set(String id, Label lable) {
		GuestDB gd = new GuestDB();
		String ID = id;
		
		lable.setText("COIN : " + gd.get_coin(ID));
		return lable;

	}
	
	// 포인트 띄우기
	public static Label point_set(String id, Label lable) {
		GuestDB gd = new GuestDB();
		String ID = id;
		
		lable.setText("POINT : " + gd.get_point(ID));
		return lable;

	}
	
	// 포인트 띄우기
		public static Label membership_set(String id, Label lable) {
			GuestDB gd = new GuestDB();
			String ID = id;
			
			lable.setText("MEMBERSHIP : " + gd.get_mebership(ID));
			return lable;

		}
		
	
	
	//최대 수용 인원 띄우기
	public static Label timemax_set(int s_key, Label lable) {
		GuestDB gd = new GuestDB();
		String a = Integer.toString(gd.getStoreMax(s_key));
		
		lable.setText(a);
		return lable;
	}
	
	public static Label cuurent_set(int s_key, LocalDate LocalDate1, Label lable, int time) {
		GuestDB gd = new GuestDB();
		Date date = new Date(0);
		
		String Str_date = LocalDate1.toString(); //로컬 데이터 string으로
		java.sql.Date date1 = java.sql.Date.valueOf(Str_date);
		
		String current_num = Integer.toString(gd.getStoreRerv(s_key, date1, time));
		System.out.println("key" + s_key+ "   " + Str_date+ "   " + time + "   " +current_num);
		lable.setText(current_num);
		
		return lable;
	
	}
	
	//버튼 visible
	public static Button btn_visal(Button btn, boolean set) {
		
		btn.setVisible(set);
		return btn;

	}

	//메뉴 출력 
	public static void Menu_Print(ListView<String> list_menuname, ListView<String> list_price, 
			 int s_key) {
		
		StoreDB sd = new StoreDB();
		
		List<MenuList> listStoreMenu = new ArrayList<>(); // 가게 메뉴 출력 : String (메뉴이름)
		
		ObservableList<String> MenuStoreList = FXCollections.observableArrayList(); // 배열화
		ObservableList<String> PriceStoreList = FXCollections.observableArrayList(); // 배열화

		listStoreMenu = sd.getMenuList(s_key);
		
		for (int i = 0; i < listStoreMenu.size(); i++) {
			MenuStoreList.add(listStoreMenu.get(i).getM_name());
			PriceStoreList.add(Integer.toString(listStoreMenu.get(i).getPrice()));
		}
		
		list_menuname.setItems(MenuStoreList);
		list_price.setItems(PriceStoreList);
	}

	
	//List 출력
	public static void SList_Print(ListView<String> stringList) {
		
		GuestDB sd = new GuestDB();
		
		List<String> List = new ArrayList<>(); 
		ObservableList<String> StringList = FXCollections.observableArrayList(); // 배열화
		List = sd.getStoreNameList();
		
		for (int i = 0; i < List.size(); i++) {
			StringList.add(List.get(i));
		}
		
		stringList.setItems(StringList);
	}
	
	//리스트뷰 더블 클릭 이벤트
	public static void listview_Dclick(ListView<String> listview) {
		listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
				    Object obj = listview.getSelectionModel().getSelectedIndex();
				    listview.getItems().remove(obj);
				}
			}
		});
	}
	
	public static void listview_Dclick(ListView<String> listview, TextField field_check_store) {
		listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {
				    Object obj = listview.getSelectionModel().getSelectedItem();
					field_check_store.setText(obj.toString());
				}
			}
		});
	}
	
	public static ObservableList<String> listview_Dclick(ListView<String> listview, ObservableList<String> OderCheckList) {
		listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				ObservableList<String> OderCheckList = FXCollections.observableArrayList(); // 배열화;
				
				if (click.getClickCount() == 2) {
				    Object obj = listview.getSelectionModel().getSelectedItem();
                    OderCheckList.add(obj.toString());
				}
			}
		});
		return OderCheckList;
	}

}
