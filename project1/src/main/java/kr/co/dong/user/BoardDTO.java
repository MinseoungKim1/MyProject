/*Table: board
Columns:
bno int AI PK 
bno varchar(45) 
content varchar(45) 
id varchar(45) 
reddate datetime 
readcnt int 
etc varchar(45)
 * 
 */
package kr.co.dong.user;

public class BoardDTO {
	
	private int CUSTOMER_NUMBER;
	private String CUSTOMER_ID;
	private String CUSTOMER_NAME;
	private String CUSTOMER_EMAIL;
	private String CUSTOMER_PASSWORD;
	private String CUSTOMER_GRADE;
	private String CUSTOMER_STATUS;
	private String CUSTOMER_PHONE;
	private String CUSTOMER_ADDRESS1;
	private String CUSTOMER_ADDRESS2;
	private String CUSTOMER_ADDRESS3;
	private String CUSTOMER_DATE_AT;
	private String CUSTOMER_GENDER;
	
	public int getCUSTOMER_NUMBER() {
		return CUSTOMER_NUMBER;
	}
	public void setCUSTOMER_NUMBER(int cUSTOMER_NUMBER) {
		CUSTOMER_NUMBER = cUSTOMER_NUMBER;
	}
	public String getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}
	public void setCUSTOMER_ID(String cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}
	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}
	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}
	public String getCUSTOMER_EMAIL() {
		return CUSTOMER_EMAIL;
	}
	public void setCUSTOMER_EMAIL(String cUSTOMER_EMAIL) {
		CUSTOMER_EMAIL = cUSTOMER_EMAIL;
	}
	public String getCUSTOMER_PASSWORD() {
		return CUSTOMER_PASSWORD;
	}
	public void setCUSTOMER_PASSWORD(String cUSTOMER_PASSWORD) {
		CUSTOMER_PASSWORD = cUSTOMER_PASSWORD;
	}
	public String getCUSTOMER_GRADE() {
		return CUSTOMER_GRADE;
	}
	public void setCUSTOMER_GRADE(String cUSTOMER_GRADE) {
		CUSTOMER_GRADE = cUSTOMER_GRADE;
	}
	public String getCUSTOMER_STATUS() {
		return CUSTOMER_STATUS;
	}
	public void setCUSTOMER_STATUS(String cUSTOMER_STATUS) {
		CUSTOMER_STATUS = cUSTOMER_STATUS;
	}
	public String getCUSTOMER_PHONE() {
		return CUSTOMER_PHONE;
	}
	public void setCUSTOMER_PHONE(String cUSTOMER_PHONE) {
		CUSTOMER_PHONE = cUSTOMER_PHONE;
	}
	public String getCUSTOMER_ADDRESS1() {
		return CUSTOMER_ADDRESS1;
	}
	public void setCUSTOMER_ADDRESS1(String cUSTOMER_ADDRESS1) {
		CUSTOMER_ADDRESS1 = cUSTOMER_ADDRESS1;
	}
	public String getCUSTOMER_ADDRESS2() {
		return CUSTOMER_ADDRESS2;
	}
	public void setCUSTOMER_ADDRESS2(String cUSTOMER_ADDRESS2) {
		CUSTOMER_ADDRESS2 = cUSTOMER_ADDRESS2;
	}
	public String getCUSTOMER_ADDRESS3() {
		return CUSTOMER_ADDRESS3;
	}
	public void setCUSTOMER_ADDRESS3(String cUSTOMER_ADDRESS3) {
		CUSTOMER_ADDRESS3 = cUSTOMER_ADDRESS3;
	}
	public String getCUSTOMER_DATE_AT() {
		return CUSTOMER_DATE_AT;
	}
	public void setCUSTOMER_DATE_AT(String cUSTOMER_DATE_AT) {
		CUSTOMER_DATE_AT = cUSTOMER_DATE_AT;
	}
	
	
	public String getCUSTOMER_GENDER() {
		return CUSTOMER_GENDER;
	}
	public void setCUSTOMER_GENDER(String cUSTOMER_GENDER) {
		CUSTOMER_GENDER = cUSTOMER_GENDER;
	}
	@Override
	public String toString() {
		return "BoardDTO [CUSTOMER_NUMBER=" + CUSTOMER_NUMBER + ", CUSTOMER_ID=" + CUSTOMER_ID + ", CUSTOMER_NAME="
				+ CUSTOMER_NAME + ", CUSTOMER_EMAIL=" + CUSTOMER_EMAIL + ", CUSTOMER_PASSWORD=" + CUSTOMER_PASSWORD
				+ ", CUSTOMER_GRADE=" + CUSTOMER_GRADE + ", CUSTOMER_STATUS=" + CUSTOMER_STATUS + ", CUSTOMER_PHONE="
				+ CUSTOMER_PHONE + ", CUSTOMER_ADDRESS1=" + CUSTOMER_ADDRESS1 + ", CUSTOMER_ADDRESS2="
				+ CUSTOMER_ADDRESS2 + ", CUSTOMER_ADDRESS3=" + CUSTOMER_ADDRESS3 + ", CUSTOMER_DATE_AT="
				+ CUSTOMER_DATE_AT + ", CUSTOMER_GENDER=" + CUSTOMER_GENDER + "]";
	}
	
	
	
	

}
