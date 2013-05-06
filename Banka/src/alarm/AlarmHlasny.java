package alarm;

public class AlarmHlasny extends Alarm{
	
	public String poplach()
	{
		if (getAktivita() == true)
			return ("POPLAAACH POOOOPLACH\n");
		
		return null;
	}

}
