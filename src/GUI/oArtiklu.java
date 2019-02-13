package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.Comm;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class oArtiklu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNaziv;
	private JTextField txtMJ;
	private JTextField txtUcena;
	private JTextField txtMP;
	private JTextField txtPP;
	private JTextField txtLager;
	private JLabel lblNaziv;


	public oArtiklu(String PK) {
		Comm.dajArtikal(PK);
		setResizable(false);
		setBounds(100, 100, 450, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtNaziv = new JTextField();
		txtNaziv.setBounds(84, 37, 86, 20);
		contentPanel.add(txtNaziv);
		txtNaziv.setColumns(10);
		
		txtMJ = new JTextField();
		txtMJ.setText("kom");
		txtMJ.setBounds(84, 69, 86, 20);
		contentPanel.add(txtMJ);
		txtMJ.setColumns(10);
		
		txtUcena = new JTextField();
		txtUcena.setBounds(84, 145, 86, 20);
		contentPanel.add(txtUcena);
		txtUcena.setColumns(10);
		
		txtMP = new JTextField();
		txtMP.setBounds(84, 186, 86, 20);
		contentPanel.add(txtMP);
		txtMP.setColumns(10);
		
		txtPP = new JTextField();
		txtPP.setBounds(84, 227, 86, 20);
		contentPanel.add(txtPP);
		txtPP.setColumns(10);
		
		txtLager = new JTextField();
		txtLager.setBounds(84, 100, 86, 20);
		contentPanel.add(txtLager);
		txtLager.setColumns(10);
		
		JLabel lblPC = new JLabel("New label");
		lblPC.setBounds(86, 274, 46, 14);
		contentPanel.add(lblPC);
		
		JLabel lblCM = new JLabel("New label");
		lblCM.setBounds(206, 189, 46, 14);
		contentPanel.add(lblCM);
		
		lblNaziv = new JLabel("Naziv:");
		lblNaziv.setBounds(28, 40, 46, 14);
		contentPanel.add(lblNaziv);
		
		if (!PK.equals("-1"))
		{
			txtNaziv.setText(Comm.sviRedovi.get(0)[1]);
			txtLager.setText(Comm.sviRedovi.get(0)[2]);
			txtMJ.setText(Comm.sviRedovi.get(0)[3]);
			txtUcena.setText(Comm.sviRedovi.get(0)[4]);
			txtMP.setText(Comm.sviRedovi.get(0)[5]);
			lblCM.setText(Comm.sviRedovi.get(0)[6]);
			txtPP.setText(Comm.sviRedovi.get(0)[7]);
			lblPC.setText(Comm.sviRedovi.get(0)[8]);
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
						Comm.izmenaArtikla(PK, txtNaziv.getText(), txtLager.getText(), 
								           txtUcena.getText(), txtMP.getText(), txtPP.getText());
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
}
