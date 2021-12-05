package List;

import java.sql.Date;

public class OrderInfoList {
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	int time;
	String storename;
	Date date; 
	
	OrderInfoList() {}
	
	public OrderInfoList(String s_name, Date date, int time) {
		this.storename = s_name;
		this.date = date;
		this.time = time;
	}
}
