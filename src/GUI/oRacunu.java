package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
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
						String[] red = {txtID.getText(), txtKol.getText(), "Bla bla"};
						((DefaultTableModel)tblArtiNaRac.getModel()).addRow(red);
						txtID.setText(null);
						txtKol.setText(null);
					}
				});
				panel.add(btnUnesiArtikal);
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
