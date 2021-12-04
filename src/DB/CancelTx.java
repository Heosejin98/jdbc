package DB;
import java.sql.*;
import java.sql.CallableStatement;
//자바 클래스
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CancelTx extends DTO{
	public int guest_key;
	
	static DTO dto = new DTO();

	public CancelTx() {}

	public void getCost(int g_key)
	{
		
		int get_cost = 0;
		try 
		{
			conn = DriverManager.getConnection(Uri,Id, Pw);
			System.out.println("연결");
			
			String sql = "select TOTAL_COST from ORDER_T where guest_key = ?";

			st = conn.prepareStatement(sql);
			st.setInt(1, g_key);
			rs = st.executeQuery();
			rs.next();
			get_cost = rs.getInt(1);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("DB 로드 실패");
		}
		
	

		Cancellation(get_cost, g_key, conn);
		System.out.println("되냐?");
	}
	
	void Cancellation(int total_cost, int g_key, Connection con)
	{
		try
		{
			con.setAutoCommit(false);
			PreparedStatement pstmt1 = con.prepareStatement("update GUEST set COIN  = COIN + ? where KEY = ?");
			PreparedStatement pstmt2 = con.prepareStatement("update GUEST set POINT = POINT + ? where KEY = ?");
			
			pstmt1.setInt(1, total_cost);
			pstmt1.setInt(2, g_key);
			pstmt1.addBatch();
			int [] cnt1 = pstmt1.executeBatch();
			
			pstmt2.setInt(1, -1*total_cost);
			pstmt2.setInt(2, g_key);
			pstmt2.addBatch();
			int [] cnt2 = pstmt2.executeBatch();
			
			con.commit();
			con.setAutoCommit(true);
			closeDB();
			
		}
		
		catch(SQLException e)
		{
			System.out.println("cancel실패" + e);
		}
	}


}
