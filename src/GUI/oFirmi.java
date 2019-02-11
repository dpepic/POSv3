package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

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


	public oFirmi() {
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
		{
			txtGrad = new JTextField();
			txtGrad.setBounds(296, 73, 86, 20);
			contentPanel.add(txtGrad);
			txtGrad.setColumns(10);
		}
		{
			txtPosta = new JTextField();
			txtPosta.setBounds(197, 73, 86, 20);
			contentPanel.add(txtPosta);
			txtPosta.setColumns(10);
		}
		{
			txtUlica = new JTextField();
			txtUlica.setBounds(296, 104, 86, 20);
			contentPanel.add(txtUlica);
			txtUlica.setColumns(10);
		}
		{
			txtBroj = new JTextField();
			txtBroj.setBounds(296, 135, 86, 20);
			contentPanel.add(txtBroj);
			txtBroj.setColumns(10);
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
