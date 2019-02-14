package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB.Comm;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class oRacunu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblArtiNaRac;
	private JTextField txtID;
	private JTextField txtKol;

	public oRacunu(String PK) {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				tblArtiNaRac = new JTable();
				tblArtiNaRac.setDefaultEditor(Object.class, null);
				tblArtiNaRac.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblArtiNaRac.setModel(MainWindow.ucitajPodatke("artiSaRacuna", PK));
				scrollPane.setViewportView(tblArtiNaRac);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			{
				JLabel lblIdArtikla = new JLabel("ID artikla:");
				panel.add(lblIdArtikla);
			}
			{
				txtID = new JTextField();
				txtID.setMaximumSize(new Dimension(100, 15));
				panel.add(txtID);
				txtID.setColumns(10);
			}
			{
				JLabel lblKolicina = new JLabel("Kolicina:");
				panel.add(lblKolicina);
			}
			{
				txtKol = new JTextField();
				txtKol.setMaximumSize(new Dimension(100, 15));
				panel.add(txtKol);
				txtKol.setColumns(5);
			}
			{
				JButton btnUnesiArtikal = new JButton("Unesi artikal");
				btnUnesiArtikal.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						Comm.dajArtikal(txtID.getText());
						if (Comm.sviRedovi.size() == 0)
						{
							JOptionPane.showMessageDialog(null, "Artikal sa tim ID ne postoji!", "Greska!", JOptionPane.ERROR_MESSAGE);
						} else if (Double.parseDouble(Comm.sviRedovi.get(0)[2]) - Double.parseDouble(txtKol.getText()) < 0)
						{
							JOptionPane.showMessageDialog(null, "Premalo na lageru!", "Greska!", JOptionPane.ERROR_MESSAGE);
						} else
						{
							String[] red = {Comm.sviRedovi.get(0)[1], txtKol.getText(), String.valueOf((Double.parseDouble(Comm.sviRedovi.get(0)[8]) * Double.parseDouble(txtKol.getText())))};
							((DefaultTableModel)tblArtiNaRac.getModel()).addRow(red);
						}
						txtID.setText(null);
						txtKol.setText(null);
					}
				});
				panel.add(btnUnesiArtikal);
			}
			{
				JButton btnObrisiArtikal = new JButton("Obrisi artikal");
				btnObrisiArtikal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						if (tblArtiNaRac.getSelectedColumnCount() == 1)
						{
							((DefaultTableModel)tblArtiNaRac.getModel()).removeRow(tblArtiNaRac.getSelectedRow());
						}
					}
				});
				panel.add(btnObrisiArtikal);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
