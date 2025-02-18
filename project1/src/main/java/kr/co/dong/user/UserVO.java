package kr.co.dong.user;

public class UserVO {
	private int cno;
	private String cid;
	private String cname;
	private String cemail;
	private String cpass;
	private String cgrade;
	private String cstatus;
	private String cphone;
	private String cadd1;
	private String cadd2;
	private String cadd3;
	private String cdateq;
	private String cgender;
	
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCpass() {
		return cpass;
	}
	public void setCpass(String cpass) {
		this.cpass = cpass;
	}
	public String getCgrade() {
		return cgrade;
	}
	public void setCgrade(String cgrade) {
		this.cgrade = cgrade;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCadd1() {
		return cadd1;
	}
	public void setCadd1(String cadd1) {
		this.cadd1 = cadd1;
	}
	public String getCadd2() {
		return cadd2;
	}
	public void setCadd2(String cadd2) {
		this.cadd2 = cadd2;
	}
	public String getCadd3() {
		return cadd3;
	}
	public void setCadd3(String cadd3) {
		this.cadd3 = cadd3;
	}
	public String getCdateq() {
		return cdateq;
	}
	public void setCdateq(String cdateq) {
		this.cdateq = cdateq;
	}
	public String getCgender() {
		return cgender;
	}
	public void setCgender(String cgender) {
		this.cgender = cgender;
	}
	@Override
	public String toString() {
		return "UserVO [cno=" + cno + ", cid=" + cid + ", cname=" + cname + ", cemail=" + cemail + ", cpass=" + cpass
				+ ", cgrade=" + cgrade + ", cstatus=" + cstatus + ", cphone=" + cphone + ", cadd1=" + cadd1 + ", cadd2="
				+ cadd2 + ", cadd3=" + cadd3 + ", cdateq=" + cdateq + ", cgender=" + cgender + "]";
	}
	
	
	
	
	
}
