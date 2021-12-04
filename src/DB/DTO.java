/**
 * 이 클래스는 DB연결 SQL문 수행을 위한 클래스다. 
 * 반환하는 값에 따라 원하는 함수를 사용한다.
 * loadDB() DB연결
 * changeDB(String query) insert, update.. 등 반환 값이 없는 sql문 수행
 * getDBString(String query, String getValue) select 로 검색하여 string을 반환
 * getDBInt(String query, String getValue) select로 검색하여 int 반환
 * getDBListint(String query, String getValue, String getValue2) select로 검색하여 ArrayList반환 int 타입만 
 * closeDB() DB연결종료
 * 
 * 매개변수 
 * query = sql문,  getValue = 값을 넣을 곳, getValue1 = 값 넣을 곳 처음 ,getValue2 = 값을 넣을 곳 마지막 
 * 
 * @version     1.00 21/11/25
 * @author      허세진
 * @see         com.javacan.event.SomeEvent
 * @since       Jre1.8.0_202
 */

package DB;

import java.sql.CallableStatement;
//자바 클래스
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DTO {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement st = null;
	CallableStatement cstmt = null;

	String sql = "";
	String Uri = "jdbc:oracle:thin:@58.239.219.202:1521:XE";
	String Id = "system";
	String Pw = "oracle";
	
	
	public void pstmt() {
		
		
	}
	// DB 연결 함수
	public Connection loadDB() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(Uri, Id, Pw);
			System.out.println("연결");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 로드 실패");
		}
		return null;
	}

	
	// DB에 insert, delete, update 등 수정 @송진영
	public void changeDB(String query) {

		String sql = query;
		try {
			st = conn.prepareStatement(sql);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("SQL문 수행 실패" + e);
		}

	}
	
	public int order_max(int s_key, int time, Date date) {
		
		int ord_num = 0;

		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
		    sql = "select max(ord_num) from order_t where store_key = ? and ord_date = ? and where order_time  = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, s_key);
			st.setDate(2, date);
			st.setInt(3, time);
			rs = st.executeQuery();
			rs.next();
			ord_num = rs.getInt(1);
			closeDB();

		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
 
        
        
		return ord_num;
	}	
    /**
    * @메서드이름 : Change
    * @작성날짜 : 21.06.03
    * @용도 : DB 수정
    * @author 허세진
    */
    public void Change(String table, String colum, String change_val, String row,String key){
            try{
            	conn = DriverManager.getConnection(Uri, Id, Pw);
                sql = "UPDATE "+table+" SET "+colum +" = ? WHERE "+row+" = ? ";
                
                st = conn.prepareStatement(sql);
                st.setString(1, change_val);
                st.setString(2, key);
                st.executeUpdate();
                
                closeDB();
            } catch (SQLException e) {
                System.out.println("DB 로드 실패" + e);
            }
    }  

	// DB에서 String 값을 반환하는 함수
	public String getDBString(String query, String getValue) {
		String value = "";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				value = rs.getString(getValue);
			}
			return value;
		} catch (SQLException e) {
			System.out.println("SQL문 수행 실패" + e);
			value = "수행 실패";
			return value;
		}

	}

	// DB에서 Int 값을 반환하는 함수 query = sql / getValue 
	public int getDBInt(String query, String getValue) {

		int value = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				value = rs.getInt(getValue);
			}
			return value;
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("SQL문 수행 실패" + e);
			return value;
		}

	}

	// DB에서 값을 arryalist로 반환하는 함수 @송진영
	public ArrayList<Integer> getDBList(String query, String getValue, String getValue2) {

		int value = 0;
		int value_2 = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				value = rs.getInt(getValue);
				value_2 = rs.getInt(getValue2);
				list.add(value * value_2);
			}
			return list;
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("SQL문 수행 실패" + e);
			return list;
		}

	}
	
	// DB 연결 종료
	public void closeDB() { 
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if(cstmt != null)
				cstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

}
