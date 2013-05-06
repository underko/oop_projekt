package senzor;
import objekt.Objekt;


public class SenzorObjekt extends Senzor{

	Objekt o;

	public Objekt getOb() {
		return o;
	}

	public void setOb(Objekt o) {
		this.o = o;
	}
	
	public Boolean getStavObjektu()
	{
		return o.getStavBool();
	}
		
	//to do akcia ked je poskodeny objekt
}
