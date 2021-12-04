package Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import DB.DTO;
import DB.GuestDB;
import DB.StoreDB;
import DB.loginDB;
import List.StoreList;

public class test {

	static DTO dto = new DTO();
	static StoreDB sb = new StoreDB();

	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		loginDB ld = new loginDB();
		StoreDB db = new StoreDB();
		Boolean a = false;

		Date date = new Date(0);

		GuestDB gb = new GuestDB();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("2021-11-13");

		String formattedDate = simpleDateFormat.format(date);

		java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);


	

	}

}
