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
 * @author Çã¼¼Áø
 */
public class GuestReserveInfoController implements Initializable{
    
    @FXML
    private Button btn_cancel;
    @FXML
    private Label label_waitperson;
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
        
    	GuestDB ld = new GuestDB();
    	CancelTx TX = new CancelTx();
    	String id = IntroViewController.getField;
    	int g_key = ld.getguestkey(id);
    	btn_cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				TX.getCost(g_key);
				ld.cancel_res(g_key);
			}
		});
        
       
    }
}

