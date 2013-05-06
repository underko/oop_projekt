package objekt;

public class Okno extends Objekt{

	public String getStav()
	{
		if (getStavP() == true)
			return ("Okno nie je rozbite");
		else if (getStavP() == false)
			return ("Okno je rozbite\nMiesto: " + this.getMiesto() + "\n");
		
		return ("Okno je rozbite\nMiesto: " + this.getMiesto() + "\n");
	}
}
