package kr.co.dong.ad;

public class ReviewVO {
	private int rid;
	private int pno;
	private int cno;
	private String cid;
	private int rating;
	private String rcontext;
	private String rdate;
	
	
	private int average;
	private int count;
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getRcontext() {
		return rcontext;
	}
	public void setRcontext(String rcontext) {
		this.rcontext = rcontext;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ReviewVO(int rid, int pno, int cno, String cid, int rating, String rcontext, String rdate, int average,
			int count) {
		super();
		this.rid = rid;
		this.pno = pno;
		this.cno = cno;
		this.cid = cid;
		this.rating = rating;
		this.rcontext = rcontext;
		this.rdate = rdate;
		this.average = average;
		this.count = count;
	}
	public ReviewVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ReviewVO [rid=" + rid + ", pno=" + pno + ", cno=" + cno + ", cid=" + cid + ", rating=" + rating
				+ ", rcontext=" + rcontext + ", rdate=" + rdate + ", average=" + average + ", count=" + count + "]";
	}
	
	
}


