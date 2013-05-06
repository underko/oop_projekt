package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import objekt.Objekt;

import senzor.*;
import main.*;
import alarm.*;

@SuppressWarnings("serial")

public class pridajOkno extends JFrame{

	private static JFrame okno;
	private static JButton btnVytvor, btnOdstran, btnPrirad;
	private static JTextPane pole;
	private static JTextField poleMeno;
	private static JComboBox<String> cmbMiesto, cmbObjekt, cmbTyp, cmbVolba, cmbP1, cmbP2;
	
	private static JLabel labelMeno, labelMiesto, labelTyp, labelVazba;
	private static JScrollPane sBar;
	
	String[] vazba;
	String[] strTypy = {"Alarm", "Alarm Hlasny", "Alarm Tichy", "Alarm Policajny", "Alarm Hasicky", "Senzor", "Senzor Dymovy", "Senzor Objekt", "Senzor Pohyb", "Senzor Voda", "Objekt"};
	String[] strVolba = {"senzor/alarm", "senzor/objekt"};
	
	final static Border obrys = BorderFactory.createLineBorder(Color.black);
	
	static StyledDocument poleDoc;
	
	public pridajOkno()
	{
		okno = new JFrame();
		
		btnVytvor = new JButton("Vytvor");
		btnOdstran = new JButton("Odstran");
		btnPrirad = new JButton("Prirad");
		pole = new JTextPane();
		poleMeno = new JTextField();
		poleDoc = pole.getStyledDocument();
		
		setCmbTyp(new JComboBox<String>(strTypy));
		setCmbMiesto(new JComboBox<String>(main_tmp.strMiestnosti));
		setCmbObjekt(new JComboBox<String>(main_tmp.strObjekty));
		setCmbVolba(new JComboBox<String>(strVolba));
				
		labelMeno = new JLabel("Zadajte meno: ");
		labelMiesto = new JLabel("Vyberte umiestnenie: ");
		labelTyp = new JLabel("Vyberte typ : ");
		labelVazba = new JLabel("Zviazat s: ");
		
		okno.setLayout(null);
		okno.setTitle("Pridávanie senzorov/alarmov");
		okno.setSize(400, 300);
		okno.setVisible(true);
		okno.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		okno.setLocationRelativeTo(null);
		
		sBar = new JScrollPane(pole);
		sBar.setBounds(5, 5, okno.getWidth()- 17, 100);
		sBar.setBorder(obrys);
		pole.setEditable(false);
		okno.add(sBar);
		
		labelTyp.setBounds(5, 110, 130, 20);
		okno.add(labelTyp);
		
		getCmbTyp().setBounds(135, 110, 130, 20);
		okno.add(getCmbTyp());
		
		labelMeno.setBounds(5, 135, 130, 20);
		okno.add(labelMeno);
		
		poleMeno.setBounds(135, 135, 130, 20);
		poleMeno.setCaretPosition(poleMeno.getDocument().getLength());
		okno.add(poleMeno);
		
		labelMiesto.setBounds(5, 160, 130, 20);
		okno.add(labelMiesto);
		
		getCmbMiesto().setBounds(135, 160, 130, 20);
		okno.add(getCmbMiesto());
		
		cmbVolba.setBounds(5, 210, 130, 20);
		okno.add(cmbVolba);
		
		labelVazba.setBounds(5, 185, 130, 20);
		okno.add(labelVazba);

		btnVytvor.setBounds(270, 110, 118, 30);
		okno.add(btnVytvor);
		
		btnOdstran.setBounds(270, 145, 118, 30);
		okno.add(btnOdstran);
		
		btnPrirad.setBounds(270, 180, 118, 30);
		okno.add(btnPrirad);
		
		okno.revalidate();
		okno.repaint();
		
		btnVytvor.addActionListener(new BtnAction());
		btnOdstran.addActionListener(new BtnAction());
		btnPrirad.addActionListener(new BtnAction());
		
		getCmbTyp().addActionListener(new CmbAction());
		getCmbVolba().addActionListener(new CmbAction());
	}
	
