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

	public oFirmi(String PK) {
		Comm.dajFirmu(PK); 
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
		}
			if (!PK.equals("-1"))
			{
				
				txtJIB.setText(Comm.sviRedovi.get(0)[1]);
				txtPIB.setText(Comm.sviRedovi.get(0)[2]);
				txtNaziv.setText(Comm.sviRedovi.get(0)[3]);
				txtTel.setText(Comm.sviRedovi.get(0)[4]);
				txtFax.setText(Comm.sviRedovi.get(0)[5]);
				txtMail.setText(Comm.sviRedovi.get(0)[6]);
			}
		{
			if (Comm.sviRedovi.size() > 1)
			{
				txtPosta = new JTextField(Comm.sviRedovi.get(1)[2]);
				txtGrad = new JTextField(Comm.sviRedovi.get(1)[3]);
				txtUlica = new JTextField(Comm.sviRedovi.get(1)[4]);
				txtBroj = new JTextField(Comm.sviRedovi.get(1)[5]);
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
							Comm.izmenaAdrese(Comm.sviRedovi.get(1)[0], txtPosta.getText(), txtGrad.getText(), txtUlica.getText(), txtBroj.getText());
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
}
