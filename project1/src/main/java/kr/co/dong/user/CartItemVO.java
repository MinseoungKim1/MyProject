package kr.co.dong.user;

public class CartItemVO {
	private int pno;
	private int cno;
	private int cartQuant;
	
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getCartQuant() {
		return cartQuant;
	}
	public void setCartQuant(int cartQuant) {
		this.cartQuant = cartQuant;
	}
	@Override
	public String toString() {
		return "CartItemVO [pno=" + pno + ", cno=" + cno + ", cartQuant=" + cartQuant + "]";
	}
	
	
	
	
	
	
}
