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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class oRacunu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblArtiNaRac;
	private JTextField txtID;
	private JTextField txtKol;
	JLabel lblTotal = new JLabel("Total:");
	private Vector<String> PKvektor = new Vector<String>(); 

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
						for (int i = 0; i < tblArtiNaRac.getRowCount(); i++)
						{
							if (Comm.sviRedovi.get(0)[1].equals(tblArtiNaRac.getValueAt(i, 0)))
							{
								((DefaultTableModel)tblArtiNaRac.getModel()).removeRow(i);
								PKvektor.remove(i);
							}
						}
						String[] red = {Comm.sviRedovi.get(0)[1], txtKol.getText(), String.valueOf((Double.parseDouble(Comm.sviRedovi.get(0)[8]) * Double.parseDouble(txtKol.getText())))};
						PKvektor.add(txtID.getText());
						((DefaultTableModel)tblArtiNaRac.getModel()).addRow(red);
						izracunajTotal();
					}
					txtID.setText(null);
					txtKol.setText(null);
				}
			});
			panel.add(btnUnesiArtikal);


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

			if (PK != "-1")
			{
				txtID.setEnabled(false);
				txtKol.setEnabled(false);
				btnObrisiArtikal.setEnabled(false);
				btnUnesiArtikal.setEnabled(false);
				izracunajTotal();
			}

		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if (PK == "-1")
						{
							if (tblArtiNaRac.getRowCount() > 0)
							{
								Comm.unosRacuna();
								for (int i = 0; i < tblArtiNaRac.getRowCount(); i++)
								{
									Comm.unosArtiklaSaRacuna(PKvektor.get(i), tblArtiNaRac.getValueAt(i, 1).toString());
								}
								dispose();
							} else
							{
								JOptionPane.showMessageDialog(null, "Molim unesite barem jedan artikal.", "Greska!", JOptionPane.ERROR_MESSAGE);
							}
						} else
						{
							dispose();
						}
					}
				});
				{
					
					buttonPane.add(lblTotal, BorderLayout.WEST);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}	
	}
	
	public void izracunajTotal()
	{
		Double total = 0.0;
		for (int i = 0; i < tblArtiNaRac.getRowCount(); i++)
		{
			total += Double.parseDouble(tblArtiNaRac.getValueAt(i, 2).toString());
		}
		lblTotal.setText("Total: " + total);
	}

}
