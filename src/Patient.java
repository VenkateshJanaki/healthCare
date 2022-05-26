public class Patient 
{
	private String name;
	private int ID;
	private int age;
	private int severity;
	private String reason;
	
	public Patient(String n, int i, int a, int s, String r)
	{
		name = n;
		age = a;
		severity = s;
		reason = r;
		ID = i;
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
	
	public int returnSeverity()
	{
		return severity;
	}
	
	public String returnReason()
	{
		return reason;
	}
}
