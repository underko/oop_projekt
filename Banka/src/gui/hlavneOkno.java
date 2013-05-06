package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import main.main_tmp;

import objekt.Objekt;

import senzor.*;
import alarm.*;

@SuppressWarnings("serial")
public class hlavneOkno extends JFrame
{
	private static JFrame okno1;
	private static JButton btn1, btn2, btn3, btn4;
	private static JTextPane pole1;
	private static JLabel img1;
	private static JScrollPane sBar;
	private static JComboBox<String> cmbMiesto, cmbVypis, cmbSimuluj, cmbSimuluj2;
	private static StyledDocument poleDoc;
	
	ImageIcon imgic1;
	Border ciara;
	
	String[] strSimuluj = {"Poziar", "Povoden", "Vniknutie"};
	String[] strSimuluj2;
	
	public hlavneOkno()
	{
		okno1 = new JFrame();
		btn1 = new JButton("Pridaj");
		btn2 = new JButton("Vypis");
		btn3 = new JButton("Simuluj");
		btn4 = new JButton("Reset");
		pole1 = new JTextPane();
		cmbMiesto = new JComboBox<String>(main_tmp.strMiestnosti);
		cmbVypis = new JComboBox<String>(main_tmp.strTypy);
		cmbSimuluj = new JComboBox<String>(strSimuluj);
		imgic1 = new ImageIcon("./img/plan_cisty.png");
		img1 = new JLabel(imgic1);
		poleDoc = pole1.getStyledDocument();
		sBar = new JScrollPane(pole1);
		ciara = BorderFactory.createLineBorder(Color.black);
			
		okno1.setLayout(null);
		okno1.setTitle("Simulátor bezpeènostného systému banky");
		okno1.setSize(800, 600);
		okno1.setVisible(true);
		okno1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno1.setLocationRelativeTo(null);
		
		img1.setBounds(5, 5, 290, 240);
		img1.setBorder(ciara);
		okno1.add(img1);
		
		sBar.setBorder(ciara);
		sBar.setBounds(300, 5, 487, okno1.getHeight() - 40);
		
		okno1.add(sBar);
		pole1.setEditable(false);
		
		btn1.setBounds(5, 250, 100, 30);
		okno1.add(btn1);
		
		btn2.setBounds(5, 285, 100, 30);
		btn2.setToolTipText("Vypise vsetky senzory");
		okno1.add(btn2);
		
		cmbMiesto.setBounds(110, 250, 130, 30);
		cmbMiesto.setSelectedIndex(0);		
		okno1.add(cmbMiesto);
		
		cmbVypis.setBounds(110, 285, 130, 30);
		cmbVypis.setSelectedIndex(0);		
		okno1.add(cmbVypis);
		
		btn3.setBounds(5, 320, 100, 30);
		okno1.add(btn3);
		
		cmbSimuluj.setBounds(110, 320, 130, 30);
		cmbSimuluj.setSelectedIndex(0);		
		okno1.add(cmbSimuluj);
		
		btn4.setBounds(5, 355, 100, 30);
		okno1.add(btn4);
				
		okno1.revalidate();
		okno1.repaint();
		
		String datum = new SimpleDateFormat("dd. MMMMMMMMMMMM yyyy").format(Calendar.getInstance().getTime());
		pole1.setText("Vítajte !\nSimulátor bezpeènostného systému banky\nDnes je: " + datum + "\n\n");
			
		
		cmbMiesto.addActionListener(new CmbAction());
		cmbSimuluj.addActionListener(new CmbAction());
		
		btn1.addActionListener(new BtnAction());
		btn2.addActionListener(new BtnAction());
		btn3.addActionListener(new BtnAction());
		btn4.addActionListener(new BtnAction());
	}
	
