package com.kh.subjectMVCProject.model;

import java.sql.Date;

public class TraineeVO {
	private int no; // pk sesqunece
	private String s_num;
	private String abbre; // 과목 요약
	private String section;
	private Date sdate; // 과목 이름

	public TraineeVO() {
		super();
	}

	public TraineeVO(int no, String s_num, String abbre, String section, Date sdate) {
		super();
		this.no = no;
		this.s_num = s_num;
		this.abbre = abbre;
		this.section = section;
		this.sdate = sdate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getAbbre() {
		return abbre;
	}

	public void setAbbre(String abbre) {
		this.abbre = abbre; 
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@Override
	public String toString() {
		return "TraineeVO [no=" + no + ", s_num=" + s_num + ", abbre=" + abbre + ", section=" + section + ", sdate="
				+ sdate + "]";
	}

																																																																																																																																																																																																																																																								}
