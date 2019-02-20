package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.Comm;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class oFirmi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtJIB;
	private JTextField txtPIB;
	private JTextField txtNaziv;
	private JTextField txtTel;
	private JTextField txtFax;
	private JTextField txtMail;
	private JTextField txtGrad;
	private JTextField txtPosta;
	private JTextField txtUlica;
	private JTextField txtBroj;
	private JButton btnAdrNazad = new JButton("<");
	private JButton btnAdrNapred = new JButton(">");
	private int indeksAdr = 1;
	private JButton btnNovaAdresa;
	private JButton btnObrisi;

	public oFirmi(String PK) {
		Comm.dajFirmu(PK); 
		btnAdrNapred.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (Comm.sviRedovi.size()-1 > indeksAdr)
				{
					indeksAdr++;
				} else
				{
					indeksAdr = 1;
				}
				ucitajAdresu(indeksAdr);
			}
		});
		btnAdrNapred.setEnabled(false);
		btnAdrNazad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (indeksAdr == 1)
					indeksAdr = Comm.sviRedovi.size() - 1;
				else
					indeksAdr--;
				ucitajAdresu(indeksAdr);
			}
		});
		btnAdrNazad.setEnabled(false);
		setResizable(false);
		setBounds(100, 100, 450, 308);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtJIB = new JTextField();
			txtJIB.setBounds(54, 11, 86, 20);
			contentPanel.add(txtJIB);
			txtJIB.setColumns(10);
		}
		{
			txtPIB = new JTextField();
			txtPIB.setBounds(54, 42, 86, 20);
			contentPanel.add(txtPIB);
			txtPIB.setColumns(10);
		}
		{
			txtNaziv = new JTextField();
			txtNaziv.setBounds(54, 73, 86, 20);
			contentPanel.add(txtNaziv);
			txtNaziv.setColumns(10);
		}
		{
			txtTel = new JTextField();
			txtTel.setBounds(54, 104, 86, 20);
			contentPanel.add(txtTel);
			txtTel.setColumns(10);
		}
		{
			txtFax = new JTextField();
			txtFax.setBounds(54, 135, 86, 20);
			contentPanel.add(txtFax);
			txtFax.setColumns(10);
		}
		{
			txtMail = new JTextField();
			txtMail.setBounds(54, 167, 86, 20);
			contentPanel.add(txtMail);
			txtMail.setColumns(10);

			txtPosta = new JTextField();
			txtGrad = new JTextField();
			txtUlica = new JTextField();
			txtBroj = new JTextField();
		}
		if (!PK.equals("-1"))
		{
			txtJIB.setText(Comm.sviRedovi.get(0)[1]);
			txtPIB.setText(Comm.sviRedovi.get(0)[2]);
			txtNaziv.setText(Comm.sviRedovi.get(0)[3]);
			txtTel.setText(Comm.sviRedovi.get(0)[4]);
			txtFax.setText(Comm.sviRedovi.get(0)[5]);
			txtMail.setText(Comm.sviRedovi.get(0)[6]);
		} else
		{
			Comm.sviRedovi.clear();
		}
		{

			if (Comm.sviRedovi.size() > 1)
			{
				ucitajAdresu(indeksAdr);

				if (Comm.sviRedovi.size() > 2)
				{
					btnAdrNapred.setEnabled(true);
					btnAdrNazad.setEnabled(true);
				} 
			} else
			{
				txtPosta = new JTextField();
				txtGrad = new JTextField();
				txtUlica = new JTextField();
				txtBroj = new JTextField();
			}
			txtGrad.setBounds(296, 73, 86, 20);
			contentPanel.add(txtGrad);
			txtGrad.setColumns(10);
		}
		{

			txtPosta.setBounds(197, 73, 86, 20);
			contentPanel.add(txtPosta);
			txtPosta.setColumns(10);
		}
		{

			txtUlica.setBounds(296, 104, 86, 20);
			contentPanel.add(txtUlica);
			txtUlica.setColumns(10);
		}
		{

			txtBroj.setBounds(296, 135, 86, 20);
			contentPanel.add(txtBroj);
			txtBroj.setColumns(10);
		}

		JLabel lblPostanski = new JLabel("Postanski");
		lblPostanski.setBounds(197, 56, 46, 14);
		contentPanel.add(lblPostanski);

		JLabel lblGrad = new JLabel("Grad");
		lblGrad.setBounds(306, 56, 46, 14);
		contentPanel.add(lblGrad);

		JLabel lblNewLabel = new JLabel("Ulica");
		lblNewLabel.setBounds(237, 107, 46, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Broj");
		lblNewLabel_1.setBounds(237, 138, 46, 14);
		contentPanel.add(lblNewLabel_1);



		btnAdrNazad.setBounds(178, 22, 41, 23);
		contentPanel.add(btnAdrNazad);



		btnAdrNapred.setBounds(226, 22, 41, 23);
		contentPanel.add(btnAdrNapred);
		
		btnNovaAdresa = new JButton("Nova adresa");
		btnNovaAdresa.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				for (String[] red: Comm.sviRedovi)
				{
					    
					if (txtPosta.getText().equals(red[2]) && txtGrad.getText().equals(red[3]) && txtUlica.getText().equals(red[4]) && txtBroj.getText().equals(red[5]))
					{
						JOptionPane.showMessageDialog(contentPanel, "Adresa vec postoji!", "Greska!", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				Comm.izmenaAdrese("-1", txtPosta.getText(), txtGrad.getText(), txtUlica.getText(), txtBroj.getText(), Comm.sviRedovi.get(0)[0]);
				dispose();
			}
		});
		btnNovaAdresa.setBounds(277, 22, 105, 23);
		contentPanel.add(btnNovaAdresa);
		
		btnObrisi = new JButton("Obrisi");
		btnObrisi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Comm.obrisiRed(Comm.sviRedovi.get(indeksAdr)[0], "adresa");
				Comm.sviRedovi.remove(indeksAdr);
				if (Comm.sviRedovi.size() < 2)
				{
					txtPosta.setText("");
					txtGrad.setText("");
					txtUlica.setText("");
					txtBroj.setText("");
				} else 
				{
					indeksAdr = 1;
					ucitajAdresu(indeksAdr);
				}
			}
		});
		btnObrisi.setBounds(296, 167, 89, 23);
		contentPanel.add(btnObrisi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						Comm.izmenaFirme(PK, txtJIB.getText(), txtPIB.getText(), txtNaziv.getText(), txtTel.getText(), 
								txtFax.getText(), txtMail.getText());
						if (Comm.sviRedovi.size() > 1)
						{
							Comm.izmenaAdrese(Comm.sviRedovi.get(indeksAdr)[0], txtPosta.getText(), txtGrad.getText(), txtUlica.getText(), txtBroj.getText(), null);
						} else if (Comm.sviRedovi.size() == 1 && txtPosta.getText() != "" && txtGrad.getText() != "" && txtUlica.getText() != "" && txtBroj.getText() != "")
						{
							Comm.izmenaAdrese("-1", txtPosta.getText(), txtGrad.getText(), txtUlica.getText(), txtBroj.getText(), Comm.sviRedovi.get(0)[0]);
						} else if (txtPosta.getText() != "" && txtGrad.getText() != "" && txtUlica.getText() != "" && txtBroj.getText() != "")
						{
							Comm.dajFirmu("-1");
							Comm.izmenaAdrese("-1", txtPosta.getText(), txtGrad.getText(), txtUlica.getText(), txtBroj.getText(), Comm.sviRedovi.get(0)[0]);
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void ucitajAdresu(int indeks)
	{
		txtPosta.setText(Comm.sviRedovi.get(indeks)[2]); 
		txtGrad.setText(Comm.sviRedovi.get(indeks)[3]);
		txtUlica.setText(Comm.sviRedovi.get(indeks)[4]); 
		txtBroj.setText(Comm.sviRedovi.get(indeks)[5]);
	}
}
