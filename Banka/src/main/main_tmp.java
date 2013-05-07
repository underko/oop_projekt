package main;

import gui.*;

import java.util.ArrayList;

import objekt.Dvere;
import objekt.Objekt;
import objekt.Okno;

import senzor.*;
import alarm.*;

public class main_tmp
{
	public static ArrayList<Objekt> zoznamObjektov;
	public static ArrayList<Alarm>  zoznamAlarmov;
	public static ArrayList<Senzor> zoznamSenzorov;
	
	public static final String[] strTypy = {"Alarm", "Senzor", "Objekt"};
	public static final String[] strMiestnosti = {"hala", "office_1", "office_2", "pulty", "trezor", "chodba", "sklad", "wc_muzi", "wc_zeny"};
	public static final String[] strObjekty = {"okno", "dvere", "pult", "ventilacia"};
	
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		hlavneOkno okno = new hlavneOkno();
		
		zoznamSenzorov = new ArrayList<Senzor>();
		zoznamObjektov = new ArrayList<Objekt>();
		zoznamAlarmov= new ArrayList<Alarm>();
		
		//policajny a hasicky alarm pre celu budovu
		Alarm p = new AlarmPolicia();
		p.setMeno("alarm policajny");
		p.setMiesto("Banka");
		p.setAktivita(false);
		pridajAlarm(p);
		
		Alarm h = new AlarmHasici();
		h.setMeno("alarm hasicky");
		h.setMiesto("Banka");
		h.setAktivita(false);
		pridajAlarm(h);
		
		for (int i = 0; i < strMiestnosti.length; i++)
		{
			//vytvori 3 typy alarmov v kazdej miestnosti
			Alarm a1 = new AlarmHlasny();
			a1.setMeno("alarm hlasny" + i);
			a1.setMiesto(strMiestnosti[i]);
			a1.setAktivita(false);
			pridajAlarm(a1);
			
			Alarm a2 = new AlarmTichy();
			a2.setMeno("alarm tichy" + i);
			a2.setMiesto(strMiestnosti[i]);
			a2.setAktivita(false);
			pridajAlarm(a2);
			
			Senzor s1 = new SenzorDym();
			s1.setMeno("senzor dym" + i);
			s1.setMiesto(strMiestnosti[i]);
			s1.setAktivita(false);
			pridajSenzor(s1);
			s1.pridajApodS(a1);
			s1.pridajApodS(h);
			
			Senzor s2 = new SenzorPohyb();
			s2.setMeno("senzor pohyb" + i);
			s2.setMiesto(strMiestnosti[i]);
			s2.setAktivita(false);
			pridajSenzor(s2);
			s2.pridajApodS(a2);
			s2.pridajApodS(p);
			
			Senzor s3 = new SenzorVoda();
			s3.setMeno("senzor voda" + i);
			s3.setMiesto(strMiestnosti[i]);
			s3.setAktivita(false);
			pridajSenzor(s3);
			s3.pridajApodS(a1);
		}
		
		// da sa toto aj nejako elegantnejsie spravit?
		Objekt o01 = new Dvere();
		o01.vytvorObjekt("dvere01", "hala", true);
		Objekt o02 = new Dvere();
		o02.vytvorObjekt("dvere02", "office_1", true);
		Objekt o03 = new Dvere();
		o03.vytvorObjekt("dvere03", "office_1", true);
		Objekt o04 = new Dvere();
		o04.vytvorObjekt("dvere04", "pulty", true);
		Objekt o05 = new Dvere();
		o05.vytvorObjekt("dvere05", "trezor", true);
		Objekt o06 = new Dvere();
		o06.vytvorObjekt("dvere06", "trezor", true);
		Objekt o07 = new Dvere();
		o07.vytvorObjekt("dvere07", "chodba", true);
		Objekt o08 = new Dvere();
		o08.vytvorObjekt("dvere08", "sklad", true);
		Objekt o09 = new Dvere();
		o09.vytvorObjekt("dvere09", "sklad", true);
		Objekt o10 = new Dvere();
		o10.vytvorObjekt("dvere10", "wc_muzi", true);
		Objekt o11 = new Dvere();
		o11.vytvorObjekt("dvere11", "wc_zeny", true);
		
		Objekt o12 = new Okno();
		o12.vytvorObjekt("Okno01", "office_1", true);
		Objekt o13 = new Okno();
		o13.vytvorObjekt("Okno02", "office_1", true);
		Objekt o14 = new Okno();
		o14.vytvorObjekt("Okno03", "office_2", true);
		Objekt o15 = new Okno();
		o15.vytvorObjekt("Okno04", "office_2", true);
		Objekt o16 = new Okno();
		o16.vytvorObjekt("Okno05", "office_2", true);
		Objekt o17 = new Okno();
		o17.vytvorObjekt("Okno06", "pulty", true);
		Objekt o18 = new Okno();
		o18.vytvorObjekt("Okno07", "wc_zeny", true);
		
