package bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Student {
	
		private String sno;
		private String sname;
		@JsonFormat(pattern="yyy/MM/dd", timezone="GMT+8")
		private Date sbday;
		private Integer ssex;
		private String smail;
		private String spwd;
		
		public String getSno() {
			return sno;
		}
		public void setSno(String sno) {
			this.sno = sno;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public Date getSbday() {
			return sbday;
		}
		public void setSbday(Date sbday) {
			this.sbday = sbday;
		}
		public Integer getSsex() {
			return ssex;
		}
		public void setSsex(Integer ssex) {
			this.ssex = ssex;
		}
		public String getSmail() {
			return smail;
		}
		public void setSmail(String smail) {
			this.smail = smail;
		}
		public String getSpwd() {
			return spwd;
		}
		public void setSpwd(String spwd) {
			this.spwd = spwd;
		}
		
}
