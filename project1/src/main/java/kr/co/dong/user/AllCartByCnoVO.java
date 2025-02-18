package kr.co.dong.user;

public class AllCartByCnoVO {
	private int cno;
	private int allcartquant;
	private int alltotal;
	
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getAllcartquant() {
		return allcartquant;
	}
	public void setAllcartquant(int allcartquant) {
		this.allcartquant = allcartquant;
	}
	public int getAlltotal() {
		return alltotal;
	}
	public void setAlltotal(int alltotal) {
		this.alltotal = alltotal;
	}
	@Override
	public String toString() {
		return "AllCartByCnoVO [cno=" + cno + ", allcartquant=" + allcartquant + ", alltotal=" + alltotal + "]";
	}
	
	
	
}