		pridajObjekt(o01);
		pridajObjekt(o02);
		pridajObjekt(o03);
		pridajObjekt(o04);
		pridajObjekt(o05);
		pridajObjekt(o06);
		pridajObjekt(o07);
		pridajObjekt(o08);
		pridajObjekt(o09);
		pridajObjekt(o10);
		pridajObjekt(o11);
		pridajObjekt(o12);
		pridajObjekt(o13);
		pridajObjekt(o14);
		pridajObjekt(o15);
		pridajObjekt(o16);
		pridajObjekt(o17);
		pridajObjekt(o18);
		
		for (Objekt o: zoznamObjektov)
		{
			SenzorObjekt s = new SenzorObjekt();
			s.setMeno("senzor objekt " + o.getMeno());
			s.setMiesto(o.getMiesto());
			s.setAktivita(false);
			s.setOb(o);
			pridajSenzor(s);
			s.pridajApodS(p);
		}

	}
	
	public static Boolean pridajSenzor(Senzor s)
	{
		for (Senzor tmp: zoznamSenzorov)
		{
			if (tmp.getMeno() == s.getMeno())
				return false;
		}
		
		if (zoznamSenzorov.add(s) == true)
			return true;
		
		return false;
	}
	
	public static Boolean pridajAlarm(Alarm a)
	{
		for (Alarm tmp: zoznamAlarmov)
		{
			if (tmp.getMeno() == a.getMeno())
				return false;
		}
		
		if (zoznamAlarmov.add(a) == true)
			return true;
		
		return false;
	}
	
	public static Boolean pridajObjekt(Objekt o)
	{
		if (zoznamObjektov.add(o) == true)
			return true;
		
		return false;
	}
	
	public static void vypisSenzory()
	{
		String nadpis = String.format("Vypis vsetkych senzorov\n%25s%15s%10s\n", "meno", "miesto", "aktivita");;		
		hlavneOkno.vypis(nadpis + "-----------------------------------------------------------------\n");
		
		for (Senzor s: zoznamSenzorov)
		{
			String riadok = String.format("%25s%15s%10s\n", s.getMeno(), s.getMiesto(), s.getAktivita().toString());
			hlavneOkno.vypis(riadok);
		}
		
		hlavneOkno.vypis("\n");
	}
	
	public static void vypisAlarmy()
	{
		String nadpis = String.format("Vypis vsetkych alarmov\n%25s%15s%10s\n", "meno", "miesto", "aktivita");;		
		hlavneOkno.vypis(nadpis + "-----------------------------------------------------------------\n");
		
		for (Alarm a: zoznamAlarmov)
		{
			String riadok = String.format("%25s%15s%10s\n", a.getMeno(), a.getMiesto(), a.getAktivita());
			hlavneOkno.vypis(riadok);
		}
		
		hlavneOkno.vypis("\n");
	}
	
	public static void vypisObjekty()
	{
		String nadpis = String.format("Vypis vsetkych objektov\n%25s%15s%10s\n", "meno", "miesto", "stav");;		
		hlavneOkno.vypis(nadpis + "-----------------------------------------------------------------\n");
		
		for (Objekt o: zoznamObjektov)
		{
			String riadok = String.format("%25s%15s%10s\n", o.getMeno(), o.getMiesto(), o.getStavBool());
			hlavneOkno.vypis(riadok);
		}
		
		hlavneOkno.vypis("\n");
	}
	
	public static int ziskajAlarmy(String miesto)
	{
		int n = 0;
		
		for(Alarm a: zoznamAlarmov)
		{
			if (a.getMiesto() == miesto)
				n++;
		}
		
		return n;
	}
	
	public static int ziskajSenzory(String miesto)
	{
		int n = 0;
		
		for(Senzor s: zoznamSenzorov)
		{
			if (s.getMiesto() == miesto)
				n++;
		}
		
		return n;
	}
	
	public static int ziskajObjekty(String miesto)
	{
		int n = 0;
		
		for(Objekt o: zoznamObjektov)
		{
			if (o.getMiesto() == miesto)
				n++;
		}
		
		return n;
	}
	
	public static void vytvorSenzor(String meno, String miesto, int typ)
	{
		if (pridajOkno.getPoleMeno().length() > 0)
		{
			Senzor s;
			
			switch(typ)
			{
			case 5: 
				s = new Senzor();
				break;
			case 6: 
				s = new SenzorDym();
				break;
			case 7:
				s = new SenzorObjekt();
				break;
			case 8:
				s = new SenzorPohyb();
				break;
			case 9:
				s = new SenzorVoda();
				break;
			default:
				s = new Senzor();
			}
			
			s.setMeno(meno);
			s.setMiesto(miesto);
			s.setAktivita(false);
		
			if (main_tmp.pridajSenzor(s) == false)
				pridajOkno.vypis("nepodarilo sa vytvorit objekt :(");
			else
			{
				pridajOkno.vypis("Podarilo sa vytvorit novy " + pridajOkno.getCmbTyp().getSelectedItem().toString() + "!\nMeno: " + s.getMeno() + "\nUmiestnenie: " + s.getMiesto() + "\n");
				hlavneOkno.vypis("Pridany zaznam do miestnosti: " + pridajOkno.getCmbMiesto().getItemAt(pridajOkno.getCmbMiesto().getSelectedIndex()) + "\n\n");
			}
		}
		else
			pridajOkno.vypis("Prilis kratke meno pre senzor.\n");
	}
	
	public static void vytvorAlarm(String meno, String miesto, int typ)
	{
		if (pridajOkno.getPoleMeno().length() > 0)
		{
			Alarm a;
			
			switch(typ)
			{
			case 1:
				a = new Alarm();
				break;
			case 2:
				a = new AlarmHlasny();
				break;
			case 3:
				a = new AlarmTichy();
				break;
			case 4:
				a = new AlarmPolicia();
				break;
			case 5:
				a = new AlarmHasici();
				break;
			default:
				a = new Alarm();
				break;
			}
			
			a.setMeno(meno);
			a.setMiesto(miesto);
			a.setAktivita(false);
		
			if (main_tmp.pridajAlarm(a) == false)
				pridajOkno.vypis("nepodarilo sa vytvorit objekt :(");
			else
			{
				pridajOkno.vypis("Podarilo sa vytvorit novy " + pridajOkno.getCmbTyp().getSelectedItem().toString() + "!\nMeno: " + a.getMeno() + "\nUmiestnenie: " + a.getMiesto() + "\n");
				hlavneOkno.vypis("Pridany zaznam do miestnosti: " + pridajOkno.getCmbMiesto().getItemAt(pridajOkno.getCmbMiesto().getSelectedIndex()) + "\n\n");
			}
		}
		else
			pridajOkno.vypis("Prilis kratke meno pre alarm.\n");
	}
	
	public static void vytvorObjekt(String meno, String miesto, int typ)
	{
		if (pridajOkno.getPoleMeno().length() > 0)
		{
			Objekt o = new Objekt();
						
			o.setMeno(meno);
			o.setMiesto(miesto);
			o.setStav(true);
		
			if (main_tmp.pridajObjekt(o) == false)
				pridajOkno.vypis("nepodarilo sa vytvorit objekt :(");
			else
			{
				pridajOkno.vypis("Podarilo sa vytvorit novy " + pridajOkno.getCmbTyp().getSelectedItem().toString() + "!\nMeno: " + o.getMeno() + "\nUmiestnenie: " + o.getMiesto() + "\n");
				hlavneOkno.vypis("Pridany zaznam do miestnosti: " + pridajOkno.getCmbMiesto().getItemAt(pridajOkno.getCmbMiesto().getSelectedIndex()) + "\n\n");
			}
		}
		else
			pridajOkno.vypis("Prilis kratke meno pre senzor.\n");
	}
	
	public static int odstranSenzor(String meno)
	{
		int m = 0;
		int pocet = zoznamSenzorov.size();
		
		for (int i = 0; i < pocet; i++)
		{
			if (zoznamSenzorov.get(i).getMeno().equals(meno))
			{
				zoznamSenzorov.remove(i);
				pocet--;
				i--;
				m++;
			}
		}
			
		return m;
	}
	
	public static int odstranAlarm(String meno)
	{
		int m = 0;
		int pocet = zoznamAlarmov.size();
		
		for (int i = 0; i < pocet; i++)
		{
			if (zoznamAlarmov.get(i).getMeno().equals(meno))
			{
				zoznamAlarmov.remove(i);
				pocet--;
				i--;
				m++;
			}
		}
		
		for (Senzor s: zoznamSenzorov)
		{
			int pocet2 = s.getZoznamApodS().size();
			
			for (int i = 0; i < pocet2; i++)
			{
				if (s.getZoznamApodS().get(i).getMeno().equals(meno))
				{
					s.getZoznamApodS().remove(i);
					pocet2--;
					i--;
					m++;
				}
			}
		}
			
		return m;
	}

	public static int odstranObjekt(String meno) 
	{
		int m = 0;
		int pocet = zoznamObjektov.size();
		
		for (int i = 0; i < pocet; i++)
		{
			if (zoznamObjektov.get(i).getMeno().equals(meno))
			{
				zoznamObjektov.remove(i);
				pocet--;
				i--;
				m++;
			}
		}
	
		pocet = zoznamSenzorov.size();
		
		for (int i = 0; i < pocet; i++)
		{
			if (zoznamSenzorov.get(i).getClass().equals("SenzorObjekt"))
			{
				int pocet2 = zoznamSenzorov.get(i).getZoznamApodS().size();
				
				for (int j = 0; j < pocet2; j++)
				{
					if (((SenzorObjekt) zoznamSenzorov.get(i)).getOb().equals(meno))
					{
						zoznamSenzorov.remove(i);
						pocet2--;
						j--;
						m++;
					}
					
				}
			}
		}
		
		return m;	
	}
	
	public static Alarm getAlarm(String meno)
	{
		for (Alarm a: zoznamAlarmov)
		{
			if (a.getMeno().equals(meno))
				return a;
		}
		
		return null;
	}
	
	public static Senzor getSenzor(String meno)
	{
		for (Senzor s: zoznamSenzorov)
		{
			if (s.getMeno().equals(meno))
				return s;
		}
		
		return null;
	}
	
	public static Objekt getObjekt(String meno)
	{
		for (Objekt o: zoznamObjektov)
		{
			if (o.getMeno().equals(meno))
				return o;
		}
		
		return null;
	}
	
	public static void simulacia(int typ, String info)
	{		
		if (typ == 0)	//poziar
		{
			hlavneOkno.setImg(info, "c");
			
			for (Senzor s: main_tmp.zoznamSenzorov)
			 {
				 if (s.getClass().getSimpleName().contains("SenzorDym") && s.getMiesto() == info)
				 {
					 s.setAktivita(true);
					 hlavneOkno.vypis("Senzor: " + s.getMeno() + " zaznamenal dym v priestore: " + s.getMiesto() + "\n");
					 s.aktivujAlarm();	 
				 }
			 }
			 
			hlavneOkno.vypis("\n");
			
			 for (Alarm a: main_tmp.zoznamAlarmov)
			 {
				 if (a.getAktivita() == true)
				 {
					 hlavneOkno.vypis("Aktivny alarm " + a.getMeno() + " v miestnosti " + a.getMiesto() + "\n");
					 hlavneOkno.vypis(a.poplach());
				 }
			 }
			 
			 hlavneOkno.vypis("\n");
		}
		else if (typ == 1)	//voda
		{
			hlavneOkno.setImg(info, "c");
			
			for (Senzor s: main_tmp.zoznamSenzorov)
			 {
				 if (s.getClass().getSimpleName().contains("SenzorVoda") && s.getMiesto() == info)
				 {
					 s.setAktivita(true);
					 hlavneOkno.vypis("aktivujem: " + s.getMeno() + "\n");
					 s.aktivujAlarm();	 
				 } 
			 }
			 
			hlavneOkno.vypis("\n");
			
			 for (Alarm a: main_tmp.zoznamAlarmov)
			 {
				 if (a.getAktivita() == true)
				 {
					 hlavneOkno.vypis("Aktivny alarm " + a.getMeno() + " v miestnosti " + a.getMiesto() + "\n");
					 hlavneOkno.vypis(a.poplach());
				 }
			 }
			 
			 hlavneOkno.vypis("\n");
		}
		else if (typ == 2)	//poskodenie objektu
		{
			String miesto = new String();
			
			for (Objekt o: main_tmp.zoznamObjektov)
			{
				if (o.getMeno() == info)
				{
					o.setStav(false);
					hlavneOkno.vypis(o.getStav() + "\n");
					miesto = o.getMiesto();
				}
			}
			 
			hlavneOkno.vypis("\n");
			hlavneOkno.setImg(miesto, "c");
			
			for (Senzor s: main_tmp.zoznamSenzorov)
			{
				if (s.getClass().getSimpleName().contains("SenzorObjekt"))
				{
					if (!(((SenzorObjekt) s).getStavObjektu()))
					{
						s.setAktivita(true);
						hlavneOkno.vypis("aktivujem: " + s.getMeno() + "\n");
						s.aktivujAlarm();
					}
				}
				else if (s.getClass().getSimpleName().contains("SenzorPohyb"))
				{
					if (s.getMiesto().equals(miesto))
					{
						s.setAktivita(true);
						hlavneOkno.vypis("aktivujem: " + s.getMeno() + "\n");
						s.aktivujAlarm();
					}
				}
			 }
			 
			hlavneOkno.vypis("\n");
			
			 for (Alarm a: main_tmp.zoznamAlarmov)
			 {
				 if (a.getAktivita() == true)
				 {
					 hlavneOkno.vypis("Aktivny alarm " + a.getMeno() + " v miestnosti " + a.getMiesto() + "\n");
					 hlavneOkno.vypis(a.poplach());
				 }
			 }
		}
	}
}
