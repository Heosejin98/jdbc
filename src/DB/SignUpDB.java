/**
 * 
 */
package DB;

import java.sql.*;

/**
 * @author aaa
 *
 */
public class SignUpDB extends DTO {
	String sql;

	static DTO dto = new DTO();

	public boolean checkId(String id) {
		boolean check = false;

		sql = "select id from member where id=?";
		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			rs = st.executeQuery();
			check = rs.next();
			closeDB();
			
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("DB 로드 실패" + e);
		}
		return check;

	}

	public void signUpGuest(String id, String pw, String name, String tel, String address, int coin) {
		try {
			
			conn = DriverManager.getConnection(Uri, Id, Pw);
			sql = "insert into member(key, id, password, name, tel, address, coin)";
			sql += "values(1000,?,?,?,?,?,?)";

			st = conn.prepareStatement(sql);
			st.setString(1, id);	
			st.setString(2, pw);
			st.setString(3, name);
			st.setString(4, tel);
			st.setString(5, address);
			st.setInt(6, coin);
			rs =st.executeQuery();
			closeDB();
			
		} catch (SQLException e) {
			System.out.println("회원가입 실패" + e);
		}
	}
	
	public void signUpStoreManger(String id, String pw, String name, String tel, String address, int coin) {
		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			sql = "insert into member(key, id, password, name, tel, address, coin)";
			sql += "values(2000,?,?,?,?,?,?)";

			st = conn.prepareStatement(sql);
			st.setString(1, id);	
			st.setString(2, pw);
			st.setString(3, name);
			st.setString(4, tel);
			st.setString(5, address);
			st.setInt(6, coin);
			rs = st.executeQuery();
			closeDB();

		} catch (SQLException e) {
			System.out.println("회원가입 실패" + e);
		}
	}

}
