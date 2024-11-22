package com.kh.student.model;

import java.util.Objects;

public class StudentVO {
	private int no;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private double avg;
	private String grade;
	private int rank;

	public StudentVO() {
		super();
	}
	
	
	public StudentVO(int kor, int eng, int math) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public StudentVO(int no, int kor, int eng, int math) {
		super();
		this.no = no;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public StudentVO(int no, String name, int kor, int eng, int math, int sum, double avg) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = sum;
		this.avg = avg;
	}
	
	public StudentVO(int no, String name, int kor, int eng, int math, int sum, double avg, String grade, int rank) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = sum;
		this.avg = avg;
		this.grade = grade;
		this.rank = rank;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}


	
	@Override
	public String toString() {
		return String.format("%-5s","학번 :") + String.format("%-8s", no) + 
			   String.format("%-5s","이름 :") + String.format("%-8s", name) +
			   String.format("%-7s","국어점수 :") + String.format("%-8s", kor) + 
			   String.format("%-7s","영어점수 :") + String.format("%-8s", eng) + 
			   String.format("%-7s","수학점수 :") + String.format("%-8s", math) + 
			   String.format("%-5s","합계 :") + String.format("%-8s", sum) + 
			   String.format("%-5s","평균 : ") + String.format("%-8s", avg) + 
			   String.format("%-5s","학점 : ") + String.format("%-8s", grade) + 
			   String.format("%-5s","순위 : ") + String.format("%-8s", rank);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sum);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StudentVO)) {
			return false;
	}
		return this.sum == ((StudentVO)obj).sum;
	}
}