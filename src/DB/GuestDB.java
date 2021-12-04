package DB;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Controller.sub;

public class GuestDB extends DTO {

	/**
	 * @메서드이름 : getStoreNameList
	 * @작성날짜 : 21.11.30
	 * @용도 : 매장 이름 출력
	 * @author 허세진
	 */
	public ArrayList<String> getStoreNameList() {

		ArrayList<String> list = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			sql = "select storename from store";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			String value = "";
			while (rs.next()) {
				value = rs.getString(1);
				list.add(value);
			}
			closeDB();

		} catch (SQLException e) {

			System.out.println("DB 로드 실패" + e);
		}
		return list;

	}



	/**
	 * @메서드이름 : getStoreCloseTime
	 * @작성날짜 : 21.12.01
	 * @용도 : GUI에 영업시간을 입력받기 위해 사용
	 * @author 허세진
	 */
	public ArrayList<Integer> getStoreOperatertime(int s_key) {

		int Time = 0;
		ArrayList<Integer> list = new ArrayList<>();
		try {

			conn = DriverManager.getConnection(Uri, Id, Pw);
			sql = "select storeopen, storeclose from store where storekey = ? ";
			st = conn.prepareStatement(sql);
			st.setInt(1, s_key);
			rs = st.executeQuery();

			if (rs.next()) {
				Time = rs.getInt(1);
				list.add(Time);
				Time = rs.getInt(2);
				list.add(Time);
			}

			closeDB();
		} catch (SQLException e) {

			System.out.println("DB 로드 실패" + e);
		}

		return list;
	}

	public int getStoreMax(int s_key) {
		int value = 0;

		try {
			sql = "select TimeMax from store where STOREKEY = ?";
			conn = DriverManager.getConnection(Uri, Id, Pw);
			st = conn.prepareStatement(sql);

			st.setInt(1, s_key);
			rs = st.executeQuery();
			rs.next();
			value = rs.getInt(1);
			closeDB();

		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("DB 로드 실패" + e);

		}
		return value;
	}
	
	
	public int getStoreRerv(int s_key, Date date, int time) {
		int value = 0;

		try {
			sql = "select max(ord_num) from order_t where  sto_key = ? and ord_date = ? and ord_time = ?";
			conn = DriverManager.getConnection(Uri, Id, Pw);
			st = conn.prepareStatement(sql);
			st.setInt(1, s_key);
			st.setDate(2, date);
			st.setInt(3, time);
			rs = st.executeQuery();
			rs.next();
			value = rs.getInt(1);
			closeDB();

		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("DB 로드 실패" + e);

		}
		return value;
	}
	
	
	
	public int storekey_val(String s_name) {
		int value = 0;

		try {
			sql = "select storekey from store where storename ='" + s_name + "'";
			conn = DriverManager.getConnection(Uri, Id, Pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			value = rs.getInt(1);
			closeDB();
			
			return value;
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("DB 로드 실패" + e);
			return value;
		}

	}
	
	// order_t check_key = 1 결제전 / 2 결제후
	public void store_reservation(int s_key, int g_key,
			LocalDate resv_date,  int cost, int time) { // in
								
		Date date = new Date(0);
		
		String Str_date = resv_date.toString(); //로컬 데이터 string으로
		java.sql.Date date1 = java.sql.Date.valueOf(Str_date);// out
		
		int max = getStoreRerv(s_key, date1, time)+1; 
		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			cstmt = conn.prepareCall("{call 예약(?, ?, ?, ?, ?, ?, ?)}");
			cstmt.setInt(1, s_key);
			cstmt.setInt(2, max);
			cstmt.setInt(3, g_key);
			cstmt.setDate(4, date1);
			cstmt.setInt(5, cost);
			cstmt.setInt(6, time);
			cstmt.setInt(7, 1);
			cstmt.executeQuery();
			
			closeDB();
		} catch (SQLException e) {
			System.out.println("예약 테이블 생성 실패" + e);
		}
	}
	
	public void Order_M_insert(String name, int guest_key, int store_key) {
		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			sql = "insert into order_menu(menuname, guest_key, store_key)";
			sql += "values(?, ?, ?)";

			st = conn.prepareStatement(sql);
			st.setString(1, name);	
			st.setInt(2, guest_key);
			st.setInt(3, store_key);
			rs = st.executeQuery();
			closeDB();

		} catch (SQLException e) {
			System.out.println("메뉴 저장 실패" + e);
		}
	}
	
	public void cancel_res(int guest_key) {
		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			sql = "DELETE FROM order_t where guest_key = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, guest_key);
			rs = st.executeQuery();
			
			sql = "DELETE FROM order_menu where guest_key = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, guest_key);
			rs = st.executeQuery();
			sub.setWindow("예약이 취소되었습니다");
			closeDB();

		} catch (SQLException e) {
			System.out.println("주문 취소 실패" + e);
		}
	}
	

	
	public int getguestkey(String id) {

	    int value = 0;
	       
        try {
        	conn = DriverManager.getConnection(Uri, Id, Pw);
        	sql = "SELECT key FROM guest where id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
     
            
            while (rs.next()) {
                    value = rs.getInt(1);

            }
    
            closeDB();
        } catch (SQLException e) {
			System.out.println("GuestDB.java 게스트키 가져오기 실패" + e);
		}
        return value;
    }
	
	public int getprice(int s_key, String m_name) { // in
																													// out
		int price = 0;																											// parameter
		try {
			sql = "select price from menu where storekey = ? and menu_name = ?";
			conn = DriverManager.getConnection(Uri, Id, Pw);
			st = conn.prepareStatement(sql);

			st.setInt(1, s_key);
			st.setString(2, m_name);
			rs = st.executeQuery();
			rs.next();
			price = rs.getInt(1);
			closeDB();
		} catch (SQLException e) {
			System.out.println("가격 정보 가져오기 실패" + e);
		}
		return price;
	}

}
