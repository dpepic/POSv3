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
					oFirmi dialog = new oFirmi(tblLica.getValueAt(tblLica.getSelectedRow(), 0).toString());
					dialog.addWindowListener(new WindowAdapter() 
					{
						@Override
						public void windowClosed(WindowEvent arg0) 
						{
							tblLica.setModel(ucitajPodatke("lica"));
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
						tblLica.setModel(ucitajPodatke("lica"));
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
					Comm.obrisiRed(tblLica.getValueAt(tblLica.getSelectedRow(), 0).toString(), "lice");
					tblLica.setModel(ucitajPodatke("lica"));
				}
			}
		});
		panel_2.add(btnObrisi);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblLica = new JTable();
		tblLica.setDefaultEditor(Object.class, null);
		tblLica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblLica.setModel(ucitajPodatke("lica"));
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
						tblArtikli.setModel(ucitajPodatke("artikli"));
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
					oArtiklu dialog = new oArtiklu(tblArtikli.getValueAt(tblArtikli.getSelectedRow(), 0).toString());
					dialog.addWindowListener(new WindowAdapter() 
					{
						@Override
						public void windowClosed(WindowEvent arg0) 
						{
							tblArtikli.setModel(ucitajPodatke("artikli"));
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
					Comm.obrisiRed(tblArtikli.getValueAt(tblArtikli.getSelectedRow(), 0).toString(), "artikal");
					tblArtikli.setModel(ucitajPodatke("artikli"));
				}
			}
		});
		panel_3.add(btnBrisanje);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		tblArtikli = new JTable();
		tblArtikli.setDefaultEditor(Object.class, null);
		tblArtikli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblArtikli.setModel(ucitajPodatke("artikli"));
		scrollPane_1.setViewportView(tblArtikli);
		frame.setLocationRelativeTo(null);
		
	}

	public DefaultTableModel ucitajPodatke(String sta)
	{
		DefaultTableModel podaci = new DefaultTableModel();
		Comm.dajPodatke(sta);

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
