package alarm;

public class AlarmHasici extends Alarm{
	
	public String poplach()
	{
		if (getAktivita() == true)
			return ("Kontaktujem hasicov ...\n");
		
		return null;
	}

}
