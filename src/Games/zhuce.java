package Games;
import java.awt.*;
import java.awt.desktop.PrintFilesEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class zhuce extends JFrame implements ActionListener{
	private JPanel p;
	private JLabel lblnum,lblname,lblpwd,lblrepwd;
	private JTextField txtnum,txtname;
	private JPasswordField txtpwd,txtrepwd;
	private JButton qrButton,tcButton;
	database mydatabase=new database();

	public zhuce() {
		super("×¢²á");
		p=new JPanel(null);
		lblnum=new JLabel("Ñ§ ºÅ:");
		lblname=new JLabel("ÐÕ Ãû:");
		lblpwd=new JLabel("ÃÜ  Âë:");
		lblrepwd=new JLabel("È·ÈÏÃÜÂë:");
		txtnum=new JTextField(20);
		txtname=new JTextField(20);
		txtpwd=new JPasswordField(20);
		txtrepwd=new JPasswordField(20);
		txtrepwd.setEchoChar('*');
		txtpwd.setEchoChar('*');
		lblnum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		lblname.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		lblpwd.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));
		lblrepwd.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 15));	
		qrButton=new JButton("È·ÈÏ×¢²á");
		tcButton=new JButton("ÍË³ö×¢²á");
		
		lblnum.setBounds(80, 40, 60, 25);
		txtnum.setBounds(145, 40, 150, 25);
		lblname.setBounds(80, 85, 60, 25);
		txtname.setBounds(145, 85,150,25);
		lblpwd.setBounds(80, 130, 60, 25);
		txtpwd.setBounds(145, 130,150,25);
		lblrepwd.setBounds(70, 175, 70, 25);
		txtrepwd.setBounds(145, 175,150,25);
		qrButton.setBounds(70, 250, 100, 30);
		tcButton.setBounds(190, 250, 100, 30);
		p.add(lblnum);
		p.add(txtnum);
		p.add(lblname);
		p.add(txtname);
		p.add(lblpwd);
		p.add(txtpwd);
		p.add(lblrepwd);
		p.add(txtrepwd);
		p.add(qrButton);
		p.add(tcButton);
		this.add(p);
		this.setSize(370,370);
		this.setLocation(780,400);
		this.setVisible(true);
		this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tcButton.addActionListener(this);
		qrButton.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e) {
		mydatabase.Connection();
		String btnlabel=e.getActionCommand();
			String numString=null;
			String nameString=null;
			String pwdString=null;
			String repwdString=null;

		//ÓÃ»§Ñ¡Ôñ×¢²á
			if(btnlabel.equals("È·ÈÏ×¢²á")) {
			numString=txtnum.getText();
			nameString=txtname.getText();
			repwdString=new String(txtrepwd.getPassword());
			pwdString=new String(txtpwd.getPassword());
			if(mydatabase.insert(numString,nameString,pwdString,repwdString)) {
				System.out.print("×¢²á³É¹¦");
				JOptionPane.showMessageDialog(null, "×¢²á³É¹¦","ÌáÊ¾",JOptionPane.INFORMATION_MESSAGE);	
				this.setVisible(false);
			}
			
			
			else {
				JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü","ÌáÊ¾",JOptionPane.ERROR_MESSAGE);	
				System.out.println(numString);
				System.out.println(nameString);
				System.out.println(pwdString);
				System.out.println(repwdString);
			}
		}
		//ÓÃ»§Ñ¡Ôñ×¢²á
		if(btnlabel.equals("ÍË³ö×¢²á")) {
			System.out.println("ÍË³ö×¢²á´°¿Ú");
			this.setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		zhuce Zhuce=new zhuce();
}
}
