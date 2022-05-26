package healthCare;
import java.util.Scanner;
import java.util.ArrayList;

// ask about scanner not recognizing reason, creating new constructor

public class HealthCare 
{
	private static ArrayList<Patient> patientList= new ArrayList<Patient>();
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter name of patient: ");
		String inputName = scanner.nextLine();
		//System.out.println(inputName);
		System.out.println("");
		
		System.out.println("Enter age of patient: ");
		String temp = scanner.nextLine();
		int inputAge = Integer.parseInt(temp);
		//System.out.println(inputAge);
		System.out.println("");
		
		System.out.println("Enter reason of hospitalization: ");
		String inputReason = scanner.nextLine();
		//System.out.println(inputReason);
		System.out.println("");
		
		int inputID = (int)(Math.random() * 10000);
		int inputSeverity = (int)(Math.random() * 4); 
		//System.out.println(inputID + inputSeverity);
		
		addPatient(inputName, inputID, inputAge, inputSeverity, inputReason);
		
		System.out.println("Patient " + inputName + ", " 
							+ "age " + inputAge + ", "
							+ "has been assigned the ID " + inputID + ", "
							+ "and has been hospitalized because of " + inputReason 
							+ " with a severity of " + inputSeverity + ".");
		
		
	}
	
	public static void addPatient(String n, int i, int a, int s, String r)
	{
		Patient newPatient = new Patient(n, i, a, s, r);
		patientList.add(newPatient);
	}
	
}
