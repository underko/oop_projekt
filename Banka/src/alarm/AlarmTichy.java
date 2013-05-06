package alarm;

public class AlarmTichy extends Alarm{
	
	public String poplach()
	{
		if (getAktivita() == true)
			return ("Potichucku zavolam policajtov :D\n");
		
		return null;
	}

}
