package objekt;

public class Dvere extends Objekt{

	public String getStav()
	{
		if (getStavP() == true)
			return ("Dvere nie su poskodene");
		else if (getStavP() == false)
			return ("Dvere su poskodene\nMiesto: " + this.getMiesto() + "\n");
		
		return ("Dvere su poskodene\nMiesto: " + this.getMiesto() + "\n");
	}
}
