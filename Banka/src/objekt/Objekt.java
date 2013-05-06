package objekt;

public class Objekt {

	private String meno;
	private String miesto;
	private Boolean stav;
	
	public void vytvorObjekt(String meno, String miesto, Boolean stav)
	{
		if (meno.length() == 0 || miesto.length() == 0)
			System.out.println("meno/miesto error");
		else
		{
			this.meno = meno;
			this.miesto = miesto;
			this.stav = stav;
		}
	}
	
	public void setMeno(String meno) {
		this.meno = meno;
	}

	public void setMiesto(String miesto) {
		this.miesto = miesto;
	}
	
	public void setStav(Boolean stav)
	{
		this.stav = stav;
	}
	
	public Boolean getStavP()
	{
		return stav;
	}
	
	public String getMeno() {
		return meno;
	}

	public String getMiesto() {
		return miesto;
	}

	public String getStav()
	{
		if (stav == true)
			return ("Objekt nie je poskodeny");
		else if (stav == false)
			return ("Objekt je poskodeny\nMiesto: " + this.getMiesto() + "\n");
		
		return ("Objekt je poskodeny\nMiesto: " + this.getMiesto() + "\n");
	}
	
	public Boolean getStavBool()
	{
		return stav;
	}
}
