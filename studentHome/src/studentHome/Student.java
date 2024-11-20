package studentHome;

import java.util.Objects;

public class Student {
	private int no;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private double avg;

	
	public Student(int no, String name, int kor, int eng, int math, int sum, double avg) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = sum;
		this.avg = avg;
	}
	
	public Student(int kor, int eng, int math) {
		super();
		this.no = no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
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

	
	
	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ sum + ", avg=" + avg + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(sum);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Student)) {
			return false;
	}
		return this.sum == ((Student)obj).sum;
	}
}