	public static void vypis(String s)
	{
		try 
		{
			poleDoc.insertString(poleDoc.getLength(), s, null);
			pole1.setCaretPosition(pole1.getDocument().getLength());
		}
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void setImg(String miesto, String typ)
	{
		try {
			///hlavneOkno.vypis(main_tmp.class.getResource("img/plan_zvyraz_" + miesto + "_" + typ + ".png"));
			//new ImageIcon(img1.getClass().getResource("yourpackage/mypackage/image.gif"))
			img1.setIcon( new ImageIcon(ImageIO.read( new File("./img/plan_zvyraz_" + miesto + "_" + typ + ".png"))));
			//img1.setIcon(new ImageIcon(main_tmp.class.getResource("img/plan_zvyraz_" + miesto + "_" + typ + ".png")));
		} catch (Exception e) {
			vypis("chyba obrazku: " + e + "\n");
		}
	}
	
	public static class BtnAction implements ActionListener
	{
		
		public void actionPerformed(ActionEvent event)
		{	
			if (event.getSource() == btn1)
			{
				@SuppressWarnings("unused")
				pridajOkno okno = new pridajOkno();
			}
			else if (event.getSource() == btn2)
			{
				if (hlavneOkno.cmbVypis.getSelectedItem().toString() == "Alarm")
					main_tmp.vypisAlarmy();
				else if(hlavneOkno.cmbVypis.getSelectedItem().toString() == "Senzor")
					main_tmp.vypisSenzory();
				else if(hlavneOkno.cmbVypis.getSelectedItem().toString() == "Objekt")
					main_tmp.vypisObjekty();
			}
			else if (event.getSource() == btn3)
			{
				int typ = cmbSimuluj.getSelectedIndex();
				String info = cmbSimuluj2.getSelectedItem().toString();
				
				main_tmp.simulacia(typ, info);
			}
			else if (event.getSource() == btn4)
			{
				hlavneOkno.vypis("Vypinam vsetky aktivovane alarmy a senzory a opravujem vsetky objekty\n");
				
				for (Senzor s: main_tmp.zoznamSenzorov)
				{
					 s.setAktivita(false);
				}
				 
				for (Alarm a: main_tmp.zoznamAlarmov)
				{
					 a.setAktivita(false);
				}
				 
				for(Objekt o: main_tmp.zoznamObjektov)
				{
					o.setStav(true);
				}
			}
		}
	}
	
	public class CmbAction implements ActionListener
	{
		int index;

		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cmbMiesto)
			{
				String miesto = cmbMiesto.getSelectedItem().toString();
				hlavneOkno.vypis(	"\nVybrali ste miestnost: " + miesto + 
									"\nAktivne alarmy: " + main_tmp.ziskajAlarmy(miesto) + 
									"\nAktivne senzory: " + main_tmp.ziskajSenzory(miesto) + 
									"\nObjekty v miestnosti: " + main_tmp.ziskajObjekty(miesto) +"\n\n");
				setImg(miesto, "n");				
			}
			else if (event.getSource() == cmbSimuluj)
			{
				if (cmbSimuluj.getSelectedIndex() == 0 || cmbSimuluj.getSelectedIndex() == 1)
				{			
					try {
						okno1.remove(cmbSimuluj2);
					} catch (Exception e) {
						//nevadi :)
					}
				
					cmbSimuluj2 = new JComboBox<String>(main_tmp.strMiestnosti);
					cmbSimuluj2.setBounds(110, 355, 130, 30);
					okno1.add(cmbSimuluj2);
				
					cmbSimuluj2.repaint();
					cmbSimuluj2.revalidate();
				
					okno1.revalidate();
					okno1.repaint();
				}
				else if (cmbSimuluj.getSelectedIndex() == 2)
				{
					strSimuluj2 = new String[main_tmp.zoznamObjektov.size()];				
					
					int i = 0;
					
					for (Objekt o: main_tmp.zoznamObjektov)	
					{
						strSimuluj2[i] = o.getMeno();
						i++;
					}
					
					try {
						okno1.remove(cmbSimuluj2);
					} catch (Exception e) {
						//nevadi :)
					}
					
					cmbSimuluj2 = new JComboBox<String>(strSimuluj2);
					cmbSimuluj2.setBounds(110, 355, 130, 30);
					okno1.add(cmbSimuluj2);
				
					cmbSimuluj2.repaint();
					cmbSimuluj2.revalidate();
				
					okno1.revalidate();
					okno1.repaint();
				}
			}
			
		}
	}

}
