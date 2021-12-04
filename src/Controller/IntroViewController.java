package Controller;


import DB.DTO;
import DB.loginDB;
//자바 클래스
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//자바fx 클래스
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class IntroViewController implements Initializable {
	
	public static String getField;
	
	@FXML
	private Button btn_login;
    @FXML
	private Button btn_signup_guest;
	@FXML
	private Button btn_signup_manager;
    @FXML
	private AnchorPane login_stage;
	@FXML
	private TextField field_id;
	@FXML
	private PasswordField field_pw;
	    
	static DTO dto = new DTO();
	loginDB ldb = new loginDB();
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO 자동 생성된 메소드 스텁
		
	    btn_signup_guest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Stage stage = (Stage) login_stage.getScene().getWindow();
                Parent main = null;
               
                try {
                    main = FXMLLoader.load(getClass().getResource("/fxml/GuestSignUp.fxml"));
                } catch (IOException ex) {
                	 System.out.print("오류 :" + ex);
                }
                Scene scene = new Scene(main);
                stage.setScene(scene);
                stage.show();

            }
        }
        );
        
        btn_signup_manager.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
            	  Stage stage = (Stage) login_stage.getScene().getWindow();
                  Parent main = null;
                  
                  try {
                      main = FXMLLoader.load(getClass().getResource("/fxml/StoreSignUp.fxml"));
                  } catch (IOException ex) {
                  	 System.out.print("오류 :" + ex);
                  }
                  Scene scene = new Scene(main);
                  stage.setScene(scene);
                  stage.show();
            }
        }
        );
        
        btn_login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	 Stage stage = (Stage) login_stage.getScene().getWindow();
                 Parent main = null;
                 String id = field_id.getText();
                 String pw = field_pw.getText();
                 getField = field_id.getText();
           
            	
            	if(ldb.key_val(id) == 0 )
                 sub.noWindow();
            	else if(ldb.key_val(id) >= 2000 && 
            			ldb.IdPW_Check(id, pw)) {
            		  try {
                          main = FXMLLoader.load(getClass().getResource("/fxml/StoreManagerMainView.fxml"));
                      } catch (IOException ex) {
                      	 System.out.print("오류 :" + ex);
                      }
                      Scene scene = new Scene(main);
                      stage.setScene(scene);
                      stage.show();
            	}
            	else if(ldb.key_val(id) <  2000 && 
            			ldb.key_val(id) > 999 && 
            			ldb.IdPW_Check(id, pw)){
            		  try {
                          main = FXMLLoader.load(getClass().getResource("/fxml/GuestMainView.fxml"));
                      } catch (IOException ex) {
                      	 System.out.print("오류 :" + ex);
                      }
                      Scene scene = new Scene(main);
                      stage.setScene(scene);
                      stage.show();
            	}
            	else if(!ldb.IdPW_Check(id, pw)) {
            		sub.noWindow();
            	}
            		
            		
            }
        });
    }
        
   
}


