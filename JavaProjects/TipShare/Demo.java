package tipshare;
import java.text.DecimalFormat;
import java.util.*;

public class Demo {

	public static void main(String[] args){
		//variables for demo
		Scanner kb = new Scanner(System.in);
		double barHours;
		double hostHours;
		
		//create Lists for bartenders and hosts and populate them
		List<Person> bartenders = new ArrayList<Person>();
		List<Person> hosts = new ArrayList<Person>();
		barHours = getNumBartender(kb, bartenders);
		hostHours = getNumHosts(kb, hosts);
		
		//get all necessary numbers for calculations
		Double totalMoney = getTotalMoney(kb);
		double totalHours = barHours + hostHours;
		double multiplier = totalMoney / totalHours;
		
		//calculate individual person's take-home tip
		calculateTakeHomeMoney(bartenders, hosts, multiplier);
		
	}
	
	private static Double getNumBartender(Scanner kb, List<Person> b){
		int num;
		double totalHours = 0;
		
		System.out.print("How many Bartenders? ");
		num = kb.nextInt();
		
		for(int i = 0; i < num; i++) {
			String name;
			double hoursWorked;
			
			
			System.out.print("Name: ");
			name = kb.next();
			System.out.print("Hours Worked: ");
			hoursWorked = kb.nextDouble();
			totalHours += hoursWorked;
			
			b.add(new Person(name, hoursWorked));
		}
		
		return totalHours;
	}

	private static Double getNumHosts(Scanner kb, List<Person> h){
		int num;
		double totalHours = 0;
		
		System.out.print("How many Hosts? ");
		num = kb.nextInt();
		
		for(int i = 0; i < num; i++) {
			String name;
			double hoursWorked;
			
			System.out.print("Name: ");
			name = kb.next();
			System.out.print("Hours Worked: ");
			hoursWorked = kb.nextDouble();
			totalHours += hoursWorked;
			
			h.add(new Person(name, hoursWorked));
		}
		
		return totalHours;
	}
	
	private static Double getTotalMoney(Scanner kb) {
		double totalMoney;
		System.out.print("What was the total amount of money collected?");
		totalMoney = kb.nextDouble();
		
		return totalMoney;
	}
	
	private static void calculateTakeHomeMoney(List<Person> b, List<Person> h, Double m){   
		DecimalFormat d = new DecimalFormat("#.##");
		for(int i = 0; i < b.size(); i++) {
			double takeHome;
			takeHome = b.get(i).getHours() * m;
			System.out.println(b.get(i).getName() + " $" + d.format(takeHome));
		}
		for(int i = 0; i < h.size(); i++) {
			double takeHome;
			takeHome = h.get(i).getHours() * m;
			System.out.println(h.get(i).getName() + ": $" + d.format(takeHome));
		}
	}
}
