package alarm;

public class Alarm {
	private String meno;
	private String miesto;
	private Boolean aktivita;
	
	public Boolean getAktivita() {
		return aktivita;
	}

	public void setAktivita(Boolean aktivita) {
		this.aktivita = aktivita;
	}

	public String getMeno() 
	{
		return meno;
	}

	public void setMiesto(String miesto) 
	{
		this.miesto = miesto;
	}

	public String getMiesto() 
	{
		return miesto;
	}
	
	public void setMeno(String meno) 
	{
		this.meno = meno;
	}

	public String poplach()
	{
		if (getAktivita() == true)
			return ("Poplaaach poooplach\n");
		
		return null;
	}
	
}
