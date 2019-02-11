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

public class MainWindow {

	private JFrame frame;
	private JTable tblLica;

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
							System.out.println("Byeeeeee :(");
						}
					});
					dialog.setVisible(true);
				}
			}
		});
		panel_2.add(btnOFirmi);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblLica = new JTable();
		tblLica.setDefaultEditor(Object.class, null);
		tblLica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblLica.setModel(ucitajPodatke("lica"));
		scrollPane.setViewportView(tblLica);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
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
