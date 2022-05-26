package healthCare;
public class Patient 
{
	private String name;
	private int ID;
	private int age;
	private String severity;
	private String reason;
	
	public Patient(String n, int a, String s, String r)
	{
		name = n;
		age = a;
		severity = s;
		reason = r;
		ID = (int) (Math.random() * 10000);
	}
	
	public String returnName()
	{
		return name;
	}
	
	public int returnID()
	{
		return ID;
	}
	
	public int returnAge()
	{
		return age;
	}
	
	public String returnSeverity()
	{
		return severity;
	}
	
	public String returnReason()
	{
		return reason;
	}
}
