package alarm;

public class AlarmHasici extends Alarm{
	
	public String poplach()
	{
		if (getAktivita() == true)
			return ("Pustam ochranne prvky ...\nKontaktujem hasicov ...\n");
		
		return null;
	}

}
