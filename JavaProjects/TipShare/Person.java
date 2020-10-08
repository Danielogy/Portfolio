package tipshare;

public class Person {
	private String name;
	private double hoursWorked;
	
	Person(){}
	
	Person(String n, double h){
		this.name = n;
		this.hoursWorked = h;
	}
	
	public Double getHours(){
		return hoursWorked;
	}
	
	public String getName() {
		return name;
	}
	public String toString() {
		return "Name: " + name + " Hours Worked: " + hoursWorked + "\n";
	}
}
