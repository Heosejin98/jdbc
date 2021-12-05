
package Controller;

import javafx.collections.FXCollections;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DB.GuestDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author 박성호, 허세진
 */
public class GuestReviewController implements Initializable{

    @FXML
    private RadioButton review_score5;
    @FXML
    private RadioButton review_score4;
    @FXML
    private RadioButton review_score3;    
    @FXML
    private RadioButton review_score2;
    @FXML
    private RadioButton review_score1;
    @FXML
    private Button btn_ReviewScore;
    @FXML
    private Button btn_Reviewwrite;
    @FXML
    private TextArea textarea1_review;
    @FXML
    private ToggleGroup StoreScore;
    @FXML
    private ListView<String>  list_showreview;
    @FXML
    private ListView<String> list_reviewstorename; 
    @FXML
    private Label label_reviewstorename;
    
    private ObservableList<String> cStoreList;
    int score = 0;
    String Review;
    private ArrayList<String> listStoreReview = new ArrayList<>();
    private ArrayList<String> listStoreName = new ArrayList<>();
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    	GuestDB gd = new GuestDB();
    	
    	String id = IntroViewController.getField;
    	ObservableList<String> R_List= FXCollections.observableArrayList(); // 배열화
    	int g_key = gd.getguestkey(id);
    	int s_key = gd.storekey_val(g_key);
    	System.out.println(g_key);
    	
    	List<String> Review_List = new ArrayList<>();
    	Review_List = gd.getReviewStoreNameList(g_key);
		for (int i = 0; i < Review_List.size(); i++) {
			R_List.add(Review_List.get(i));
		}
		list_reviewstorename.setItems(R_List);
        
        
    StoreScore.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
    {
        @Override
        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1)
            {
            RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
            score = Integer.parseInt(chk.getText());
            System.out.println(score);
            }
        });
    
    btn_Reviewwrite.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            	 Review= textarea1_review.getText();	
            	 gd.signUpreview(s_key, id, Review, score, g_key);
            	 	
            	 	//내용 갱신
            		ObservableList<String>  R_List= FXCollections.observableArrayList(); // 배열화
            		List<String> Review_List = new ArrayList<>();
                	Review_List = gd.getReviewStoreNameList(g_key);
            		for (int i = 0; i < Review_List.size(); i++) {
            			R_List.add(Review_List.get(i));
            		}
            		
            		list_reviewstorename.setItems(R_List);
            		StoreScore.selectToggle(null);
            		textarea1_review.clear();
            		label_reviewstorename.setText("");	
   
            }
        });
    

     list_reviewstorename.setOnMouseClicked(new EventHandler<MouseEvent>() {  //리스트뷰 클릭 시 메뉴이름 텍스트박스에 출력
                @Override
                public void handle(MouseEvent event) {
                Object obj = list_reviewstorename.getSelectionModel().getSelectedItem();
                label_reviewstorename.setText(obj.toString());
                
               
                }
        });
     }
  }