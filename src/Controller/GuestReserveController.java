
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DB.GuestDB;
import DB.StoreDB;
import DB.loginDB;
import List.StoreList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 박성호, 허세진
 */
public class GuestReserveController implements Initializable {

	@FXML
	private Button btn_guestreserve_check;
	@FXML
	private Button btn_guest_menuorder;
	@FXML
	private Button btn_date_check;
	@FXML
	private ListView<String> list_Storelist;
	@FXML
	private ListView<String> list_showreview;
	@FXML
	private ListView<String> list_storeinfo;
	@FXML
	private ListView<String> list_ordercheck;
	@FXML
	private ListView<String> list_menuname;
	@FXML
	private ListView<String> list_price;
	@FXML
	private DatePicker datepicker;
	@FXML
	private ChoiceBox choice_time;
	@FXML
	private TextField field_check_store;
	@FXML
	private TextField field_check_date;
	@FXML
	private TextField field_storeinfo;
	@FXML
	private TextField field_storeinfo_address;
	@FXML
	private TextField field_storeinfo_tel;
	@FXML
	private TextField field_check_time;
	@FXML
	private Label label_totalprice;
	@FXML
	private Label label_timemax;
	@FXML
	private Label label_current;
	@FXML
	private ListView<String> list_showscore;

	private ArrayList<String> listStoreInfo = new ArrayList<>();
	private ArrayList<String> listStoreReview = new ArrayList<>();
	private ArrayList<String> listStoreScore = new ArrayList<>();
	private ArrayList<Integer> listStoreOperateTime = new ArrayList<>();

	private List<StoreList> Store_List = new ArrayList<>();

	int s_key = 0;
	int g_key = 0;
	int re_time = 0;
	int price = 0;
	LocalDate datepickervalue;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		loginDB ld = new loginDB();
		StoreDB sd = new StoreDB();
		GuestDB gd = new GuestDB();
		
		
		
		ObservableList<String> OderCheckList = FXCollections.observableArrayList(); // 배열화
		LocalDate now = LocalDate.now(); // 현재시간
		now = now.plusDays(1); // 현재시간 + 1

		// 가게 이름 출력
		sub.SList_Print(list_Storelist);
		// 초기 날짜 설정
		datepicker.setValue(now);
		datepickervalue = datepicker.getValue();
		// listview 더블클릭
		sub.listview_Dclick(list_Storelist, field_check_store);
		sub.listview_Dclick(list_ordercheck);

		btn_guestreserve_check.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String field_store = "";
				field_store = field_check_store.getText();
				
				s_key = gd.storekey_val(field_store); // 스토어 관리자 스토어 키값을 초기화
				g_key = ld.key_val(IntroViewController.getField, "guest", "key");
				// 가게 이름, 주소, 전화번호 출력
				Store_List = sd.getStringStoreInformaiton(s_key);
				field_storeinfo.setText(Store_List.get(0).getS_name());
				field_storeinfo_address.setText(Store_List.get(0).getS_addr());
				field_storeinfo_tel.setText(Store_List.get(0).getS_tel());
				list_ordercheck.setItems(OderCheckList);
				

				// 메뉴, 가격 출력
				sub.Menu_Print(list_menuname, list_price, s_key);

				// choiceBox 초기화 open시간 ~ close 시간까지
				listStoreOperateTime = gd.getStoreOperatertime(s_key);
				choice_time.getItems().removeAll(choice_time.getItems());
				for (int i = listStoreOperateTime.get(0); i < listStoreOperateTime.get(1); i++) {
					choice_time.getItems().addAll(i);
				}
				choice_time.getSelectionModel().select(0); // 처음 실행됐을 때 클릭되는 부분

				re_time = Integer.parseInt(choice_time.getValue().toString());

				// 가게 최대 테이블 띄우기
				sub.timemax_set(s_key, label_timemax);
				// 현재 예약 인원 보기
				sub.cuurent_set(s_key, datepickervalue, label_current, re_time);
			}
		});

		list_menuname.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				String menu = list_menuname.getSelectionModel().getSelectedItem();;
				if (click.getClickCount() == 2) {
					Object obj = list_menuname.getSelectionModel().getSelectedItem();
					OderCheckList.add(obj.toString());
					list_ordercheck.setItems(OderCheckList);
					price += gd.getprice(s_key, menu);
					System.out.println(price);
					label_totalprice.setText("총 : " + Integer.toString(price) + "원");
				}
			}
		});

		btn_guest_menuorder.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				List<String> ListOrdercheck = list_ordercheck.getItems();
				gd.store_reservation(s_key, g_key, datepickervalue, price, re_time);
				for(int i=0; i<ListOrdercheck.size(); i++) {
					gd.Order_M_insert(ListOrdercheck.get(i), g_key, s_key);
					System.out.println("gKey"+ ListOrdercheck.get(i));
					System.out.println("gKey"+ g_key + "s_key" + s_key);
				}
				sub.setWindow("예약이 완료되었습니다");
			}
		});

		btn_date_check.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				re_time = Integer.parseInt(choice_time.getValue().toString());
				datepickervalue = datepicker.getValue();
				sub.timemax_set(s_key, label_timemax);
				sub.cuurent_set(s_key, datepickervalue, label_current, re_time);
			}
		});

	
	}
}