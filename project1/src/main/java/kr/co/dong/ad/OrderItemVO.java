package kr.co.dong.ad;

public class OrderItemVO {
	private int id;
	private int cno;
	private int oid;
	private int pno;
	private String pname;
	private int orderquan;
	private int pprice;
	private String pcat;
	private String odate;
	private int ototal;
	
	
	private String month;
	private int count;
	private String ono;
	private String ostatus;

	private int revenue;
	private int Dcount;
	private int Edcount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getOrderquan() {
		return orderquan;
	}
	public void setOrderquan(int orderquan) {
		this.orderquan = orderquan;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public String getPcat() {
		return pcat;
	}
	public void setPcat(String pcat) {
		this.pcat = pcat;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public int getOtotal() {
		return ototal;
	}
	public void setOtotal(int ototal) {
		this.ototal = ototal;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	public String getOstatus() {
		return ostatus;
	}
	public void setOstatus(String ostatus) {
		this.ostatus = ostatus;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public int getDcount() {
		return Dcount;
	}
	public void setDcount(int dcount) {
		Dcount = dcount;
	}
	public int getEdcount() {
		return Edcount;
	}
	public void setEdcount(int edcount) {
		Edcount = edcount;
	}
	public OrderItemVO(int id, int cno, int oid, int pno, String pname, int orderquan, int pprice, String pcat,
			String odate, int ototal, String month, int count, String ono, String ostatus, int revenue, int dcount,
			int edcount) {
		super();
		this.id = id;
		this.cno = cno;
		this.oid = oid;
		this.pno = pno;
		this.pname = pname;
		this.orderquan = orderquan;
		this.pprice = pprice;
		this.pcat = pcat;
		this.odate = odate;
		this.ototal = ototal;
		this.month = month;
		this.count = count;
		this.ono = ono;
		this.ostatus = ostatus;
		this.revenue = revenue;
		Dcount = dcount;
		Edcount = edcount;
	}
	public OrderItemVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderItemVO [id=" + id + ", cno=" + cno + ", oid=" + oid + ", pno=" + pno + ", pname=" + pname
				+ ", orderquan=" + orderquan + ", pprice=" + pprice + ", pcat=" + pcat + ", odate=" + odate
				+ ", ototal=" + ototal + ", month=" + month + ", count=" + count + ", ono=" + ono + ", ostatus="
				+ ostatus + ", revenue=" + revenue + ", Dcount=" + Dcount + ", Edcount=" + Edcount + "]";
	}
	

		
}

