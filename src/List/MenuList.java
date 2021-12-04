package List;

public class MenuList {
	String m_name;
	int price; 
	
	public MenuList(){  }
	
	   public MenuList(int price, String m_name) {
	        this.price = price;
	        this.m_name = m_name;
	   
	    }

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
