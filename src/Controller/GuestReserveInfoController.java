/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DB.CancelTx;
import DB.GuestDB;
import DB.StoreDB;
import List.OrderInfoList;
import List.StoreList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;


/**
 *
 * @author 허세진
 */
public class GuestReserveInfoController extends GuestMainViewController implements Initializable{
    
    @FXML
    private Button btn_cancel;
  
    @FXML
    private Label label_waittime;
    @FXML
    private Label label_store;
    @FXML
    private Label label_date;
    @FXML
    private Label label_time;
    @FXML
    private TextField field_reserveinfo;
    @FXML
    private ListView list_reservationmenu;
         
    private ObservableList<String> cStoreList;
    private ArrayList<String> ReservationMenu = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	GuestDB gd = new GuestDB();
    	StoreDB sd = new StoreDB();
    	CancelTx TX = new CancelTx();
    	
    	ObservableList<String> MenuList = FXCollections.observableArrayList();
    	
    	String id = IntroViewController.getField;
    	String time = "";
    	String date = "";
    	String storename = "";
    	int g_key = gd.getguestkey(id);
    	int s_key = gd.storekey_val(g_key);
    	int ordnum = sd.get_ord_num(s_key, g_key);
    	List<OrderInfoList> Store_List = new ArrayList<>();
    	
    
    	System.out.println(g_key);
    	System.out.println(s_key);
    	System.out.println(ordnum);
    	//가게 정보, 예약 정보 출력
    	
    	Store_List = gd.getOrderInfo(s_key, g_key);
    	System.out.println("안나옴?" + Store_List.get(0).getStorename());
    	label_store.setText(Store_List.get(0).getStorename());
    	label_date.setText((Store_List.get(0).getDate()).toString());
    	label_time.setText(Integer.toString(Store_List.get(0).getTime()));
    	
    	//메뉴 출력
    	List<String> M_List = new ArrayList<>();
		M_List = sd.GetOrderMenuList(g_key, s_key, ordnum);
		for (int i = 0; i < M_List.size(); i++) {
			MenuList.add(M_List.get(i));
		}
		list_reservationmenu.setItems(MenuList);
		
		
		//에약 취소
    	btn_cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				TX.getCost(g_key);
				gd.cancel_res(g_key);
	
					
				
			}
		});
        
       
    }
}

