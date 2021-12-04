package List;

public class StoreList {
	
	String s_name;
	String s_addr;
	int s_open;
	int s_close;
	String s_tel;
	int timemax;
	int s_key; 
	
    StoreList(){  }
    
	public String getS_name() {
		return s_name;
	}

	public String getS_addr() {
		return s_addr;
	}

	public String getS_tel() {
		return s_tel;
	}


	public int getS_close() {
		return s_close;
	}


	public int getTimemax() {
		return timemax;
	}


	public int getS_key() {
		return s_key;
	}

	public int getS_open() {
		return s_open;
	}
	
	
    
    public StoreList(int s_key, String s_name,String s_addr,
    		String s_tel,int s_open,int s_close,int timemax) {
        this.s_key = s_key;
        this.s_name = s_name;
        this.s_addr = s_addr;
        this.s_tel = s_tel;
        this.s_open = s_open;
        this.s_close = s_close;
        this.timemax = timemax;
    }
    
    public void showInfo(){
        System.out.println("(key) : " + s_key);
        System.out.println("(name) : " + s_name);
        System.out.println("(addr) : " + s_addr);
        System.out.println("(tel) : " + s_tel);
        System.out.println("(open) : " + s_open);
        System.out.println("(close) : " + s_close);
        System.out.println("(timemax) : " + timemax);
    }
}
