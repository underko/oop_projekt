package senzor;

import java.util.ArrayList;

import alarm.*;
import gui.*;

public class Senzor {
	
	private String meno;
	private String miesto;
	private Boolean aktivita;
	private ArrayList<Alarm> zoznamApodS = new ArrayList<Alarm>(); 
	
	public ArrayList<Alarm> getZoznamApodS() {
		return zoznamApodS;
	}

	public void setZoznamApodS(ArrayList<Alarm> zoznamApodS) {
		this.zoznamApodS = zoznamApodS;
	}

	public String getMeno()
	{
		return meno;
	}
	
	public void setMeno(String meno)
	{
		this.meno = meno;
	}

	public String getMiesto()
	{
		return miesto;
	}
	
	public void setMiesto(String miesto)
	{
		this.miesto = miesto;
	}
	
	public Boolean getAktivita()
	{
		return this.aktivita;
	}
	
	public void setAktivita(Boolean b)
	{
		this.aktivita = b;
	}
	
	public Boolean pridajApodS(Alarm a)
	{
		return zoznamApodS.add(a);
	}
	
	public void aktivujAlarm()
	{
		if (aktivita == true && zoznamApodS != null)
		{
			hlavneOkno.vypis("Aktivujem prislusne alarmy !\n");
			
			for (Alarm a: zoznamApodS)
				a.setAktivita(true);	
		}
	}
	
}
