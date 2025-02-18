package kr.co.dong.ad;

public class NoticeVO {
	  	private int eno;
	    private String ntitle;
	    private String ncontent;
	    private String ndate;
	    private String nimg;
	    
		
		public int getEno() {
			return eno;
		}
		public void setEno(int eno) {
			this.eno = eno;
		}
		public String getNtitle() {
			return ntitle;
		}
		public void setNtitle(String ntitle) {
			this.ntitle = ntitle;
		}
		public String getNcontent() {
			return ncontent;
		}
		public void setNcontent(String ncontent) {
			this.ncontent = ncontent;
		}
		public String getNdate() {
			return ndate;
		}
		public void setNdate(String ndate) {
			this.ndate = ndate;
		}
		
		public String getNimg() {
			return nimg;
		}
		public void setNimg(String nimg) {
			this.nimg = nimg;
		}
		@Override
		public String toString() {
			return "NoticeVO [eno=" + eno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", ndate=" + ndate
					+ ", nimg=" + nimg + "]";
		}
	
	
}