	public static void vypis(String s)
	{
		try 
		{
			poleDoc.insertString(poleDoc.getLength(), s, null);
			pole.setCaretPosition(pole.getDocument().getLength());
		} 
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String getPoleMeno() {
		return poleMeno.getText();
	}
	
	public static JComboBox<String> getCmbObjekt() {
		return cmbObjekt;
	}

	public static void setCmbObjekt(JComboBox<String> cmbObjekt) {
		pridajOkno.cmbObjekt = cmbObjekt;
	}
	
	public static JComboBox<String> getCmbVolba() {
		return cmbVolba;
	}
	
	public static void setCmbVolba(JComboBox<String> cmbVolba) {
		pridajOkno.cmbVolba = cmbVolba;
	}

	public static JComboBox<String> getCmbTyp() {
		return cmbTyp;
	}

	public static void setCmbTyp(JComboBox<String> cmbTyp) {
		pridajOkno.cmbTyp = cmbTyp;
	}

	public static JComboBox<String> getCmbMiesto() {
		return cmbMiesto;
	}

	public static void setCmbMiesto(JComboBox<String> cmbMiesto) {
		pridajOkno.cmbMiesto = cmbMiesto;
	}

	public static class BtnAction implements ActionListener
	{
				
		public void actionPerformed(ActionEvent event)
		{	
			if (event.getSource() == btnVytvor)
			{
				int typ = pridajOkno.getCmbTyp().getSelectedIndex();
				String meno = pridajOkno.poleMeno.getText();
				String miesto = pridajOkno.getCmbMiesto().getSelectedItem().toString();
				
				if (pridajOkno.getCmbTyp().getSelectedItem().toString().contains("Senzor"))
					main_tmp.vytvorSenzor(meno, miesto, typ);
				else if (pridajOkno.getCmbTyp().getSelectedItem().toString().contains("Alarm"))
					main_tmp.vytvorAlarm(meno, miesto, typ);				
				else if (pridajOkno.getCmbTyp().getSelectedItem().toString().contains("Objekt"))
					main_tmp.vytvorObjekt(meno, miesto, typ);
			}
			else if (event.getSource() == btnOdstran)
			{
				String meno = pridajOkno.getPoleMeno();
				String typ = pridajOkno.getCmbTyp().getSelectedItem().toString();
				
				if (typ.contains("Senzor"))
					pridajOkno.vypis("Pocet odstranenych senzorov: " + main_tmp.odstranSenzor(meno) + "\n");
				else if (typ.contains("Alarm"))
					pridajOkno.vypis("Pocet odstranenych alarmov: " + main_tmp.odstranAlarm(meno) + "\n");
				else if (typ.contains("Objekt"))
					pridajOkno.vypis("Pocet odstranenych objektov: " + main_tmp.odstranObjekt(meno) + "\n");	
			}
			else if (event.getSource() == btnPrirad)
			{
				if (getCmbVolba().getSelectedIndex() == 0)
				{
					// senzor/alarm
					String senzor = cmbP1.getSelectedItem().toString();
					String alarm = cmbP2.getSelectedItem().toString();
					
					main_tmp.getSenzor(senzor).pridajApodS(main_tmp.getAlarm(alarm));
					
					vypis("priradujem: " + alarm + " pod " + senzor + "\n");
				}
				else if (getCmbVolba().getSelectedIndex() == 1)
				{
					// senzor/objekt
					String senzor = cmbP1.getSelectedItem().toString();
					String objekt = cmbP2.getSelectedItem().toString();
					
					((SenzorObjekt) main_tmp.getSenzor(senzor)).setOb(main_tmp.getObjekt(objekt));
					
					vypis("priradujem: " + objekt + " pod " + senzor + "\n");
				}
			}
			
		}
	}
	
	public class CmbAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == getCmbVolba())
			{
				try {
					okno.remove(cmbP1);
					okno.remove(cmbP2);
				} catch (Exception e) {
					//nevadi :)
				}
				
				if (getCmbVolba().getSelectedIndex() == 0)
				{
					cmbP1 = new JComboBox<String>(strZoznam(main_tmp.zoznamSenzorov, 1));
					cmbP1.setBounds(5, 235, 160, 20);
					okno.add(cmbP1);
					
					cmbP2 = new JComboBox<String>(strZoznam(main_tmp.zoznamAlarmov, 1));
					cmbP2.setBounds(175, 235, 160, 20);
					okno.add(cmbP2);
				}
				else if (getCmbVolba().getSelectedIndex() == 1)
				{
					cmbP1 = new JComboBox<String>(strZoznam(main_tmp.zoznamSenzorov, 2));
					cmbP1.setBounds(5, 235, 160, 20);
					okno.add(cmbP1);
					
					cmbP2 = new JComboBox<String>(strZoznam(main_tmp.zoznamObjektov, 1));
					cmbP2.setBounds(175, 235, 160, 20);
					okno.add(cmbP2);
				}
				
				cmbP1.repaint();
				cmbP1.revalidate();
				
				cmbP2.repaint();
				cmbP2.revalidate();
				
				okno.revalidate();
				okno.repaint();
			}
			
		}
	}
	
	public String[] strZoznam(ArrayList<? extends Object> list, int n)
	{
		String[] strTmp = new String[list.size()];
		
		int i = 0;
		
		Object o = list.get(0);
		
		vypis(o.getClass().getName() + " " + o.getClass().getSuperclass().getName() + " " + o.getClass().getPackage() + "\n");
		
		if (n == 1)
		{
			if (o.getClass().getName().contains("alarm"))
			{
				for (Object a: list)
					strTmp[i++] = ((Alarm) a).getMeno();
			}
			else if (o.getClass().getName().contains("senzor"))
			{
				for (Object s: list)
					strTmp[i++] = ((Senzor) s).getMeno();
			}
			else if (o.getClass().getName().contains("objekt"))
			{
				for (Object b: list)
					strTmp[i++] = ((Objekt) b).getMeno();
			}
		}
		else if (n == 2) //chcem senzory objektov
		{
			int m = 0;
			
			for (Object s: list)
			{
				if (s.getClass().getName().contains("SenzorObjekt"))
					m++;
			}
			
			String[] strTmp2 = new String[m];
			
			for (Object s: list)
			{
				if (s.getClass().getName().contains("SenzorObjekt"))
					strTmp2[i++] = ((Senzor) s).getMeno();
			}
			
			return strTmp2;
		}
		else if (n == 3) //nechcem senzory objektov
		{
			int m = 0;
			
			for (Object s: list)
			{
				if (!s.getClass().getName().contains("SenzorObjekt"))
					m++;
			}
			
			String[] strTmp2 = new String[m];
			
			for (Object s: list)
			{
				vypis(s.getClass().getName() + "\n");
				if (!s.getClass().getName().contains("SenzorObjekt"))
					strTmp2[i++] = ((Senzor) s).getMeno();
			}
			
			return strTmp2;
		}
		
		return strTmp;
	}
}
