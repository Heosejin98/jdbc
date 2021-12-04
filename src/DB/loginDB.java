package DB;

import java.sql.*;

public class loginDB extends DTO {
	String sql;



	public boolean IdPW_Check(String id, String pw) {
		boolean check = false;

		sql = "select id from member where id=? AND password=?";
		try {
			conn = DriverManager.getConnection(Uri, Id, Pw);
			st = conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, pw);
			rs = st.executeQuery();
			check = rs.next();
			closeDB();
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("DB 로드 실패" + e);
		} 
		return check;
	}

	public String getname(String id) {
		String name = "";

		try {
			sql = "select name from member where id='" + id + "'";
			conn = DriverManager.getConnection(Uri, Id, Pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			name = rs.getString(1);
			closeDB();
			return name;

		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("DB 로드 실패" + e);
			return name;
		}
	}

	public int key_val(String id) {
		int value = 0;

		try {
			sql = "select key from member where id='" + id + "'";
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

	public int key_val(String id, String table) {
		int value = 0;

		try {
			sql = "select key from "+ table +" where id='" + id + "'";
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
	
	
	public int key_val(String id, String table, String colum) {
		int value = 0;

		try {
			sql = "select "+colum+" from "+ table +" where id='" + id + "'";
			conn = DriverManager.getConnection(Uri, Id, Pw);
			stmt = conn.createStatement();
			rs =  stmt.executeQuery(sql);
			rs.next();
			value = rs.getInt(1);
			closeDB();
			
			return value;
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			System.out.println("키값 불러오기 실패" + e);
			return value;
		}
	}
	
	public int storekey_val(String id) {
		int value = 0;

		try {
			sql = "select storekey  from store_manager where id ='" + id + "'";
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
}
