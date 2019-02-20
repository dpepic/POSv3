package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DB.Comm;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Frame;

public class MainWindow {

	private JFrame frame;
	private JTable tblLica;
	private JTable tblArtikli;
	private JTable tblRacuni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Lica", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.EAST);

		JButton btnOFirmi = new JButton("O firmi");
		btnOFirmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (tblLica.getSelectedRowCount() == 1)
				{
					ucitajPodatke("lica", null); 
					oFirmi dialog = new oFirmi(Comm.PK.get(tblLica.getSelectedRow()));
					dialog.addWindowListener(new WindowAdapter() 
					{
						@Override
						public void windowClosed(WindowEvent arg0) 
						{
							tblLica.setModel(ucitajPodatke("lica", null));
						}
					});
					dialog.setVisible(true);
				}
			}
		});
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JButton btnNovaFirma = new JButton("Nova firma");
		btnNovaFirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				oFirmi dialog = new oFirmi("-1");
				dialog.addWindowListener(new WindowAdapter() 
				{
					@Override
					public void windowClosed(WindowEvent arg0) 
					{
						tblLica.setModel(ucitajPodatke("lica", null));
					}
				});
				dialog.setVisible(true);
			}
		});
		panel_2.add(btnNovaFirma);
		panel_2.add(btnOFirmi);
		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (tblLica.getSelectedRowCount() == 1)
				{
					ucitajPodatke("lica", null);
					Comm.obrisiRed(Comm.PK.get(tblLica.getSelectedRow()), "lice");
					tblLica.setModel(ucitajPodatke("lica", null));
				}
			}
		});
		panel_2.add(btnObrisi);
		
		JButton btnPrikaziRacune = new JButton("Prikazi racune");
		btnPrikaziRacune.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (tblLica.getSelectedRowCount() == 1)
				{
					ucitajPodatke("lica", null);
					Comm.dajRacunePoFirmi(Comm.PK.get(tblLica.getSelectedRow()));
					for (String[] red: Comm.sviRedovi)
					{
						System.out.println(String.format("Firma %s ima racun %s",
								          tblLica.getValueAt(tblLica.getSelectedRow(), 0), red[0]));
					}
				}
			}
		});
		panel_2.add(btnPrikaziRacune);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblLica = new JTable();
		tblLica.setDefaultEditor(Object.class, null);
		tblLica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblLica.setModel(ucitajPodatke("lica", null));
		scrollPane.setViewportView(tblLica);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Artikli", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JButton btnUnos = new JButton("Unos");
		btnUnos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				oArtiklu dialog = new oArtiklu("-1");
				dialog.addWindowListener(new WindowAdapter() 
				{
					@Override
					public void windowClosed(WindowEvent arg0) 
					{
						tblArtikli.setModel(ucitajPodatke("artikli", null));
					}
				});
				dialog.setVisible(true);
			}
		});
		panel_3.add(btnUnos);
		
		JButton btnIzmena = new JButton("Izmena");
		btnIzmena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (tblArtikli.getSelectedRowCount() == 1)
				{
					ucitajPodatke("artikli", null); 
					oArtiklu dialog = new oArtiklu(Comm.PK.get(tblArtikli.getSelectedRow()));
					dialog.addWindowListener(new WindowAdapter() 
					{
						@Override
						public void windowClosed(WindowEvent arg0) 
						{
							tblArtikli.setModel(ucitajPodatke("artikli", null));
						}
					});
					dialog.setVisible(true);
				}
			}
		});
		panel_3.add(btnIzmena);
		
		JButton btnBrisanje = new JButton("Brisanje");
		btnBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if (tblArtikli.getSelectedRowCount() == 1)
				{
					ucitajPodatke("artikli", null);
					Comm.obrisiRed(Comm.PK.get(tblArtikli.getSelectedRow()), "artikal");
					tblArtikli.setModel(ucitajPodatke("artikli", null));
				}
			}
		});
		panel_3.add(btnBrisanje);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		tblArtikli = new JTable();
		tblArtikli.setDefaultEditor(Object.class, null);
		tblArtikli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblArtikli.setModel(ucitajPodatke("artikli", null));
		scrollPane_1.setViewportView(tblArtikli);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Racuni", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.EAST);
		
		JButton btnORacunu = new JButton("O racunu");
		btnORacunu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (tblRacuni.getSelectedRowCount() == 1)
				{
					ucitajPodatke("racuni", null); 
					oRacunu dialog = new oRacunu(Comm.PK.get(tblRacuni.getSelectedRow()));
					dialog.addWindowListener(new WindowAdapter() 
					{
						@Override
						public void windowClosed(WindowEvent arg0) 
						{
							tblRacuni.setModel(ucitajPodatke("racuni", null));
						}
					});
					dialog.setVisible(true);
				}
			}
		});
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JButton btnNoviRacun = new JButton("Novi racun");
		btnNoviRacun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					oRacunu dialog = new oRacunu("-1");
					dialog.addWindowListener(new WindowAdapter() 
					{
						@Override
						public void windowClosed(WindowEvent arg0) 
						{
							tblRacuni.setModel(ucitajPodatke("racuni", null));
							tblArtikli.setModel(ucitajPodatke("artikli", null));
						}
					});
					dialog.setVisible(true);
			}
		});
		panel_5.add(btnNoviRacun);
		panel_5.add(btnORacunu);
		
		JButton btnObrisi_1 = new JButton("Obrisi");
		btnObrisi_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (tblRacuni.getSelectedRowCount() == 1)
				{
					ucitajPodatke("racuni", null);
					Comm.obrisiRed(Comm.PK.get(tblRacuni.getSelectedRow()), "racun");
					tblRacuni.setModel(ucitajPodatke("racuni", null));
				}
			}
		});
		panel_5.add(btnObrisi_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_4.add(scrollPane_2, BorderLayout.CENTER);
		
		tblRacuni = new JTable();
		tblRacuni.setDefaultEditor(Object.class, null);
		tblRacuni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblRacuni.setModel(ucitajPodatke("racuni", null));
		scrollPane_2.setViewportView(tblRacuni);
		frame.setLocationRelativeTo(null);
		
	}

	public static DefaultTableModel ucitajPodatke(String sta, String ID)
	{
		DefaultTableModel podaci = new DefaultTableModel();
		
		if (sta.equals("artiSaRacuna"))
		{
			Comm.dajArtikleSaRacuna(ID);
		} else
		{
			Comm.dajPodatke(sta);
		}

		for (int i = 0; i < Comm.naziviKolona.length; i++)
		{
			podaci.addColumn(Comm.naziviKolona[i]);
		}

		for (String[] red: Comm.sviRedovi)
		{
			podaci.addRow(red);
		}

		return podaci;
	}
}
