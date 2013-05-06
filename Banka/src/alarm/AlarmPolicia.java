package alarm;

public class AlarmPolicia extends Alarm{
	
	public String poplach()
	{
		if (getAktivita() == true)
			return ("Kontaktujem policiu ...\n");
		
		return null;
	}

}
